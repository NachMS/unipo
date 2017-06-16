<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<a class="btn" href="Home">戻る</a>
	<div class="message">学部を選んでください</div>
	<div class="cards-container">
		<a class="card animated zoomIn A" href="/unipo/SelectFaculty?faculty=A">システムデザイン工学部</a>
		<a class="card animated zoomIn F" href="/unipo/SelectFaculty?faculty=F">未来科学部</a>
		<a class="card animated zoomIn E" href="/unipo/SelectFaculty?faculty=E">工学部</a>
	</div>
</body>
</html>