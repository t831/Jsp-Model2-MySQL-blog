
// 이거하는중
package com.cos.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.CommentDao;
import com.cos.dao.ReplyDao;
import com.cos.model.Comment;
import com.cos.model.Reply;
import com.cos.util.Script;
import com.google.gson.Gson;

public class ReplyWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("content");

		System.out.println("userId : " + userId);
		System.out.println("commentId : " + commentId);
		System.out.println("content : " + content);

		Reply replyForm = new Reply();
		replyForm.setUserId(userId);
		replyForm.setCommentId(commentId);
		replyForm.setContent(content);

		ReplyDao dao = new ReplyDao();
		int result = dao.save(replyForm);
		System.out.println(result);
		if (result == 1) {
			Reply reply = dao.findByMaxId();
			reply.getResponseData().setStatusCode(1);
			reply.getResponseData().setStatus("ok");
			reply.getResponseData().setStatusMessage("complete");

			// Gson 이용해서 JSON으로 변환
			Gson gson = new Gson();
			String replyJson = gson.toJson(reply);
			response.setContentType("application/json; charset=utf-8");
			// 데이터 응답
			PrintWriter out = response.getWriter();
			out.print(replyJson);
			out.flush();
		} else {
			Script.back(response);
		}

	}

}
