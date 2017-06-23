<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生アカウント編集</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/editStudentAccout.css">
</head>
<body>
    <div class="title">
        学生アカウント編集
    </div>
    <form action="">
        <section>
            <span class="radio__name2">学籍番号</span>
            <input placeholder="15FI000" class="form" type="text" name="" value="">
        </section>
        <section>
            <span class="radio__name">氏名</span>
            <input placeholder="電大太郎" class="form" type="text" name="" value="">
        </section>

        <section>
            <span class="radio__name">学部</span>
            <input type="radio" name="department" value="S" id="radio01" />
            <label for="radio01" class="radio longname">シスデザ</label>

            <input type="radio" name="department" value="F" id="radio02" />
            <label for="radio02" class="radio longname">未来科学部</label>

            <input type="radio" name="department" value="E" id="radio03" />
            <label for="radio03" class="radio longname">工学部</label>
        </section>
        <section>
            <span class="radio__name">学科</span>
            <input type="radio" name="facluty" value="FA" id="radio04" />
            <label for="radio04" class="radio">FA</label>

            <input type="radio" name="facluty" value="FI" id="radio05" />
            <label for="radio05" class="radio">FI</label>

            <input type="radio" name="facluty" value="FR" id="radio06" />
            <label for="radio06" class="radio">FR</label>
        </section>
        <section>
            <span class="radio__name">学年</span>
            <input type="radio" name="hoge" value="1" id="radio07" />
            <label for="radio07" class="radio2">1</label>

            <input type="radio" name="hoge" value="2" id="radio08" />
            <label for="radio08" class="radio2">2</label>

            <input type="radio" name="hoge" value="3" id="radio09" />
            <label for="radio09" class="radio2">3</label>

            <input type="radio" name="hoge" value="4" id="radio10" />
            <label for="radio10" class="radio2">4</label>
        </section>
        <input class="submit" type="button" onclick="submit();" name="" value="保存">
    </form>
</body>
</html>