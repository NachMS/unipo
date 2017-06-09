<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文履歴</title>
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
				<li>合計: <%=order.getTotalAmount()%> 円</li>
				<li>受け取り日時: <%=formatedReceiveDate%></li>
			</ul></li>

		<%
			}
		%>
	</ol>

	<a class="btn" href="HomeStudent">戻る</a>
</body>
</html>