<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/nav.jsp"%>
<% request.setCharacterEncoding("UTF-8"); %>
<section class="contact_area">
	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<h2>Update</h2>
				<hr />
				<form class="row contact_form" action="/blog/user?cmd=update" method="post" onsubmit="return validateCheck()">
					<input type="hidden" name="id" value="${sessionScope.user.id}"/>
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" value="${sessionScope.user.username}" name="username" placeholder="아이디를 입력하세요." readonly maxlength="20">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요." required="required" maxlength="20">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="동일한 비밀번호를 입력하세요." required="required" maxlength="20">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="email" class="form-control" value="${sessionScope.user.email}" name="email" placeholder="이메일을 입력하세요." readonly maxlength="40">
								<c:choose>
                                 <c:when test="${sessionScope.user.emailAuth == 1}">
                                    	<span style="font-size: 12px; color: red; position:absolute; top:7px; right:30px;">이메일 인증 완료 </span>
                                 </c:when>
                                 <c:otherwise>
                                    	<span style="font-size: 12px; color: red; position:absolute; top:7px; right:30px;">이메일 인증 미완료 </span>
                                 </c:otherwise>
                              </c:choose>
						</div>
					</div>
					<!-- 도로명주소 시작 -->
					<div class="col-md-10">
						<div class="form-group">
							<!-- readonly는 칸에 작성 할 수 없게 하는 것 -->
							<input type="text" class="form-control" id="roadFullAddr" name="address" placeholder="도로명 주소가 자동 입력됩니다." required="required" readonly>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class=" form-control" onClick='goPopup()'>검색</a>
						</div>
					</div>
					<!-- 도로명주소 끝 -->
					
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Update</button>
					</div>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
</section>
<div style="margin-bottom: 60px"></div>
<script>
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}

	//주소 입력 버튼을 클릭하면 콜백된다.
	function jusoCallBack(roadFullAddr) {
		//document.form.roadFullAddr.value = roadFullAddr;  이건 이제 옛날방식이라서 쓰진않는다
		//요즘 방식으로 수정
		var juso = document.querySelector('#roadFullAddr');
		juso.value = roadFullAddr;

	}

	function validateCheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;
		var roadFullAddr = document.querySelector('#roadFullAddr').value;

		if (roadFullAddr == '') {
			alert('주소를 입력하세요.');
			return false;
		}

		if (password === passwordCheck) {
			console.log('비밀번호가 동일합니다.');
			return true;
		} else {
			console.log('비밀번호가 다릅니다.');
			alert('비밀번호가 동일하지 않습니다. <br> 다시 입력해주세요.');
			return false;
		}
	}
</script>
<%@ include file="/include/footer.jsp"%>