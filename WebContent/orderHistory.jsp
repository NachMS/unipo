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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<ol>
		<%
			List<Order> list = (List) request.getAttribute("orders");
			for (Order order : list) {
				SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日 hh:mm");
				String formatedOrderDate = sdf.format(order.getOrderDate());
				String formatedReceiveDate = sdf.format(order.getReceiveDate());
		%>

		<li><ul>
				<li>注文日時: <%=formatedOrderDate%></li>
				<li>合計: <%=order.getTotalAmount()%> 円
				</li>
				<li>受け取り日時: <%=formatedReceiveDate%></li>
				<li><a class="btn"
					href="OrderDetail?selection=<%=order.getOrderID()%>">注文詳細</a></li>
			</ul></li>

		<%
			}
		%>
	</ol>
    <div class="TITLE">注文履歴</div>

    <div class="order__card">
        <span class="order__datetime">注文履歴
            <div class="order__datetime__value">2017/04/04</div>
        </span>

        <span class="order__sum">合計
            <div class="order__sum__value">¥&nbsp;20000</div>
        </span>

        <span class="order__receive">受取日時
            <div class="order__receive__value">2017/04/06</div>
        </span>

        <a class="order__detail" href="#">注文詳細</a>
    </div>

	<a class="btn" href="Home">戻る</a>

</body>
</html>