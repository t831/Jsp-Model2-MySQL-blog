package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class UserLoginAction implements Action {

	private static final String TAG = "UserLoginAction : ";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 유효성 검사  
		   
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
//		System.out.println(TAG+"rawPassword : "+rawPassword);
		String rememberMe = request.getParameter("rememberMe");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		
		
//		System.out.println(TAG+"password : "+password);
//		System.out.println(TAG+"username : " + username);
//		System.out.println(TAG+"password : " + password);
//		System.out.println(TAG+"rememberMe : " + rememberMe);
		
		UserDao dao = new UserDao();
		int result = dao.findByUsernameAndPassword(username, password);
		
		if(result==1) {
			// 쿠키 저장
			if(rememberMe != null) { // rememberMe 체크했을 때
				System.out.println(TAG+"쿠키 저장");
				Cookie c = new Cookie("username", username);
				// 쿠키 : 보관 시간을 지정해줘야함
				c.setMaxAge(6000); // 단위 : seconds
				response.addCookie(c);
			} else { // rememberMe 체크안함
				System.out.println(TAG+"쿠키 삭제");
				Cookie c = new Cookie("username", null);
				c.setMaxAge(0);
				response.addCookie(c);
			}
			
			// User 객체로 세션 등록
			HttpSession session = request.getSession();
			
			// User 객체 가져오기
			User user = dao.findByUsername(username);
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		} else { // 0 or -1
			Script.back(response);
		}
	}

}
