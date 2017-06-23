<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生アカウント管理</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/studentAccoutList.css">
</head>
<body>
	<p>学生アカウント一覧</p>
    <div class="Subjects">
        <div class="Subject">
            <span class="Subject__detail">
            <span class="period">15FI998</span>&emsp;
            <span class="name">山田太郎</span>
            <!-- <span class="textbook">「教科書名１」</span> -->
            </span>
            <span class="evaluate">
            <a href="#">
                <span class="setting">
                    <svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg">
                        <path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </span>
            </a>
            <a href="#">
                <span class="delete">
                    <svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg">
                        <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </span>
            </a>
            </span>
        </div>

        <div class="Subject">
            <span class="Subject__detail">
            <span class="period">15FI999</span>&emsp;
            <span class="name">山田二郎</span>
            <!-- <span class="textbook">「教科書名２」</span> -->
            </span>
            <span class="evaluate">
            <a href="#">
                <span class="setting">
                    <svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg">
                        <path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </span>
            </a>
            <a href="#">
                <span class="delete">
                    <svg fill="#FFFFFF" height="48" viewBox="0 0 24 24" width="48" xmlns="http://www.w3.org/2000/svg">
                        <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
                        <path d="M0 0h24v24H0z" fill="none"/>
                    </svg>
                </span>
            </a>
            </span>
        </div>
    </div>
    <div class="Buttons">
        <a class="add" href="#">+</a>
        <a class="back" href="">戻る</a>
    </div>
</body>
</html>