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
		<span class="message__textbook">購入教科書&emsp;お支払い金額￥<%=String.format("%,3d", order.getTotalAmount())%></span>
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
	<div class="Button">
		<a class="btn" href="<%=backLink%>">戻る</a> <a class="btn confirm"
			href="ConfirmOrder?act=confirm">確定</a>
	</div>

</body>
</html>