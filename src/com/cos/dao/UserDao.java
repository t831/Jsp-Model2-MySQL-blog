package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.model.User;
import com.cos.util.DBClose;

public class UserDao {
	// 싱글톤으로 만들어야하는데 그냥 함.
	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성하고 실행하기위해 필요함
	private ResultSet rs; // 결과를 보관할 커서 받기 위해
	
	// 이미지 업로드
		public int updateImg(User user) {

			final String SQL = "UPDATE user SET userProfile=? WHERE id = ?";
			conn = DBConn.getConnection();

			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, user.getUserProfile());
				pstmt.setInt(2, user.getId());
				int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴

				return result;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBClose.close(conn, pstmt);
			}
			return -1;// 오류
		}
	
	
	
	public int setEmailAuthByUsername(String username) {

		final String SQL = "UPDATE user SET emailAuth =1 WHERE username = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return 0;
	}

	public User findByUsername(String username) {

		final String SQL = "SELECT * FROM user WHERE username = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setEmailAuth(rs.getInt("emailAuth"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	public int save(User user) {

		final String SQL = "INSERT INTO user(username, password, email, address, createDate) VALUES(?,?,?,?,now())";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;// 오류
	}

	// update
	public int update(User user) {

		final String SQL = "UPDATE user SET password =?, address=?, WHERE id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate();// 변경된 튜플(행)의 개수를 리턴

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;// 오류
	}

	// 로그인 인증 성공 : 1 / DB오류 : -1 / 미인증 : 0
	public int findByUsernameAndPassword(String username, String password) {
		// count를 사용하면 맞으면 1 틀리면 0이 리턴된다.
		final String SQL = "SELECT count(id) FROM user WHERE username = ? AND password = ?;";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// result 값이 1일때 성공한 것
				int result = rs.getInt(1);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}
}