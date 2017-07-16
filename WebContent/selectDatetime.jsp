<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.Date,java.util.Calendar,java.text.SimpleDateFormat"%>
<%
	boolean isChangingReceiveDatetime = (boolean) request.getAttribute("isChangingReceiveDatetime");
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>受け取り日時選択</title>
<!-- Normalize.css -->
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" type="text/css" href="css/selectDatetime.css">
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
				int[] datesTowards7DaysAhead = (int[]) request.getAttribute("datesTowards7DaysAhead");

				for (int date : datesTowards7DaysAhead) {
			%>
			<th class="sq"><%=date%></th>
			<%
				}
			%>
		</tr>
		<tr>
			<th class="ninja"></th>
			<%
				String[] daysOfWeekTowards7DaysAhead = (String[]) request.getAttribute("daysOfWeekTowards7DaysAhead");

				for (String dow : daysOfWeekTowards7DaysAhead) {
					out.print("<th>" + dow + "</th>");
				}
			%>
		</tr>

		<%
			int[] monthOfEachDateTowards7DaysAhead = (int[]) request.getAttribute("monthOfEachDateTowards7DaysAhead");
			int hour = 10;
			int[][] congestionArray = (int[][]) request.getAttribute("congestionArray");
			for (int[] hourRow : congestionArray) {
				//int i=0;
				out.print("<tr>");
		%>
		<th class="Time"><%=hour%>-<%=(hour + 1)%></th>
		<%
			String selectablity;
				for (int j = 0; j < hourRow.length; j++) {
					selectablity = "";
					int month = monthOfEachDateTowards7DaysAhead[j];
					int date = datesTowards7DaysAhead[j];
					String link = "SelectDatetime" + "?month=" + month + "&date=" + date + "&hour=" + hour;
					if (j == 0 && hour <= currentHour) {
						link = "#";
						selectablity = "unselectable";
					}
		%>
		<td class="L <%=selectablity%>"><a href="<%=link%>"> <%=hourRow[j]%></a></td>
		<%
			}
				out.println("</tr>");
				hour++;
			}
		%>

</body>
</html>