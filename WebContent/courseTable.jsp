<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.Student"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>履修科目一覧</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/courseTable.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<!-- 誰だこんなuncode書いたやつ -->
	<%
		String[][] selectedCourses = (String[][]) request.getAttribute("selectedCourses");
		boolean[][] selectalbeTiles = (boolean[][]) request.getAttribute("selectalbeTiles");
		Student student = (Student) request.getAttribute("student");
		String department = student.getDepartment();
		int grade = student.getGrade();
	%>
	<div class="Button__container">
		<p class="Button--element state"><%=department%>
			<%=grade%>年
		</p>
		<a class="Button--element next" href="SelectTextbooks">次へ</a>
		<p class="Button--element MON">月</p>
		<p class="Button--element TUE">火</p>
		<p class="Button--element WED">水</p>
		<p class="Button--element THU">木</p>
		<p class="Button--element FRI">金</p>
		<a class="Button--element btn" href="SelectFaculty?reselect">再選択</a>

		<p class="Button--element NUM1">1</p>
		<p class="Button--element NUM2">2</p>
		<p class="Button--element NUM3">3</p>
		<p class="Button--element NUM4">4</p>
		<p class="Button--element NUM5">5</p>

		<%
			String[] dayOfWeekStr = {"MON", "TUE", "WED", "THU", "FRI"};
			for (int dayOfWeek = 1; dayOfWeek <= 5; dayOfWeek++) {
				for (int hour = 1; hour <= 5; hour++) {
					String styleClassAttribute;
					String link = "";
					String textShown;
					if (selectedCourses[dayOfWeek - 1][hour - 1] != null) {
						//選択された科目が格納済み
						styleClassAttribute = "stored";
						textShown = selectedCourses[dayOfWeek - 1][hour - 1];
					} else if (selectalbeTiles[dayOfWeek - 1][hour - 1]) {
						textShown = "+";
						styleClassAttribute = "selectable";
						link = "SelectCourse?dayOfWeek=" + dayOfWeek + "&hour=" + hour;
					} else {
						styleClassAttribute = "empty";
						textShown = "選択可能な科目はありません";
					}
		%>
		<div class="Button--element <%=styleClassAttribute%>"
			id="<%=dayOfWeekStr[dayOfWeek - 1]%><%=hour%>" href="<%=link%>">
			<div class="close-button"
				id="<%=dayOfWeekStr[dayOfWeek - 1]%><%=hour%>close">
				<!-- 閉じるボタンxのsvg -->
				<svg fill="#FFFFFF" height="26" viewBox="0 0 24 24" width="26"
					xmlns="http://www.w3.org/2000/svg">
                        <path
						d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z" />
                        <path d="M0 0h24v24H0z" fill="none" />
                    </svg>
			</div><%=textShown%>
		</div>
		<%
			}
			}
		%>

	</div>
	<!-- 動いているのでご容赦ください -->
	<script type="text/javascript">
		var i = 1;
		var id = "#MON";
		var array = [ '#MON', '#TUE', '#WED', '#THU', '#FRI' ];
		function test() {
			var id2 = array[j] + i;
			var ii = i; //なぜかi=6,j=5になる
			var jj = j;
			var flag = true;
			if ($(id2).hasClass('stored')) {
				$(id2).hover(function() {
					$(id2 + "close").css('display', 'flex');
					console.log(i + ":" + j);
					console.log(ii + ":" + jj);
					flag = true;
				}, function() {
					$(id2 + "close").hide();
				});

				$(id2 + "close").click(function() {
					$(id2).text("+");
					$(id2).toggleClass("stored");
					$(id2).toggleClass("selectable");
					$.post("CourseTable", {
						act : "remove",
						dayOfWeek : jj + 1,
						hour : ii
					});
					flag = false;
				});
			}
			$(id2).click(
					function() {
						if (!$(id2).hasClass('stored')
								&& !$(id2).hasClass('empty') && flag) {
							location.href = "SelectCourse?dayOfWeek="
									+ (jj + 1) + "&hour=" + ii;
						}
					});
		}
		for (var j = 0; j < 5; j++) {
			for (var i = 1; i <= 5; i++) {
				test();
			}
		}
	</script>
</body>
</html>