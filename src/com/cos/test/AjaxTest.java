package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.model.Comment;
import com.google.gson.Gson;

@WebServlet("/test")
public class AjaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxTest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청에 응답해줄 Stream
		// 인코딩 지정 안 해주면 한글 깨짐
		request.setCharacterEncoding("UTF-8");
		// MIME 타입 확인해서 지정해줘야 함
		// https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
                                 		response.setContentType("application/json; charset=UTF-8");

		// 요청 데이터 처리
		// replyJsonString (1)
		BufferedReader in = request.getReader();
		String replyJsonString = in.readLine();
		System.out.println("요청 데이터 : " + replyJsonString);

		// lib에 GSon 추가
		Gson gson = new Gson();
		// 파싱할 때 null값 오류 뜨는지 테스트
		Comment reply = gson.fromJson(replyJsonString, Comment.class);
		System.out.println("id : " + reply.getId()); // 숫자 null은 0으로 떨어짐
		System.out.println("boardid : " + reply.getBoardId());
		System.out.println("userid : " + reply.getUserId());
		System.out.println("content : " + reply.getContent());
		System.out.println("createDate : " + reply.getCreateDate()); // 문자열은 null

		// 응답 데이터 처리 (2)
		String jsonData = "{\"name\" : \"손흥민\", \"sal\" : 100}";

		PrintWriter out = response.getWriter();
		out.println(jsonData);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
