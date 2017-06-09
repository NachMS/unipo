<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<%
		if (session.getAttribute("login") != null && !(Boolean) session.getAttribute("login")) {
			out.println("<p>ユーザ名またはパスワードが違います</p>");
		}
	%>
	<div class="container">
		<form action="Login" method="post">
			<div class="LoginForm ">
				<h1 class="LoginForm__title">UNIPO</h1>

				<div class="LoginForm__student">
					<input type="text" placeholder="STUDENT ID" name="name" />
				</div>

				<div class="LoginForm__password">
					<input type="password" placeholder="PASSWORD" name="pass" />
				</div>

				<div class="LoginForm__submit">
					<input type="submit" value="ログイン" />
				</div>
			</div>
		</form>
	</div>


</body>
</html>
