package com.cos.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBClose {
	
	// DML (INSERT, UPDATE, DELETE) 를 닫을 때 사용
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DQL (SELECT) 를 닫을 때 사용
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
