package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.Script;
import com.mysql.cj.Session;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserImgupAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String path = request.getSession().getServletContext().getRealPath("media");
		HttpSession session = request.getSession();

		MultipartRequest multi = new MultipartRequest (
				request,
				path, // 경로
				1024 * 1024 * 2, // 2MB
				"UTF-8",
				new DefaultFileRenamePolicy() // 동일한 파일명 들어오면 파일명 뒤에 숫자 붙임
		);

		int id = Integer.parseInt(request.getParameter("id"));
		String filename = multi.getFilesystemName("userProfile"); // 정책에 의해서 변경된 이름
		String contextPath = request.getSession().getServletContext().getContextPath();
		String filepath = contextPath + "/media/" + filename;
		
		User user = new User();
		user.setId(id);
		user.setUserProfile(filepath);
		
		UserDao dao = new UserDao();
		int result = dao.updateImg(user);
		
		if(result == 1) {
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		}else {
			Script.back(response);
		}
	}
}
