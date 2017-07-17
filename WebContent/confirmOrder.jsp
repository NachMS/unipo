<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.Textbook,java.text.SimpleDateFormat"%>
<%
	/**
	* コントローラからのデータの取得
	*/
	Order order = (Order) request.getAttribute("order");
	boolean isChangingOrder = (boolean) request.getAttribute("isChangingOrder");

	List<Textbook> textbooks = order.getTextbooks();
	String message = "以下の内容で注文を確定します。よろしいですか？";
	String backLink = "SelectDatetime";
	if (isChangingOrder) {
		message = "以下の内容で注文を変更します。よろしいですか？";
		backLink = "SelectTextbooks";
	}
	int coopMembersPrice = (int)Math.floor(order.getTotalAmount() * 0.9);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確定画面</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/confirmOrder.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="message__top"><%=message%></div>

	<div class="message__bottom">
		<span class="message__textbook">購入教科書</span>
		<span class="message__datetime">受け取り日時</span>
	</div>
	<div class="order">

		<div class="order__textbook">
			<%
				for (Textbook textbook : textbooks) {
			%>
			<div class="textbook">
				<span class="DOW DOW-<%=textbook.getCourse().getDayOfWeek()%>"><%=textbook.getCourse().getDayOfWeekKanji()%><%=textbook.getCourse().getHour()%></span>
				<span class="course__name"><%=textbook.getCourse().getName()%></span>
				<span class="textbook__name"><%=textbook.getName()%></span> <span
					class="textbook__price">¥<%=String.format("%,3d", textbook.getPrice())%></span>
			</div>
			<%
				}
			%>
		</div>

		<div class="order__datetime">
			<div class="date__top L"><%=order.getReceiveDateInt()%></div>
			<div class="date__middle"><%=order.getReceiveDayOfWeekKanji()%></div>
			<div class="date__bottom"><%=order.getReceiveHour()%>~<%=order.getReceiveHour() + 1%></div>
		</div>
	</div>
	<div class="top">
		<div class="order__card">
			 <span
				class="order__sum">合計金額￥ <%=String.format("%,3d", order.getTotalAmount())%>&emsp;( 生協会員<span
				class="blink"> ￥<%=String.format("%,3d", coopMembersPrice)%></span> )
			</span>
			<span id="advertising"></span>
		</div>
	</div>
	<div class="Button">
		<a class="btn" href="<%=backLink%>">戻る</a> <a class="btn confirm"
			href="ConfirmOrder?act=confirm">確定</a>
	</div>

</body>
<script>
var myVar = setInterval(myTimer, 1000);
var array = [
  '<div class="blink">生協会員なら 10%OFF!!!</div>',
  '<div class="zoom">会員証提示で 10%OFF!!!</div>',
  '<div class="vibrate"> 今すぐ会員になろう!!!</div>'
];
var count = 0;
function myTimer() {
  document.getElementById("advertising").innerHTML = array[count%3];
  count ++;
}
</script>
<style>
#advertising {
	background: yellow;
	padding: 1vw;
}
.blink {
  animation: blink .3s infinite;
}
.zoom {
  animation: zoom .5s infinite;
  color: blue;
}
.vibrate {
  animation: vibrate .1s infinite;
}

@keyframes blink {
  0%, 100% {
    color: white;
  }
  80% {
    color: red;
  }
}

@keyframes zoom {
  0%, 100% {
    transform: scale(1.2, 1.2);
     transform-origin: center;
  }
  50% {
    transform: scale(1, 1);
  }
}

@keyframes vibrate {
  0%, 100% {
    transform: translate(-4px, -2px);
  }
    25% {
    transform: translate(0px, 0px);
  }
  50% {
    transform: translate(2px, 4px);
  }
    75% {
    transform: translate(4px, -4px);
  }
</style>
</html>