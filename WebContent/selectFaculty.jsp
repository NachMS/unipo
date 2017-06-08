<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><a href="/unipo/HomeStudent">戻る</a></li>
		<li><a href="/unipo/SelectDepartment?selection=s">システムデザイン工学部</a></li>
		<li><a href="/unipo/SelectDepartment?selection=f">未来科学部</a></li>
		<li><a href="/unipo/SelectDepartment?selection=e">工学部</a></li>
	</ul>
</body>
</html>