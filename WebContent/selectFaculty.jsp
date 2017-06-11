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
<title>Insert title here</title>
    <!-- Normalize.css -->
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/selectFaculty.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
	<a class="btn" href="SelectFaculty">戻る</a>
	<div class="message">学部を選んでください</div>
	<div class="cards-container">
		<a class="card animated zoomIn S" href="/unipo/SelectDepartment?selection=S">システムデザイン工学部</a>
		<a class="card animated zoomIn F" href="/unipo/SelectDepartment?selection=F">未来科学部</a>
		<a class="card animated zoomIn E" href="/unipo/SelectDepartment?selection=E">工学部</a>
	</div>
</body>
</html>