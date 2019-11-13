<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/nav.jsp"%>

<body onload="init();">


	<!--================Contact Area =================-->
	<section class="contact_area p_120">
		<div class="container">
			<div class="row">
				<div>
					<img id="img__wrap" />
				</div>
				<div class="col-lg-3"></div>
				<div class="col-lg-6" style="border-style: outset; padding:30px;">
					<h4>Change your profile picture</h4>
					<form action="/blog/user?cmd=imgup&id=${sessionScope.user.id}" method="POST" enctype="Multipart/form-data">
						<!-- username : <input type = "text" name = "username"> <br/> -->
						<input style="font-family: '맑은 고딕';" id="img__input" type="file" name="userProfile" > <br />
						<input style="float:right;" class="btn submit_btn" type="submit" value="전송">
					</form>
				</div>
				<div class="col-lg-3"></div>
			</div>
		</div>
		<br/><br/><br/>
	</section>
	<!--================Contact Area =================-->

	<script src="/blog/js/jquery-3.2.1.min.js"></script>

	<script>
		$("#img__input").on("change", handleImgFile);

		function handleImgFile(e) {
			console.log(e);
			console.log(e.target);
			console.log(e.target.files);
			console.log(e.target.files[0]);
			var f = e.target.files[0];
		}

		if (!f.type.match("image.*")) {
			console.log("이미지 타입이 아닙니다");
			return;
		}

		var reader = new FileReader();

		reader.onload = function(e) {
			console.log(e.target);
			console.log(e.target.result); // 파일 로딩 성공 결과
			$("#img__wrap").attr("src", e.target.result);
		}

		reader.readAsDataURL(f);
	</script>
	<%@ include file="/include/footer.jsp"%>