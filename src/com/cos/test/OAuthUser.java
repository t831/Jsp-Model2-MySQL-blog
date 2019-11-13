package com.cos.test;

public class OAuthUser {
	
	private int id;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expire_in;
	
	private String username; // email의 @ 이전 부분만 파싱
	private String email;
	
	// private int userId; // 실제 사용할 때는 join 해야함
	
	public OAuthUser() {
	}

	public OAuthUser(int id, String access_token, String refresh_token, String token_type, String expire_in,
			String username, String email) {
		super();
		this.id = id;
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.token_type = token_type;
		this.expire_in = expire_in;
		this.username = username;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(String expire_in) {
		this.expire_in = expire_in;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}