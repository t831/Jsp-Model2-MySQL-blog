package com.cos.gmail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("이메일주소", "비밀번호");
	}
}