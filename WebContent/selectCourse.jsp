<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, models.Course"%>
<%
	List<Course> list = (List<Course>) request.getAttribute("coursesList");
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>履修科目選択</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectCourse.css">
</head>
<body>
	<form action="SelectCourse" method="post">
		<section>
			<p>履修している科目を選んでください</p>
			<%
				if (list.size() == 0) {
			%>
			<p>
				この時間に履修できる科目はありません。<a href="CourseTable">戻る</a>
			</p>
			<%
				}
				int i = 1;
				for (Course course : list) {
					String[] dow = { "月", "火", "水", "木", "金" };
					String iIn2Digits = String.format("%02d", i); //1→"01"
			%>
			<input type="radio" name="courseID" value="<%=course.getCourseID()%>"
				id="radio<%=iIn2Digits%>" /> <label for="radio<%=iIn2Digits%>"
				class="radio"> <span class="period"><%=dow[course.getDayOfWeek() - 1]%><%=course.getHour()%></span>&emsp;<%=course.getName()%><span
				class="teacher"><%=course.getTeacher()%>&emsp;</span>
			</label>
			<%
				i++;
				}
			%>
		</section>
		<input type="submit" class="Button next" value="科目選択" />
	</form>
</body>
</html>