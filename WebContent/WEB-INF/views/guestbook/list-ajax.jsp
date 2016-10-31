<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute( "newLine", "\n" );
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var page=1;

$(function(){
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		//ajax insert
		
	});
	$(window).scroll(function(){
		var $window=$(this);
		var scrollTop=$window.scrollTop();
		var windowHeight=$window.height();
		var documentHeight=$(document).height();
		console.log(scrollTop+":"+windowHeight+":"+documentHeight);
	});
	$("#btn-fetch").click(function(){
		$.ajax({
			url:"/mysite3/guestbook/ajax?a=ajax-list&p="+page++,
			type:"get",
			dataType:"json",
			data:"",
			success:function(response){
				if(response.result!="success"){
					console.error(response.message);
					isEnd=true;
					return;
				}
				$(response.data).each(function(index,vo){//data 받아오기 
					var htmls=
						"<li>"+
					"<table>"+
						"<tr>"+
							"<td>"+vo.no+"</td>"+
							"<td>"+vo.name+"</td>"+
							"<td>"+vo.regDate+"</td>"+
							"<td><a href=''>삭제</a></td>"+
						"</tr>"+
						"<tr>"+
							"<td colspan=4>"+vo.context+"</td>"+
						"</tr>"+
					"</table>"+
					"<br>"+
				"</li>";
				$("#list-guestbook").append(htmls);//갯수만큼 계속 추가 ul을 아이디로 받고 

				
				

					console.log(index+":"+vo);
				});
				
				
				if(response.data.length<5){
					isEnd=true;
					$("#btn-fetch").prop("disabled",true);
				}
			},
			error:function(jqXHR,staus,e){
				console.error(status+":"+e);
			}
		})
	})
})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/head.jsp" />
		<div id="content">
			<div id="guestbook">
				<form id="add-form" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="passwd"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="context" id="context"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">
				
				</ul>
				<button id="btn-fetch" "margin-top:20px;">가져오기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>