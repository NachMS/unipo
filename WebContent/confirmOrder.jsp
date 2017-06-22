<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確定画面</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/confirmOrder.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="message__top">以下の内容で注文を確定します。よろしいですか？</div>
	<div class="message__bottom">
		<span class="message__textbook">購入教科書</span> <span
			class="message__datetime">受け取り日時</span>
	</div>
	<div class="order">

		<div class="order__textbook">
			<div class="textbook">
				<span class="DOW DOW-mon">月１</span> <span class="course__name">電大演習A</span>
				<span class="textbook__name">実学とはなんなのか</span>
			</div>
			<div class="textbook">
				<span class="DOW DOW-mon">月１</span> <span class="course__name">電大演習A</span>
				<span class="textbook__name">実学とはなんなのか</span>
			</div>
			<div class="textbook">
				<span class="DOW DOW-mon">月１</span> <span class="course__name">電大演習A</span>
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
		<a class="btn" href="Home">戻る</a> <a class="btn confirm" href="#">確定</a>
	</div>

</body>
</html>