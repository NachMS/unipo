<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="models.Order, java.text.SimpleDateFormat,java.util.Date,java.text.DateFormat,java.util.Locale"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文詳細</title>
</head>
<body>
	<%
		Order order = (Order) request.getAttribute("order");
		int num = (int) request.getAttribute("num");
	%>
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日 hh:mm");
		String formatedOrderDate = sdf.format(order.getOrderDate());
		String formatedReceiveDate = sdf.format(order.getReceiveDate());
		Date date1 = sdf.parse(formatedOrderDate);
		Date date2 = sdf.parse(formatedReceiveDate);
		DateFormat weekFormat1 = new SimpleDateFormat("E", Locale.JAPANESE);
		DateFormat dateFormat1 = new SimpleDateFormat("d", Locale.JAPANESE);
		DateFormat weekFormat2 = new SimpleDateFormat("EEEE", Locale.JAPANESE);
		DateFormat dateFormat2 = new SimpleDateFormat("dd", Locale.JAPANESE);
		String weekStr1 = weekFormat1.format(date1);
		String dateStr1 = dateFormat1.format(date1);
		String weekStr2 = weekFormat2.format(date2);
		String dateStr2 = dateFormat2.format(date2);
	%>
	<li><ul>
			<li>注文日時: <%=formatedOrderDate%></li>
			<li>合計: <%=order.getTotalAmount()%>円
			</li>
			<li><%="受取日:" + dateStr1%>
			<li><%=weekStr1%>
			<li>
			<li>受け取り日時: <%=formatedReceiveDate%></li>
			<li><%=dateStr2%>
			<li><%=weekStr2%>
		</ul></li>

	<a class="btn" href="OrderHistory">戻る</a>
	<a class="btn" href="CancelOrder?selection=<%=num%>">注文削除</a>
	<a class="btn" href="CourseList">注文変更</a>
	<a class="btn" href="SelectDate">受け取り日時変更</a>
</body>
</html>