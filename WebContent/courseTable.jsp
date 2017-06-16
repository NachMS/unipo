<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.Student" %>
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
		<p class="Button--element MON"">月</p>
		<p class="Button--element TUE">火</p>
		<p class="Button--element WEN">水</p>
		<p class="Button--element THU">木</p>
		<p class="Button--element FRI">金</p>

		<p class="Button--element NUM1">1</p>
		<p class="Button--element NUM2">2</p>
		<p class="Button--element NUM3">3</p>
		<p class="Button--element NUM4">4</p>
		<p class="Button--element NUM5">5</p>
<%
		String[] dayOfWeekStr = {"MON", "TUE", "WEN", "THU", "FRI"};
		for(int dayOfWeek = 1; dayOfWeek<=5; dayOfWeek++) {
			for(int hour = 1; hour<=5; hour++) {
%>
				<a class="Button--element HOVER <%=dayOfWeekStr[dayOfWeek-1]%><%=hour%>"
				 href="SelectCourse?dayOfWeek=<%=dayOfWeek%>&hour=<%=hour%>">＋</a>
<%
			}
 		}
%>

	</div>
</body>
</html>