<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学科選択</title>
  <!-- Normalize.css -->
    <link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/selectDepartment.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

</head>
<body>
	<a class="btn" href="SelectFaculty">戻る</a>
	<div class="message">学科を選んでください</div>
	<div class="cards-container">
		<%	//facultyには"F","E","A"のどれか、選択されたものが格納されています。
			String faculty = (String) request.getAttribute("faculty");
			String[][] array = (String[][]) request.getAttribute("viewDataArray");
			for (int i = 0; i < array.length; i++) {
		%>
		<a class="card <%=faculty%> animated zoomIn" href="SelectGrade?selection=<%=array[i][0]%>">
			<div class="card-top"><%=array[i][0]%></div>
			<div class="card-bottom"><%=array[i][1]%></div>
		</a>
		<%
			}
		%>
	</div>
</body>
</html>
