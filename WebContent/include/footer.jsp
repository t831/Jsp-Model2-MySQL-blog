<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--================ start footer Area  =================-->
<hr>
<div class=" justify-content-center d-flex" style="background-color: #fffffa;">
	<h4>― Popular Posts ―</h4>
</div>
<hr>
<footer class="footer-area p_120">
	<div class="container">
		<div class="row col-lg-12">
			<!--  여기하는중임 -->
			<c:forEach var="board" items="${hotBoards}">
				<div class="col-lg-4">
					<div class="blog_right_sidebar">
						<aside class="popular_post_widget">
							<div class="media post_item">
								<img src="${board.previewImg}" width="130px" height="80px" alt="post">
								<div class="media-body">
									<a href="/blog/board?cmd=detail&id=${board.id}"><h3>${board.title}</h3></a>
									<p>${board.createDate}</p>
								</div>
							</div>
							<div class="br"></div>
						</aside>
					</div>
				</div>
			</c:forEach>
			<!--  여기까지 끝 -->
		</div>
		<div>
			<br>
			<p class="col-lg-12 footer-text text-center">
				<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				Copyright &copy;
				<script>
					document.write(new Date().getFullYear());
				</script>
				All rights reserved | This template is made by <a href="https://colorlib.com" target="_blank">Colorlib</a>
				<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
			</p>
		</div>
	</div>
</footer>
<!--================ End footer Area  =================-->

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/blog/js/jquery-3.2.1.min.js"></script>
<script src="/blog/js/popper.js"></script>
<script src="/blog/js/bootstrap.min.js"></script>
<script src="/blog/js/stellar.js"></script>
<script src="/blog/vendors/lightbox/simpleLightbox.min.js"></script>
<script src="/blog/vendors/nice-select/js/jquery.nice-select.min.js"></script>
<script src="/blog/vendors/isotope/imagesloaded.pkgd.min.js"></script>
<script src="/blog/vendors/isotope/isotope-min.js"></script>
<script src="/blog/vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="/blog/vendors/jquery-ui/jquery-ui.js"></script>
<script src="/blog/js/jquery.ajaxchimp.min.js"></script>
<script src="/blog/js/mail-script.js"></script>
<script src="/blog/js/theme.js"></script>
</body>
</html>