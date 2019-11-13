<%@page import="java.util.List"%>
<%@page import="com.cos.dao.BoardDao"%>
<%@page import="com.cos.model.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/nav.jsp"%>
<%@page import="com.cos.action.board.BoardListAction"%>
<!--================Blog Area =================-->
<section class="blog_area p_120 ">
	<div class="container ">
		<div class="row ">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
				<div class="blog_left_sidebar">
					<!-- var : 임의 -->
					<c:forEach var="board" items="${boards}">
						<article class="blog_style1">
							<div class="blog_img" style="width: 100%; height: 270px; overflow: hidden;">
								<img style="width: 100%;" class="img-fluid" src="${board.previewImg}" alt="">
							</div>
							<div class="blog_text">
								<div class="blog_text_inner">
									<div class="cat">
										<a class="cat_btn" href="#">${board.user.username}</a> <a href="#"><i class="fa fa-calendar" aria-hidden="true"></i>${board.createDate}</a> <a href="#"><i class="fa fa-comments-o"
											aria-hidden="true"></i>${board.readCount}</a>
									</div>
									<a href="#"><h4>${board.title}</h4></a>
									<div
										style="display: -box; -box-orient: vertical; text-align: left; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.2; height: 2.4em; -line-clamp: 2; margin-bottom: 20px; word-break: break-all">
										${board.content}</div>
									<a class="blog_btn" href="/blog/board?cmd=detail&id=${board.id}">Read More</a>
								</div>
							</div>
						</article>
					</c:forEach>
					<!-- 페이징 -->
					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination" id="pageList">
						
							<c:if test="${param.page > 1}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page - 1}&search=${search}" class="page-link" aria-label="Previous"> &nbsp;Previous <span aria-hidden="true"> <span class="lnr lnr-chevron-left"></span></span></a></li>
							</c:if>
							
							<c:forEach var="i" begin="${start}" end="${end}" step="1">
								<li class="page-item"><a href="/blog/board?cmd=list&page=${i}&search=${search}" class="page-link" aria-label="Next">${i} </a></li>
							</c:forEach>
							
							<c:if test="${param.page < totalPage}">
							<li class="page-item"><a href="/blog/board?cmd=list&page=${param.page + 1}&search=${search}" class="page-link" aria-label="Next"><span aria-hidden="true"> <span class="lnr lnr-chevron-right"></span></span>Next page </a></li>
							</c:if>
						</ul>
					</nav>


				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
</section>
<!--================Blog Area =================-->

<%@include file="/include/footer.jsp"%>

</body>
</html>