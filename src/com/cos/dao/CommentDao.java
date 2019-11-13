package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.model.Comment;
import com.cos.model.User;
import com.cos.util.DBClose;

public class CommentDao {
	// 싱글톤으로 만들어야하는데 그냥 함.
	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성하고 실행하기위해 필요함
	private ResultSet rs; // 결과를 보관할 커서 받기 위해

	// findByMaxId
	// synchronized : 동시 접근 제어 (임계 구역)
	synchronized public Comment findByMaxId() {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.userId, c.boardId, c.content, c.createDate, u.username ");
		sb.append("FROM comment c, user u ");
		sb.append("WHERE c.userId = u.id ");
		sb.append("AND c.id = (SELECT max(id) FROM comment) ");

		final String SQL = sb.toString();
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setUserId(rs.getInt("c.userId"));
				comment.setBoardId(rs.getInt("c.boardId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));

				return comment;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	// save
	public int save(Comment comment) {

		final String SQL = "INSERT INTO comment(userId, boardId, content, createDate) VALUES (?,?,?,now())";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, comment.getUserId());
			pstmt.setInt(2, comment.getBoardId());
			pstmt.setString(3, comment.getContent());

			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 리스트 보기
	public List<Comment> findByBoardId(int boardId) {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.userId, c.boardId, c.content, c.createDate, u.username, u.userProfile ");
		sb.append("FROM comment c, user u ");
		sb.append("WHERE c.userId = u.id ");
		sb.append("and boardId = ?");

		final String SQL = sb.toString();
		conn = DBConn.getConnection();

		try {
			List<Comment> comments = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setBoardId(rs.getInt("c.boardId"));
				comment.setUserId(rs.getInt("c.userId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));
				comment.getUser().setUserProfile(rs.getString("u.userProfile"));
				
				comments.add(comment); // 컬렉션에 comment 담기
			}

			return comments;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	// 삭제
	public int delete(int id) {
		final String SQL = "DELETE FROM comment WHERE id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

}