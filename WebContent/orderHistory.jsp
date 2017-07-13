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
		<svg fill="#FFFFFF" height="24" viewBox="0 0 24 24" width="24"
			xmlns="http://www.w3.org/2000/svg">
    <path
				d="M20 2H4c-1.1 0-1.99.9-1.99 2L2 22l4-4h14c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm-2 12H6v-2h12v2zm0-3H6V9h12v2zm0-3H6V6h12v2z" />
    <path d="M0 0h24v24H0z" fill="none" />
</svg>
		&ensp;
		<%=message[1]%></div>
	<%
		String[][] orders = (String[][]) request.getAttribute("orders");
		for (int i = 0; i < orders.length; i++) {
			String str = "";
			String canceled = "";
			log(orders[i][4]);
			if (orders[i][4] == "true") {
				//continue;
				str = "キャンセル";
				canceled = " canceled";
			}
	%>
	<div class="center">
		<div class="order__card<%=canceled%>"><%=str%>
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
	</div>
	<%
		}
	%>
	<a class="back" href="Home">戻る</a>

</body>
</html>