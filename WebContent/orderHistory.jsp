<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	} //学部が選択されてない場合、学部選択画面へ転送
		//if (session.getAttribute("viewDataArray") == null) {
		//response.sendRedirect("selectFaculty.jsp");
		//}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文履歴</title>
</head>
<body>
<ul>
	<li>a</li>
	<li>b</li>
	<li>c</li>
</ul>
	<a class="btn" href="HomeStudent">戻る</a>
</body>
</html>