package com.cos.action.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.util.Script;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader in = request.getReader();
		int commentId = Integer.parseInt(in.readLine());
		System.out.println("commentId : " + commentId);
		
		CommentDao commentDao = new CommentDao();
		int result = commentDao.delete(commentId);
		
		if (result == 1) {
			PrintWriter out = response.getWriter();
			out.print("OK"); // printIn을 쓰면 \n이 함께 전송돼서 문자열 비교 불가능
			out.flush();
		} else {
			Script.back(response);
		}
		
	}

}
