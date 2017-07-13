<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, models.Textbook, models.Course"%>
<%
	List<Textbook> suggestedTextbooks = (List<Textbook>) request.getAttribute("suggestedTextbooks");
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
				for (Textbook tb : suggestedTextbooks) {
					String iIn2Digits = String.format("%02d", i); //1→"01"
					Course course = tb.getCourse();
			%>
			<input type="checkbox" name="textbookID" value="<%=tb.getTextbookID()%>" checked
				id="checkbox<%=iIn2Digits%>" /> <label
				for="checkbox<%=iIn2Digits%>" class="checkbox"> <span
				class="period"><%=course.getDayOfWeekKanji()+course.getHour()%></span><span class="course"><%=course.getName()%></span><span
				class="textbook">「<%=tb.getName()%>」
			</span><span class="price">¥<%=tb.getPrice()%></span>
			</label>

			<%
				i++;
				}
			%>
		</section>
		<%
			if (suggestedTextbooks.size() == 0) {
		%>
		<p>このサービスの存在価値がなくなっちまう！頼むから一個はえらんでくれ！</p>
		<%
			}
		%>
		<div class="K">
				<input type="submit" class="Button__next next" value="次へ"/>
		</div>

	</form>
</body>
</html>