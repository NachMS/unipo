<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, models.Textbook, models.Course"%>
<%
	String[][] dataArray = (String[][]) request.getAttribute("dataArray");
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教科書選択</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectTextbooks.css">
</head>
<body>
	<div class="Button__back">
		<a class="back" href="CourseTable">戻る</a>
	</div>
	<p>購入する教科書にチェックを入れてください</p>
	<form action="SelectTextbooks" method="post">
		<section>

			<%
				int i = 1;
				for (String[] dat : dataArray) {
					String iIn2Digits = String.format("%02d", i); //1→"01"
			%>
			<input type="checkbox" name="textbookID" value="<%=dat[0]%>" checked
				id="checkbox<%=iIn2Digits%>" /> <label
				for="checkbox<%=iIn2Digits%>" class="checkbox"> <span
				class="period"><%=dat[1]%></span>&emsp;<%=dat[2]%> <span
				class="textbook">「<%=dat[3]%>」&emsp;
			</span>
			</label>
			<%
				i++;
				}
			%>
		</section>
		<%
			if (dataArray.length == 0) {
		%>
		<p>このサービスの存在価値がなくなっちまう！頼むから一個はえらんでくれ！</p>
		<%
			}
		%>
		<input type="submit" class="Button__next" value="確定"/>
	</form>
</body>
</html>