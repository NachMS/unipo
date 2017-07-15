<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>受け取り日時変更</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/confirmNewDatetime.css">
</head>
<body>
	<div class="message__top">
        以下の内容で受取日時を変更します。よろしいですか？
    </div>
    <div class="message__bottom">
        <span class="message__datetime">受け取り日時</span>
    </div>
    <div class="order">
        <span class="order__datetime">
            <div class="date__top L">${before.receiveDateInt}</div>
            <div class="date__middle">${before.receiveDayOfWeekKanji}</div>
            <div class="date__bottom">${before.receiveHour}-${before.receiveHour+1}</div>
        </span>
        <span class="arrow">
            <svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 0h24v24H0z" fill="none"/>
                <path d="M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z"/>
            </svg>
        </span>
        <span class="order__datetime">
            <div class="date__top L">${after.receiveDateInt}</div>
            <div class="date__middle">${after.receiveDayOfWeekKanji}</div>
            <div class="date__bottom">${after.receiveHour}-${after.receiveHour+1}</div>
        </span>
    </div>
    <div class="Button">
        <a class="btn " href="SelectDatetime">戻る</a>
        <a class="btn confirm" href="ConfirmNewDatetime?confirm">確定</a>
    </div>
</body>
</html>