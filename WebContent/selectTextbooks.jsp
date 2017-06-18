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
    <link rel="stylesheet" type="text/css" href="css/selectTextbooks.css">
</head>
<body>
  <div class="Button__back">
          <a class="back" href="">戻る</a>
  </div>
    <p>購入する教科書にチェックを入れてください</p>
    <form action="">
      <section>
        <input type="checkbox" name="hoge" value="科目名１" checked id="checkbox01" />
        <label for="checkbox01" class="checkbox">
            <span class="period">火１</span>&emsp;科目名１
            <span class="textbook">「教科書名１」&emsp;</span>
        </label>

        <input type="checkbox" name="hoge" value="科目名２" checked id="checkbox02" />
        <label for="checkbox02" class="checkbox">
            <span class="period">火１</span>&emsp;科目名２
            <span class="textbook">「教科書名２」&emsp;</span>
        </label>

        <input type="checkbox" name="hoge" value="科目名３" checked id="checkbox03" />
        <label for="checkbox03" class="checkbox">
            <span class="period">火１</span>&emsp;科目名３
            <span class="textbook">「教科書名３」&emsp;</span>
        </label>
      </section>
    </form>
    <div class="Button__next">
            <a class="next" href="">確定</a>
    </div>
</body>
</html>