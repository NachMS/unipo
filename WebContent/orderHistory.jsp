<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<%
	String[] message = { "neutral", "メッセージはありません。" };
	if (request.getAttribute("message") != null) {
		message = (String[]) request.getAttribute("message");
	}
%>
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
	<div class="message <%=message[0]%>">
		メッセージ：<%=message[1]%></div>
	<%
		String[][] orders = (String[][]) request.getAttribute("orders");
		for (int i = 0; i < orders.length; i++) {
	%>

	<div class="order__card">
		<span class="order__datetime">注文日時
			<div class="order__datetime__value"><%=orders[i][0]%></div>
		</span> <span class="order__sum">合計
			<div class="order__sum__value">
				¥&nbsp;<%=orders[i][1]%></div>
		</span> <span class="order__receive">受取日時
			<div class="order__receive__value"><%=orders[i][2]%></div>
		</span> <a class="order__detail"
			href="OrderDetail?selection=<%=orders[i][3]%>">注文詳細</a>
	</div>

	<%
		}
	%>
	<a class="btn" href="Home">戻る</a>

</body>
</html>