<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
</head>
<body>
	<%
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("login.jsp");
		}
	%>
	<p>ようこそ ${sessionScope.student.name} さん</p>
	<ul>
		<li><a href="">注文する</a></li>
		<li><a href="">注文履歴</a></li>
		<li><a href="">評価する</a></li>
	</ul>
	<p>
		<a href="LogoutController">ログアウト</a>
	</p>
</body>
</html>