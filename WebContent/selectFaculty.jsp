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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
	    <div class="flex dark">
        <div class="message">学部を選択してください</div>
        <div class="main">
            <span class="block">
                <a href="/unipo/SelectFaculty?faculty=A" class="bttn-dark A">シスデザ工学部</a>

            </span>

            <span class="block">
                <a href="/unipo/SelectFaculty?faculty=F" class="bttn-dark F">未来科学部</a>

            </span>

            <span class="block">
                <a href="/unipo/SelectFaculty?faculty=E" class="bttn-dark E">工学部</a>
            </span>
        </div>

    </div>

    <a class="back" href="Home">戻る</a>
</body>
</html>