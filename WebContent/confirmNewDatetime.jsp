<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${before.receiveDateInt}${before.receiveDayOfWeekKanji}
		${before.receiveHour} - ${before.receiveHour+1} を</p>
	<p>${after.receiveDateInt}${after.receiveDayOfWeekKanji}
		${after.receiveHour} - ${after.receiveHour+1} に変更します。よろしいですか?</p>
	<a href="SelectDatetime">戻る</a>
	<a href="ConfirmNewDatetime?confirm">確定</a>
</body>
</html>