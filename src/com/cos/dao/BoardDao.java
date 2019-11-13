package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.model.User;
import com.cos.util.DBClose;

public class BoardDao {
	// 싱글톤으로 만들어야 하는데 그냥 한다고 하네요?!
	private Connection conn; // MySQL과 연결
	private PreparedStatement pstmt; // 쿼리문을 작성, 실행
	private ResultSet rs; // 결과 보관하는 커서

	// 인기 리스트 3건 가져오기
	public List<Board> findOrderByReadCountDesc() {

		final String SQL = "SELECT * FROM board ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<Board>();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() : 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boards.add(board); // 컬렉션에 담아주기
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	public int save(Board board) {

		final String SQL = "INSERT INTO board(userId, title, content, createDate) VALUES (?,?,?, now())";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 조회수 증가
	public int increaseReadCount(int id) {

		final String SQL = "UPDATE board SET readcount = readCount + 1 WHERE id =?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 문서 업데이트
	public int update(Board board) {
		final String SQL = "UPDATE board SET title=?, content=? WHERE Id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;// 오류
	}

	// 토탈 페이지
	public int totalRecords() {
		final String SQL = "SELECT COUNT(*) FROM board";
		conn = DBConn.getConnection();
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return cnt;
	}

	// 리스트 보기
	public List<Board> findAll() {

		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<Board>();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() : 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString(("content")) + " ");
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boards.add(board); // 컬렉션에 담아주기
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	// 서치
		public List<Board> findAll(int page, String search) {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM board b, user u ");
			sb.append("WHERE b.userId = u.id AND ");
			sb.append("(b.content LIKE ? OR b.title LIKE ?) ");
			sb.append("ORDER BY b.id DESC limit ?, 3");
			
			final String SQL = sb.toString();
			conn = DBConn.getConnection();

			try {
				List<Board> boards = new ArrayList<Board>();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				pstmt.setInt(3, (page - 1) * 3);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Board board = new Board();
					board.setId(rs.getInt("b.id"));
					board.setUserId(rs.getInt("b.userId"));
					board.setTitle(rs.getString("b.title"));
					board.setContent(rs.getString(("b.content")) + " ");
					board.setReadCount(rs.getInt("b.readCount"));
					board.setCreateDate(rs.getTimestamp("b.createDate"));
					board.getUser().setUsername(rs.getString("u.username"));
					boards.add(board); // 컬렉션에 담아주기
				}

				return boards;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBClose.close(conn, pstmt, rs);
			}
			return null;
		}

	// 3개만 보기
	public List<Board> findAll(int page) {
		// (1) join 쿼리 변경
		final String SQL = "SELECT * FROM board b, user u WHERE b.userId = u.id ORDER BY b.id DESC LIMIT ?, 3;";
		conn = DBConn.getConnection();

		try {
			List<Board> boards = new ArrayList<Board>();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page - 1) * 3);
			rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next() : 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString(("b.content")) + " ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));

				// (2) board의 user 객체에 username 저장
				board.getUser().setUsername(rs.getString("u.username"));
				
				boards.add(board); // 컬렉션에 담아주기
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	// 상세 보기
	public Board findById(int id) {

		final String SQL = "SELECT * FROM board b, user u WHERE b.userId = u.id AND b.id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // rs.next() : 커서 이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return null;
	}

	// 삭제
	public int delete(int id) {
		final String SQL = "DELETE FROM board WHERE id = ?";
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
