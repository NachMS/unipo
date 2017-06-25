<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,models.Order,models.OrderDAO,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目情報編集</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/editCourse.css">
</head>
<body>
    <div class="title">
        科目情報編集
    </div>
    <form action="">
        <section>
            <span class="radio__name3">科目名</span>
            <input placeholder="電大演習A" class="form" type="text" name="" value="">
        </section>
        <section>
            <span class="radio__name3">教員名</span>
            <input placeholder="電大太郎" class="form" type="text" name="" value="">
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
            <span class="radio__name">学期</span>
            <input type="radio" name="firstSecound" value="first" id="radio11" />
            <label for="radio11" class="radio longname">前期</label>

            <input type="radio" name="firstSecound" value="secound" id="radio12" />
            <label for="radio12" class="radio longname">後期</label>
        </section>

        <section>
            <span class="radio__name2">開講曜日</span>
            <input type="radio" name="DOW" value="MON" id="radio13" />
            <label for="radio13" class="radio2">月</label>

            <input type="radio" name="DOW" value="TUE" id="radio14" />
            <label for="radio14" class="radio2">火</label>

            <input type="radio" name="DOW" value="WED" id="radio15" />
            <label for="radio15" class="radio2">水</label>

            <input type="radio" name="DOW" value="THU" id="radio16" />
            <label for="radio16" class="radio2">木</label>

            <input type="radio" name="DOW" value="FRI" id="radio17" />
            <label for="radio17" class="radio2">金</label>
        </section>
        <section>
            <span class="radio__name">時限</span>
            <input type="radio" name="time" value="1" id="radio18" />
            <label for="radio18" class="radio2">1</label>

            <input type="radio" name="time" value="2" id="radio19" />
            <label for="radio19" class="radio2">2</label>

            <input type="radio" name="time" value="3" id="radio20" />
            <label for="radio20" class="radio2">3</label>

            <input type="radio" name="time" value="4" id="radio21" />
            <label for="radio21" class="radio2">4</label>

            <input type="radio" name="time" value="5" id="radio22" />
            <label for="radio22" class="radio2">5</label>
        </section>
        <input class="submit" type="button" onclick="submit();" name="" value="登録">
    </form>
</body>
</html>