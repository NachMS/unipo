<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学年選択</title>
    <!-- Normalize.css -->
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/selectGrader.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
    <a class="btn" href="SelectFaculty">戻る</a>
    <div class="message">学年を選んでください</div>
    <div class="cards-container">
        <a class="card animated zoomIn" href="">1</a>
        <a class="card animated zoomIn" href="">2</a>
        <a class="card animated zoomIn" href="">3</a>
        <a class="card animated zoomIn" href="">4</a>
    </div>
</body>
</html>