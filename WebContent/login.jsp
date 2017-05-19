<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
</head>
<body>
	<%
		if (session.getAttribute("login") != null && !(Boolean) session.getAttribute("login")) {
			out.println("<p>ユーザ名またはパスワードが違います</p>");
		}
	%>
	<p>ログインします</p>
	<form action="LoginController" method="post">
		<dl>
			<dt>ユーザ名</dt>
			<dd>
				<input type="text" name="name" />
			</dd>
			<dt>パスワード</dt>
			<dd>
				<input type="password" name="pass" />
			</dd>
		</dl>
		<button type="submit" name="login">ログイン</button>
	</form>
</body>
</html>