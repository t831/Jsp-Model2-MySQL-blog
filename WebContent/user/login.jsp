<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/nav.jsp"%>

<!-- Application : 서버 꺼질 때까지
	   Session : 브라우저 닫을 때까지
	   Request : 요청할 때마다
	   PageContext : 해당 페이지에서만  -->

<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<h2>Login</h2>
				<hr />
				<form class="row contact_form" action="/blog/user?cmd=login" method="post">
					<div class="col-md-12">
						<div class="form-group">
							<c:choose>
								<c:when test="${empty cookie.username.value}">
									<input type="text" class="form-control" id="username" name="username" placeholder="ID" />
								</c:when>
								<c:otherwise>
								
									<input type="text" class="form-control" id="username" name="username" placeholder="ID" value="${cookie.username.value}" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" placeholder="PASSWORD" />
						</div>
						
					</div>
					<div class="col-md-12 text-right">
						<label> <input type="checkbox" name="rememberMe" /> Remember me
						</label>
					</div>

					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Login</button>
					</div>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
</section>
<div style="margin-bottom: 60px"></div>
<%@include file="/include/footer.jsp"%>