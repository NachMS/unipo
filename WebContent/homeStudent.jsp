<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
	//ログアウト後にブラウザの戻るボタンで前の画面に戻らないようにするおまじない
	//ブラウザがこの画面をcacheで保存しないようにしていると思われる
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
    <!-- Normalize.css -->
    <link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/homeStudent.css">
</head>
<body>
            <div class="Button__container">
        <a class="Button--element logout" href="Logout">ログアウト</a>
        <a class="Button--element order" href="SelectFaculty">注文する</a>
        <a class="Button--element history" href="OrderHistory">注文履歴</a>
        <a class="Button--element evaluate" href="#">評価する</a>
    </div>
</body>
</html>
