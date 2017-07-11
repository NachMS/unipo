<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="models.Order,models.Textbook, java.util.ArrayList"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教科書評価</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/evaluateTextbook.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<%
		ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
	%>
	<span class="message__top">教科書評価</span>

	<%
		ArrayList<Integer> textbookIDs = new ArrayList<Integer>();
		int textbooksNum = 0;
		int i = 0;
		for (Order order : orders) {
			textbooksNum += order.getTextbooks().size();
			if(order.isCancelFlag()==true){
				continue;
			}
			for (Textbook textbook : order.getTextbooks()) {
				//教科書重複防止
				if (!textbookIDs.contains(textbook.getTextbookID())) {
					textbookIDs.add(textbook.getTextbookID());
				} else {
					continue;
				}
	%>
	<div class="Subject">
		<span class="Subject__detail"> <span class="period period-tue"><%=textbook.getCourse().getDayOfWeekKanji()%><%=textbook.getCourse().getHour()%></span>&emsp;
			<span class="name"><%=textbook.getCourse().getName()%></span> <span
			class="textbook">「<%=textbook.getName()%>」 </span>
		</span>
		<span class="evaluate" textbookID="<%=textbook.getTextbookID()%>">
			<a id="good<%=i%>" val="like">
			<span style="user-select: none;" class="smile">
				<svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg"><path d="M11.99 2C6.47 2 2 6.47 2 12s4.47 10 9.99 10S22 17.53 22 12 17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8zm1-10.06L14.06 11l1.06-1.06L16.18 11l1.06-1.06-2.12-2.12zm-4.12 0L9.94 11 11 9.94 8.88 7.82 6.76 9.94 7.82 11zM12 17.5c2.33 0 4.31-1.46 5.11-3.5H6.89c.8 2.04 2.78 3.5 5.11 3.5z" /><path d="M0 0h24v24H0z" fill="none" /></svg>
			<span style="color:white;" id="likes<%=i%>"><%=textbook.getLikes()%></span>
			</span>

		</a> <a id="bad<%=i%>" val="dislike">
		<span style="user-select: none;" class="sad"> <svg
						fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48"
						xmlns="http://www.w3.org/2000/svg">
                        <path d="M0 0h24v24H0z" fill="none" />
                        <circle cx="15.5" cy="9.5" r="1.5" />
                        <circle cx="8.5" cy="9.5" r="1.5" />
                        <path
							d="M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8zm0-6c-2.33 0-4.32 1.45-5.12 3.5h1.67c.69-1.19 1.97-2 3.45-2s2.75.81 3.45 2h1.67c-.8-2.05-2.79-3.5-5.12-3.5z" />
                    </svg>
                    <span style="color:#FFF;" id="dislikes<%=i%>"><%=textbook.getDislikes()%></span>
			</span>
		</a>
		</span>
	</div>
	<%
		i++;
			}
		}
	%>

	<div class="Button__back">
		<a class="back" href="Home">戻る</a>
	</div>
	<script type="text/javascript">
		var n =
	<%=textbooksNum%>
		;
		var likeCount = 0;
		var dislikeCount = 0
		var i = 0;
		var evaluation = [ "good", "bad" ];
		function evaluation_func() {
			//var ii = i;
			var jj = j;
			var id = "#" + evaluation[jj] + i;
			var likesFieldId = "#likes" + i;
			var dislikesFieldId = "#dislikes" + i;
			$(id).click(function() {
				if (evaluation[jj] == ("good")) {
					likeCount = $(likesFieldId).text();
					likeCount++;
					console.log($(likesFieldId).text());
					$(likesFieldId).text(likeCount);
				} else {
					dislikeCount = $(dislikesFieldId).text();
					dislikeCount++;
					console.log($(dislikesFieldId).text());
					$(dislikesFieldId).text(dislikeCount);
				}
				$.post("EvaluateTextbook", {
					textbookID : $(this).parent().attr('textbookID'),
					val : $(this).attr('val')
				});
			});
		}
		for (var j = 0; j <= evaluation.length; j++) {
			for (var i = 0; i < n; i++) {
				evaluation_func();
			}
		}
	</script>
</body>
</html>