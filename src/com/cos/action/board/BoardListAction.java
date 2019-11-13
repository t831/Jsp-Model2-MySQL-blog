package com.cos.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.util.Utils;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("page") == null)
			return;

		int page = Integer.parseInt(request.getParameter("page"));

		// page <= 0 or page > maxNum 버튼 비활성화
		if (page <= 0) {
			page = 1;
		}

		BoardDao bDao = new BoardDao();
		List<Board> boards = null; // paging하기
		List<Board> hotBoards = bDao.findOrderByReadCountDesc();

		// search와 목록을 분기
		if(request.getParameter("search") == null || request.getParameter("search").contentEquals("")) {
			boards = bDao.findAll(page);
			request.setAttribute("search", null);
		} else {
			String search = request.getParameter("search");
			boards = bDao.findAll(page, search);
			request.setAttribute("search", search);
		}
		
		Utils.setPreviewImg(boards); // 이미지 priviewImg에 저장
		Utils.setPreviewContent(boards); // 이미지 태그 제거
		Utils.setPreviewImg(hotBoards); // 이미지 priviewImg에 저장

		// 내용 담아서 이동
		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);

		// 페이징

		int totalRecords = bDao.totalRecords(); // 전체 게시글 수
		int totalPage = 0; // 총 페이지 수
		if (totalRecords != 0) {
			if ((totalRecords % 3) == 0) {
				totalPage = (totalRecords / 3);
			} else {
				totalPage = (totalRecords / 3) + 1;
			}
		}

		int numPerPage = 5; // 페이지당 레코드 수
		int start = 1; // 시작레코드
		int end = 5; // 마지막 레코드

		if (page <= 5) {
			start = 1;
			end = 5;
		} else if (page > 5) {
			start = ((page / 5) * 5) + 1;
			end = ((page / 5) * 5) + 5;
		}

		request.setAttribute("totalPage", totalPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);

		RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
		dis.forward(request, response);
	}
}
