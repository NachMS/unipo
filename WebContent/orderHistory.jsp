<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,models.Order,models.OrderDAO"%>
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
	<ul>
		<%
			OrderDAO dao = new OrderDAO();
			ArrayList<Order> list;
			list = dao.getOrdersByStudentID("15FI001");
			for (Order order : list) {
		%>
		<li><%="注文日時" + order.getOrderDate() + " 合計" + order.getTotalAmount() + " 受け取り日時"
						+ order.getReceiveDate()%></li>
		<%
			}
		%>
		<li>注文日 合計 受取日</li>
		<li>注文日 合計 受取日</li>
	</ul>
	<a class="btn" href="HomeStudent">戻る</a>
</body>
</html>