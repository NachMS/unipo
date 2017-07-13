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
				//float eval = 0;
				for (Textbook tb : suggestedTextbooks) {
					Course course = tb.getCourse();
					int eval = 100*tb.getLikes() / (tb.getLikes() + tb.getDislikes()) ;
			%>
			<input type="checkbox" name="textbookID"
				value="<%=tb.getTextbookID()%>" checked id="checkbox<%=i%>" /> <label
				for="checkbox<%=i%>" class="checkbox"> <span class="period"><%=course.getDayOfWeekKanji() + course.getHour()%></span><span
				class="course"><%=course.getName()%></span><span class="textbook">「<%=tb.getName()%>」
			</span><span class="price">¥<%=tb.getPrice()%>&emsp;<%=eval%>%
			</span>
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
			<input type="submit" class="Button__next next" value="次へ" />
		</div>

	</form>
</body>
</html>