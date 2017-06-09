<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
	//学部が選択されてない場合、学部選択画面へ転送
	if (session.getAttribute("viewDataArray") == null) {
		response.sendRedirect("selectFaculty.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学科選択</title>
<link rel="stylesheet" href="css/selectDepartment.css" />
</head>
<body>
	<a class="btn" href="SelectFaculty">戻る</a>
	<div class="message">学科を選んでください</div>
	<div class="cards-container">
		<%
			String[][] array = (String[][]) session.getAttribute("viewDataArray");
			for (int i = 0; i < array.length; i++) {
		%>
		<a class="card animated zoomIn"
			href="SelectGrade?selection=<%=array[i][0]%>">
			<div class="card-top"><%=array[i][0]%></div>
			<div class="card-bottom"><%=array[i][1]%></div>
		</a>
		<%
			}
		%>
	</div>
</body>
</html>
