<%@page import="com.cos.model.User"%>
<%@page import="com.cos.gmail.Gmail"%>
<%@page import="com.cos.gmail.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/include/nav.jsp"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>



<%
		String from = "이메일주소";
		
		User user = new User();
		user = (User)request.getAttribute("user");
		String username = user.getUsername();
		
		
		String to = user.getEmail();
		
		String code = SHA256.getEncrypt(to, "cos");

		//사용자에게 보낼 메시지
		String subject = "회원가입을 위한 이메일 인증 메일입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행해주세요. " 
		        + "<a href='http://localhost:8000/blog/gmail/gmailCheckAction.jsp?code=" + code +"&username="+username
				+ "'>이메일 인증하기</a>";

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "false");
		p.put("mail.smtp.socketFactory.port", "465"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.sockerFactory.fallback", "false");

		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html; charset=UTF8");
			Transport.send(msg);
		} catch (Exception e) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이메일 인증 오류')");
			script.println("history.back();");
			script.println("</script>");
		}
	%>	

<section class="contact_area p_120">
		<div class="container">
			<div class="row">
				<div>
					<img id="img__wrap" />
				</div>
				<div class="col-lg-3"></div>
				<div class="col-lg-6" style="border-style: outset; padding:30px;">
					<h4 style="text-align:center; font-family:'맑은 고딕';">이메일 주소 인증 메일이 전송되었습니다. <br/> 이메일에 들어가서 인증해주세요.</h4>	
				</div>
				<div class="col-lg-3"></div>
			</div>
		</div>
		<br/><br/><br/>
	</section>

<%@ include file="/include/footer.jsp"%>