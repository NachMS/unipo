<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.Date,java.util.Calendar,java.text.SimpleDateFormat"%>
<%
	/*
	* コントローラからのデータ
	*/
	int[] datesTowards7DaysAhead = (int[]) request.getAttribute("datesTowards7DaysAhead");
	String[] daysOfWeekTowards7DaysAhead = (String[]) request.getAttribute("daysOfWeekTowards7DaysAhead");
	int[] monthOfEachDateTowards7DaysAhead = (int[]) request.getAttribute("monthOfEachDateTowards7DaysAhead");
	int[][] congestionArray = (int[][]) request.getAttribute("congestionArray");
	boolean isChangingReceiveDatetime = (boolean) request.getAttribute("isChangingReceiveDatetime");
	final int CROWDED = (int) request.getAttribute("CROWDED"); //黄色 混雑警戒値 （まだ選択可能）
	final int TOO_CROWDED = (int) request.getAttribute("TOO_CROWDED"); //赤 混雑上限値 (もう選択不可)
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>受け取り日時選択</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectDatetime.css">
<!-- jQuery -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
	<%
		//受取日時変更時は戻るボタンを非表示に
		if (!isChangingReceiveDatetime) {
	%>
	<a class="back" href="SelectFaculty">戻る</a>
	<%
		}
	%>
	<div class="message">受け取り日時を選択してください</div>
	<table>
		<tr>
			<th class="ninja"></th>
			<%
				Calendar cal = Calendar.getInstance();
				int currentHour = cal.get(Calendar.HOUR_OF_DAY);
				for (int i = 0; i < 7; i++) {
			%>
			<th class="sq"><%=datesTowards7DaysAhead[i]%><br /><%=daysOfWeekTowards7DaysAhead[i]%></th>
			<%
				}
			%>
		</tr>
		<%
			int hour = 10;
			for (int[] hourRow : congestionArray) {
				out.print("<tr>");
		%>
		<th class="Time"><%=hour%>-<%=(hour + 1)%></th>
		<%
			for (int j = 0; j < hourRow.length; j++) {
					int month = monthOfEachDateTowards7DaysAhead[j];
					int date = datesTowards7DaysAhead[j];
					String link = "SelectDatetime" + "?month=" + month + "&date=" + date + "&hour=" + hour;
					boolean isSelectable = true; //クリックできるかどうか
					String cellStyleClass; //セルの色
					if (j == 0 && hour <= currentHour) {
						cellStyleClass = "unselectable"; //灰色 (過ぎた時間)
						isSelectable = false;
					} else if (hourRow[j] >= TOO_CROWDED) {
						cellStyleClass = "H"; //赤 (混みすぎ)
						isSelectable = false;
					} else if (hourRow[j] >= CROWDED) {
						cellStyleClass = "M"; //黄 (混んでいる)
					} else {
						cellStyleClass = "L"; //緑 (空いている)
					}
					if (isSelectable) {
		%>
		<td class="selection-cell <%=cellStyleClass%>"><a
			href="<%=link%>"> <%=hourRow[j]%></a></td>
		<%
			} else {
		%>
		<td class="selection-cell <%=cellStyleClass%>" title="選択できません。"><%=hourRow[j]%></td>
		<%
			}
				}
				out.println("</tr>");
				hour++;
			}
		%>

</body>
</html>