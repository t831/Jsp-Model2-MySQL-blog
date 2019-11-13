<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cos.model.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="/blog/img/favicon.png" type="/blog/image/png">
<title>My Blog :: 2019</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/blog/css/bootstrap.css">
<link rel="stylesheet" href="/blog/vendors/linericon/style.css">
<link rel="stylesheet" href="/blog/css/font-awesome.min.css">
<link rel="stylesheet" href="/blog/vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet" href="/blog/vendors/lightbox/simpleLightbox.css">
<link rel="stylesheet" href="/blog/vendors/nice-select/css/nice-select.css">
<link rel="stylesheet" href="/blog/vendors/animate-css/animate.css">
<link rel="stylesheet" href="/blog/vendors/jquery-ui/jquery-ui.css">
<!-- main css -->
<link rel="stylesheet" href="/blog/css/style.css">
<link rel="stylesheet" href="/blog/css/responsive.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


</head>
<body>
	<!--================Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container box_1620">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="#"><img src="/blog/img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="col-lg-1 col-md-6 col-sm-6"></div>
					<div class="col-lg-10 col-md-6 col-sm-6">
						<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
							<ul class="nav navbar-nav menu_nav">
								<li class="nav-item active"><a class="nav-link" href="/blog/index.jsp">Home</a></li>
								<li class="nav-item"><a class="nav-link" href="/blog/board/write.jsp">Posting</a></li>
								<c:choose>
								<c:when test="${empty sessionScope.user}">
									<li class="nav-item"><a class="nav-link" href="/blog/user/join.jsp">Join</a></li>
									<li class="nav-item"><a class="nav-link" href="/blog/user/login.jsp">Login</a></li>
								</c:when>
								<c:otherwise>
									<li class="nav-item"><a class="nav-link" href="/blog/user/update.jsp">Profile</a></li>
									<li class="nav-item"><a class="nav-link" href="/blog/user?cmd=logout">Logout</a></li>
								</c:otherwise>
							</c:choose>
							</ul>

							<ul class="nav navbar-nav navbar-right header_social ml-auto">
								<li>
									<div class="input-group">
										<form action="/blog/board?cmd=list&page=1" method="POST">
											<input type="text" name = "search" class="form-control" placeholder="Search Posts"  style="width:200px; float:left;">
												<button class="btn btn-default" type="submit" style="margin-left:3px;">
													<i class="lnr lnr-magnifier"></i>
												</button>
										</form>
										<c:choose>
										<c:when test="${not empty sessionScope.user}">
											<img id = "userProfile" src ="${sessionScope.user.userProfile}" width="37px" height="37px" style="margin-left:10px; border-radius:50%;" onclick="location.href='/blog/user/uploadForm.jsp'">
										</c:when>
										</c:choose>
									</div>
								</li>
							</ul>
						</div>

					</div>
					<div class="col-lg-1 col-md-6 col-sm-6"></div>
				</div>
			</nav>
		</div>
	</header>
	<!--================Header Menu Area =================-->