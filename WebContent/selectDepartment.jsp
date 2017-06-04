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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学科選択</title>
<link rel="stylesheet" href="selectDepartment.css" />
</head>
<body>
	<a class="btn" href="selectFaculty.jsp">戻る</a>
	</header>
	<div class="message">学科を選んでください</div>
	<main> <%
 	String[][] array = (String[][]) session.getAttribute("viewDataArray");
 	for (int i = 0; i < array.length; i++) {
 %> <a class="card animated zoomIn"
		href="SelectGradeController?selection=<%=array[i][0]%>">
		<div class="card-top"><%=array[i][0]%></div>
		<div class="card-bottom"><%=array[i][1]%></div>
	</a> <%
 	}
 %> </main>
</body>
</html>