<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.Student" %>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>履修科目一覧</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/courseTable.css">
</head>
<body>
	<div class="Button__container">
		<a class="Button--element btn" href="SelectGrade?selection=${student.department}">戻る</a> <a
			class="Button--element next" href="">次へ</a>
		<p class="Button--element MON" href="#">月</p>
		<p class="Button--element TUE" href="#">火</p>
		<p class="Button--element WEN" href="#">水</p>
		<p class="Button--element THU" href="#">木</p>
		<p class="Button--element FRI" href="#">金</p>

		<p class="Button--element NUM1" href="#">1</p>
		<p class="Button--element NUM2" href="#">2</p>
		<p class="Button--element NUM3" href="#">3</p>
		<p class="Button--element NUM4" href="#">4</p>
		<p class="Button--element NUM5" href="#">5</p>

		<a class="Button--element HOVER MON1" href="#">+</a> <a
			class="Button--element HOVER MON2" href="#">+</a> <a
			class="Button--element HOVER MON3" href="#">+</a> <a
			class="Button--element HOVER MON4" href="#">+</a> <a
			class="Button--element HOVER MON5" href="#">+</a> <a
			class="Button--element HOVER TUE1" href="#">+</a> <a
			class="Button--element HOVER TUE2" href="#">+</a> <a
			class="Button--element HOVER TUE3" href="#">+</a> <a
			class="Button--element HOVER TUE4" href="#">+</a> <a
			class="Button--element HOVER TUE5" href="#">+</a> <a
			class="Button--element HOVER WEN1" href="#">+</a> <a
			class="Button--element HOVER WEN2" href="#">+</a> <a
			class="Button--element HOVER WEN3" href="#">+</a> <a
			class="Button--element HOVER WEN4" href="#">+</a> <a
			class="Button--element HOVER WEN5" href="#">+</a> <a
			class="Button--element HOVER THU1" href="#">+</a> <a
			class="Button--element HOVER THU2" href="#">+</a> <a
			class="Button--element HOVER THU3" href="#">+</a> <a
			class="Button--element HOVER THU4" href="#">+</a> <a
			class="Button--element HOVER THU5" href="#">+</a> <a
			class="Button--element HOVER FRI1" href="#">+</a> <a
			class="Button--element HOVER FRI2" href="#">+</a> <a
			class="Button--element HOVER FRI3" href="#">+</a> <a
			class="Button--element HOVER FRI4" href="#">+</a> <a
			class="Button--element HOVER FRI5" href="#">+</a>
	</div>
</body>
</html>