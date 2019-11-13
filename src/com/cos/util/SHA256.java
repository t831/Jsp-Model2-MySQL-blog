package com.cos.util;

import java.security.MessageDigest;

// 256비트 길이의 암호, 해시, 복호화 안 됨
public class SHA256 {
	// 비밀번호를 암호화해서 리턴
	public static String getEncrypt(String rawPassword, String salt) {
		// rawPassword = "qw5678qw";
		// salt = "cos";
		String result = "";

		byte[] b = (rawPassword + salt).getBytes();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b); // MessageDigest가 SHA-256으로 암호화된 값을 들고 있음

			byte[] bResult = md.digest();

//			손상된 값 출력됨
//			for (byte data : bResult) {
//				System.out.print(data + " ");
//			}

// 			손상된 거 &연산으로 복구해서 출력			
			StringBuffer sb = new StringBuffer();
			for (byte data : bResult) {
			sb.append(Integer.toString(data & 0xff, 16));
			}
			
			result = sb.toString();
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
