package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Order;
import models.OrderDAO;

@WebServlet("/SelectDatetime")
public class SelectDatetime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectDatetime() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 今日から一週間先の日付を配列に格納 ex:{28, 29, 30, 1, 2, 3, 4}
		 */
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE);
		int dow = cal.get(Calendar.DAY_OF_WEEK); // 日曜 = 1, 月曜 = 2
		int monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] datesTowards7DaysAhead = new int[7];
		String[] daysOfWeekTowards7DaysAhead = new String[7];
		String[] dowStr = { "日", "月", "火", "水", "木", "金", "土" };
		for (int i = 0, day = today; i < 7; i++, day++, dow++) {
			datesTowards7DaysAhead[i] = (day <= monthDays) ? day : day % monthDays;
			daysOfWeekTowards7DaysAhead[i] = (dow <= 7) ? dowStr[dow - 1] : dowStr[dow % 7 - 1];
		}

		/**
		 * 注文テーブルから混雑度を算出して二次配列に格納
		 */
		int[][] congestionDataArray = new int[8][7];
		OrderDAO odao = new OrderDAO();
		List<Order> allOrders = odao.getAllOrders();
		for (Order order : allOrders) {
			if (!order.isCompleteFlag() && !order.isCancelFlag()) {
				log("今週のorder:" + order);
				Date receiveDate = order.getReceiveDate();
				Calendar receiveCal = Calendar.getInstance();
				receiveCal.setTime(receiveDate);
				int receiptDate = receiveCal.get(Calendar.DATE);
				int receiptHour = receiveCal.get(Calendar.HOUR_OF_DAY);
				log("date:" + receiptDate + ", hour:" + receiptHour);
				if ((today <= receiptDate && receiptDate <= today + 6) && (10 <= receiptHour && receiptHour <= 18)) {
					congestionDataArray[receiptHour - 10][receiptDate - today]++;
				}
			}
		}

		/**
		 * ビューの描画
		 *
		 * datesDataArray[何日目] = 日付 ex:{28, 29, 30, 1, 2, 3, 4}
		 *
		 * congestionDataArray[何日目][時間帯] = 混雑度(0:すいている, 1:中, 2:混んでいる); 時間帯:
		 * [0]10~11 [1]11~12 [2]12~13 [3]13~14 [4]14~15 [5]15~16 [6]16~17
		 * [7]17~18
		 */
		request.setAttribute("datesTowards7DaysAhead", datesTowards7DaysAhead);
		request.setAttribute("daysOfWeekTowards7DaysAhead", daysOfWeekTowards7DaysAhead);
		request.setAttribute("congestionDataArray", congestionDataArray);
		getServletContext().getRequestDispatcher("/selectDatetime.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
