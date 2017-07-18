<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学年選択</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectGrade.css">
</head>
<body>

	<div class="flex dark">
		<div class="message">学年を選択してください</div>
		<div class="main">
			<span class="block"> <a href="SelectGrade?grade=1"
				class="bttn-dark ${student.faculty}">1</a>

			</span> <span class="block "> <a href="SelectGrade?grade=2"
				class="bttn-dark ${student.faculty}">2</a>

			</span> <span class="block"> <a href="SelectGrade?grade=3"
				class="bttn-dark ${student.faculty}">3</a>
			</span> <span class="block"> <a href="SelectGrade?grade=4"
				class="bttn-dark ${student.faculty}">4</a>
			</span>
		</div>
	</div>
	<a class="back" href="SelectDepartment">戻る</a>
</body>
</html>