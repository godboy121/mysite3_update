<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js">

</script>

<script>
$(function(){
	$("#join-form").submit(function(){
		if($("#name").val()==""){
			
			alert("이름은 필수 입력 항목입니다");
			$("#name").focus();
			return false;
		}
		if($("#email").val()==""){
			alert("이메일은은 필수 입력 항목입니다");
			$("#email").focus();
			return false;
		}
		
		if($("#checked").is(":visible")==false){
			alert("이메일 중복체크를 해야합니다");
			return false;
		}
		if($("input[type='password']").val()==""){
			alert("비밀번호는 필수 입력사항입니다");
			$("input[type='password']").focus();
			return false;
		}
		if($("#agree-prov").is(":checked")==false){
			alert("약관동의는는 필수 입력사항입니다");
			return false;
		}
		
	});
	$("#btn-check").click(function(){
		var email=$("#email").val();
		if(email==""){
			return ;
		}
		$.ajax({
			url:"/mysite3/api/user?a=checkemail&email="+email,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result!="success"){
					console.log(response.message);
					return ;
				}
				else if(response.data=="exist"){
					alert("이미 존재하는 이메일입니다 다른 이메일을 사용해보세요");
					$("#email").val("").focus();
					return ;
				}
				else if(response.data=="not exist"){
					$("#checked").show();
					$("#btn-check").hide();
					return ;
				}
				
				
			},
		
			error: function(jqXHR,status,e){
				console.error(status+":"+e);
				
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/head.jsp"></jsp:include>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite3/user?a=join">
				    
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<img src="${pageContext.request.contextPath }/assets/images/a.png" id="checked" style="display:none">
					<input type="button" id="btn-check" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	<div id="dialog" title="Basic dialog" style="display:none">
  <p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>
</body>
</html>