<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//このページは http://localhost:8080/unipo/(http://150.95.140.199:8080/) にアクセスしたときに呼び出されます。
	//理由はファイル名がindexになっているからです(http://hoge/ = http://hoge/index.jsp)
	if (session.getAttribute("login") != null && (Boolean) session.getAttribute("login")) {
		//ログイン済みの場合はホーム画面へ転送
		response.sendRedirect("Home");
	} else {
		//未ログインの場合ログイン画面ヘ転送
		response.sendRedirect("Login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>転送中</title>
</head>
<body>
	<p>転送します。</p>
	<p>
		転送されない場合は<a href="Login">こちら</a>をクリック。
	</p>
</body>
</html>
