<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学部選択</title>
</head>
<body>
	<ul>
		<li><a href="/unipo/HomeStudent">戻る</a></li>
		<li><a href="/unipo/SelectDepartment?selection=S">システムデザイン工学部</a></li>
		<li><a href="/unipo/SelectDepartment?selection=F">未来科学部</a></li>
		<li><a href="/unipo/SelectDepartment?selection=E">工学部</a></li>
	</ul>
</body>
</html>