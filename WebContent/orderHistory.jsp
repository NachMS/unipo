<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文履歴</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/orderHistory.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="TITLE">注文履歴</div>
	<%
		List<Order> list = (List) request.getAttribute("orders");
		for (Order order : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日 hh:mm");
			String formatedOrderDate = sdf.format(order.getOrderDate());
			String formatedReceiveDate = sdf.format(order.getReceiveDate());
	%>

	<div class="order__card">
		<span class="order__datetime">注文日時
			<div class="order__datetime__value"><%=formatedOrderDate%></div>
		</span> <span class="order__sum">合計
			<div class="order__sum__value">
				¥&nbsp;<%=order.getTotalAmount()%></div>
		</span> <span class="order__receive">受取日時
			<div class="order__receive__value"><%=formatedReceiveDate%></div>
		</span> <a class="order__detail"
			href="OrderDetail?selection=<%=order.getOrderID()%>">注文詳細</a>
	</div>

	<%
		}
	%>
	<a class="btn" href="Home">戻る</a>

</body>
</html>