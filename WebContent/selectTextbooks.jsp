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
					int eval = 100 * tb.getLikes() / (tb.getLikes() + tb.getDislikes());
			%>
			<input type="checkbox" name="textbookID" value="<%=tb.getTextbookID()%>" checked id="checkbox<%=i%>" />
				<label for="checkbox<%=i%>" class="checkbox">
				<span class="period"><%=course.getDayOfWeekKanji() + course.getHour()%></span>
				<span class="course"><%=course.getName()%></span>
				<span class="textbook">「<%=tb.getName()%>」</span>
				<span class="price">¥<%=tb.getPrice()%><br>
					<svg fill="#FFFFFF" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M0 0h24v24H0z" fill="none" /><path d="M1 21h4V9H1v12zm22-11c0-1.1-.9-2-2-2h-6.31l.95-4.57.03-.32c0-.41-.17-.79-.44-1.06L14.17 1 7.59 7.59C7.22 7.95 7 8.45 7 9v10c0 1.1.9 2 2 2h9c.83 0 1.54-.5 1.84-1.22l3.02-7.05c.09-.23.14-.47.14-.73v-1.91l-.01-.01L23 10z" /></svg><%=eval%>%
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