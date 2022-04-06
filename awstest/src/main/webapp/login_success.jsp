<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Forty by HTML5 UP</title>
		<meta charset="UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
	</head>
	<style>
		#Update > ul.actions {
			margin-top:10%;
		}
	</style>
	<body style="text-align: center;">
	
	<%
		MemberDTO info = (MemberDTO)session.getAttribute("info");
	%>
	

		<!-- Wrapper -->
			<div id="wrapper">
				<!-- Menu -->
					<nav id="Update">	
						<ul class="actions vertical">
							<li><h1>환영합니다!</h1></li>
							<li>로그인을 축하합니다.</li>
								<%if(info != null){ %>
									<h1> <%= info.getEmail() %> </h1>
								<%}else{ %>	
									<h1>로그인 한 세션아이디를 출력해주세요</h1>
								<%} %>
							<li><button onclick='location.href="index.html"'>시작하기</button></li>
						</ul>
					</nav>			
			</div>
	</body>
</html>

