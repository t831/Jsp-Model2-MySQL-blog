package com.cos.controller;
import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cos.action.Action;
import com.cos.action.board.BoardFactory;

// 진입 주소 = http://localhost:8000/프로젝트명(blog)/user/cmd=list
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 들어오는 모든 문자를 UTF-8로 인코딩
		request.setCharacterEncoding("UTF-8");
		// 2. command 처리
		String cmd = request.getParameter("cmd");
		// 3. command 검증
		if (cmd == null || cmd.equals("")) { // null이거나 공백일 때 처리해줘야 함
			cmd = "list"; // 메인 페이지
		}
		// 4. Factory 연결
		Action action = BoardFactory.getAction(cmd);
		// 5. execute 실행
		if (action != null) {
			action.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}