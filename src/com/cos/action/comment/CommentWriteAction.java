package com.cos.action.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.model.Comment;
import com.google.gson.Gson;

public class CommentWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String content = request.getParameter("content");

//		System.out.println("userId : " + userId);
//		System.out.println("boardId : " + boardId);
//		System.out.println("content : " + content);
		
		Comment commentForm = new Comment();
		commentForm.setUserId(userId);
		commentForm.setBoardId(boardId);
		commentForm.setContent(content);
		
		CommentDao dao = new CommentDao();
		// form으로 받은 데이터를 Comment 객체로 변환
		int result = dao.save(commentForm);
		
		if (result == 1 ) {
			// comment 테이블 가장 마지막에 만들어진 comment의 튜플이 필요
			Comment comment = dao.findByMaxId();
			comment.getResponseData().setStatusCode(1);
			comment.getResponseData().setStatus("ok");
			comment.getResponseData().setStatusMessage("complete");
			
			// Gson 이용해서 JSON으로 변환
			Gson gson = new Gson();
			String commentJson = gson.toJson(comment);
			response.setContentType("application/json; charset=utf-8");
			// 데이터 응답
			PrintWriter out = response.getWriter();
			out.print(commentJson);
			out.flush();
		}
		
	}

}
