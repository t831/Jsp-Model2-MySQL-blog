package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserUpdateAction implements Action{
	private static final String TAG = "UserJoinAction : ";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 폼 태그의 데이터를 받아서 DB에 insert 후 페이지 이동
		// 나중에 null값, 유효성 검사 처리도 해야함
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		// 암호화 해주는 코드
		String password = SHA256.getEncrypt(rawPassword, "cos");
		
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);  // 나중에 암호화 encryption 해야됨 (라이브러리 사용)
		user.setEmail(email);
		user.setAddress(address);
		
		// DAO 연결
		UserDao dao = new UserDao();
		int result = dao.update(user);
		if(result==1) {
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}
		
	}
	
}
