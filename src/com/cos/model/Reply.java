package com.cos.model;

import java.sql.Timestamp;

public class Reply {
	private int id;
	private int userId;
	private int commentId;
	private int boardId;
	private String content;
	private Timestamp createDate;
	private User user = new User(); // DB와 상관 X
	private ResponseData responseData = new ResponseData(); // DB와 상관 X
	
	public Reply() {}

	
	
	public Reply(int id, int userId, int commentId, int boardId, String content, Timestamp createDate, User user,
			ResponseData responseData) {
		super();
		this.id = id;
		this.userId = userId;
		this.commentId = commentId;
		this.boardId = boardId;
		this.content = content;
		this.createDate = createDate;
		this.user = user;
		this.responseData = responseData;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	
	
}
