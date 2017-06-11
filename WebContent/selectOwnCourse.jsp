<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//未ログインの場合ログイン画面ヘ転送
	if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>教科書選択</title>
    <!-- Normalize.css -->
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/selectOwnCourse.css">
</head>
<body>
    <form action="">
      <section>
        <p>履修している科目を選んでください</p>
        <input type="radio" name="hoge" value="科目名１" id="radio01" />
        <label for="radio01" class="radio">
            <span class="period">火１</span>&emsp;科目名１<span class="teacher">丹羽保一郎&emsp;</span>
        </label>

        <input type="radio" name="hoge" value="科目名２" id="radio02" />
        <label for="radio02" class="radio">
            <span class="period">火１</span>&emsp;科目名２<span class="teacher">丹羽保次郎&emsp;</span>
        </label>

        <input type="radio" name="hoge" value="科目名３" id="radio03" />
        <label for="radio03" class="radio">
            <span class="period">火１</span>&emsp;科目名３<span class="teacher">丹羽保三郎&emsp;</span>
        </label>
      </section>
    </form>
        <div class="Button">
            <a class="next" href="">次へ</a>
    </div>
</body>
</html>