package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test3")
public class AjaxTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxTest3() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8"); // MIME type

		BufferedReader in = request.getReader();
		String requestData = in.readLine();
		System.out.println(requestData);
				
		PrintWriter out = response.getWriter();
		out.print(50);
		out.flush();
	}

}
