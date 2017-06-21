<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="models.Order,models.Textbook,java.text.SimpleDateFormat,java.util.Date,java.text.DateFormat,java.util.Locale,java.util.List"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文詳細</title>
    <!-- Normalize.css -->
    <link rel="stylesheet" href="normalize.css">
    <link rel="stylesheet" type="text/css" href="css/orderDetail.css">
</head>
<body>
	<%
		Order order = (Order) request.getAttribute("order");
		int num = (int) request.getAttribute("num");
		List<Textbook> textbooks = (List) request.getAttribute("textbooks");
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
			<li>購入教科書:</li>
			<%
				for (Textbook textbook : textbooks) {
			%>
			<li><%=textbook.getName()%></li>
			<%
				}
			%>
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

	<div class="message__top">
        注文詳細
    </div>

    <div class="order__card">
        <span class="order__date">注文日時&nbsp;
            <span class="order__datetime__value">2017/04/04</span>
        </span>

        <span class="order__sum">合計
            <span class="order__sum__value">¥&nbsp;20000</span>
        </span>
    </div>

    <div class="message__bottom">
        <span class="message__textbook">購入教科書</span>
        <span class="message__datetime">受け取り日時</span>
    </div>
    <div class="order">

        <div class="order__textbook">
            <div class="textbook">
                <span class="DOW DOW-mon">月１</span>
                <span class="course__name">電大演習A</span>
                <span class="textbook__name">実学とはなんなのか</span>
            </div>
            <div class="textbook">
                <span class="DOW DOW-mon">月１</span>
                <span class="course__name">電大演習A</span>
                <span class="textbook__name">実学とはなんなのか</span>
            </div>
            <div class="textbook">
                <span class="DOW DOW-mon">月１</span>
                <span class="course__name">電大演習A</span>
                <span class="textbook__name">実学とはなんなのか</span>
            </div>
        </div>

        <div class="order__datetime">
            <div class="date__top L">26</div>
            <div class="date__middle">木</div>
            <div class="date__bottom">12~20</div>
        </div>
    </div>
    <div class="Button">
        <a class="btn order__edit" href="#">注文変更</a>
        <a class="btn datetime__edit" href="#">受取日時変更</a>
        <a class="btn cancel" href="#">注文キャンセル</a>
    </div>
</body>
</html>