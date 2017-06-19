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
<title>受け取り日時選択</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectDatetime.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
	<a class="btn" href="SelectFaculty">戻る</a>
    <div class="message">教科書を受け取りたい日時を指定してください</div>
    <div class="cards-container">
        <div class="card animated zoomIn">
            <div class="card__top H" href="#>">1</div>
            <div class="card__bottom">月</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top M" href="#>">2</div>
            <div class="card__bottom">火</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top L" href="#>">3</div>
            <div class="card__bottom">水</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top H" href="#>">4</div>
            <div class="card__bottom">木</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top M" href="#>">5</div>
            <div class="card__bottom">金</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top L" href="#>">6</div>
            <div class="card__bottom">土</div>
        </div>
        <div class="card animated zoomIn">
            <div class="card__top L" href="#>">7</div>
            <div class="card__bottom">日</div>
        </div>
        <div class="Time">
            <section>
                <input type="radio" name="hoge" value="10~12" id="radio01" />
                <label for="radio01" class="radio">10~12</label>

                <input type="radio" name="hoge" value="12~14" id="radio02" />
                <label for="radio02" class="radio">12~14</label>

                <input type="radio" name="hoge" value="14~16" id="radio03" />
                <label for="radio03" class="radio">14~16</label>

                <input type="radio" name="hoge" value="16~18" id="radio04" />
                <label for="radio04" class="radio">16~18</label>
            </section>
        </div>
        <div class="Next__Button">
            <a class="next" href="">科目選択</a>
        </div>
    </div>
    <script type="text/javascript">
        $(".card").click(function() {
            if (!$(this).hasClass("clicked")) {
                $(this).addClass("clicked");
                $(this).siblings().removeClass("clicked");
            }
        });
    </script>
</body>
</html>