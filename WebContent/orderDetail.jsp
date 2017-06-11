<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.Order"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文詳細</title>
</head>
<body>
	<%
		Order order = (Order) request.getAttribute("order");
	%>
	<li><ul>
			<li>注文日時: <%=order.getOrderDate()%></li>
			<li>合計: <%=order.getTotalAmount()%>円
			</li>
			<li>受け取り日時: <%=order.getReceiveDate()%></li>

		</ul></li>

	<a class="btn" href="OrderHistory">戻る</a>
	<a class="btn" href="orderDetail">注文削除</a>
	<a class="btn" href="CourseList">注文変更</a>
	<a class="btn" href="SelectDate">受け取り日時変更</a>
</body>
</html>