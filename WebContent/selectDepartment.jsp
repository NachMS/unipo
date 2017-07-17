<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学科選択</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/selectDepartment.css" />


</head>
<body>
    <div class="flex dark">
        <div class="message">学科を選択してください</div>
        <div class="main">
        <%
			//facultyには"F","E","A"のどれか、選択されたものが格納されています。
			String faculty = (String) request.getAttribute("faculty");
			String[][] array = (String[][]) request.getAttribute("viewDataArray");
			for (int i = 0; i < array.length; i++) {
		%>
            <span class="block">
                <a href="SelectDepartment?department=<%=array[i][0]%>" class="bttn-dark <%=faculty%>"><%=array[i][0]%></a>
                <div class="description"><%=array[i][1]%></div>
            </span>
		<%
			}
		%>


        </div>

    </div>

    <a class="back" href="SelectFaculty?reselect">戻る</a>
</body>
</html>
