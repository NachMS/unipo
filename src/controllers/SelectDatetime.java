package controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();

		/*
		 * (例外) 未ログインの場合ログイン画面ヘ転送
		 */
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}
		if (session.getAttribute("student") == null) {
			response.sendRedirect("Logout");
			return;
		}

		boolean isChangingOrder = (session.getAttribute("changing") != null
				&& session.getAttribute("changing").equals("order"));
		log("今注文内容変更中?" + isChangingOrder);

		boolean isChangingReceiveDatetime = (session.getAttribute("changing") != null
				&& session.getAttribute("changing").equals("receiveDatetime"));
		log("今受取日時変更中?" + isChangingReceiveDatetime);

		/*
		 * (例外) 注文セッションが空の場合教科書選択画面へ転送
		 *
		 * なお、受取日時変更時なら転送しない。 --Jun
		 */
		if (session.getAttribute("order") == null && !isChangingReceiveDatetime) {
			log("session.orderが空なのでSelectTextbooksにリダイレクトします");
			response.sendRedirect("SelectTextbooks");
			return;
		}

		Order order = (Order) session.getAttribute("order");

		/**
		 * 注文内容変更時この画面をスキップする
		 *
		 * ただし、受取日時変更時はスキップしない。
		 */
		if (isChangingOrder && !isChangingReceiveDatetime) {
			Order oldOrder = (Order) session.getAttribute("oldOrder");
			if (!oldOrder.getTextbooks().isEmpty()) {
				order.setReceiveDate(oldOrder.getReceiveDate());
				log("注文内容変更時なのでこの画面をスキップします");
				response.sendRedirect("ConfirmOrder");
				return;
			}
		}

		/**
		 * (非DT) 受取日時が選択されたとき
		 */
		if (request.getParameter("month") != null && request.getParameter("date") != null
				&& request.getParameter("hour") != null) {
			int selectedMonth = Integer.parseInt(request.getParameter("month"));
			int selectedDate = Integer.parseInt(request.getParameter("date"));
			int selectedHour = Integer.parseInt(request.getParameter("hour"));
			log("受取日時(月:" + selectedMonth + "日付:" + selectedDate + "時間:" + selectedHour + ")");
			// (例外処理) 無効な受取日時か確認
			if (!validDate(selectedMonth, selectedDate, selectedHour)) {
				log("無効な受取日時(月:" + selectedMonth + "日付:" + selectedDate + "時間:" + selectedHour + ")");
				response.sendRedirect("SelectDatetime");
				return;
			}
			// (例外処理) 混雑している（満員の）時間でないか確認
			if (true) {
				// TODO
			}
			Calendar cal = Calendar.getInstance();
			int thisYear = cal.get(Calendar.YEAR);
			int minute = 0;
			cal.set(thisYear, selectedMonth - 1, selectedDate, selectedHour, minute); // Calendar.MONTHは6月なら=5
			Date receiveDate = cal.getTime();
			/*
			 * 受取日時変更中なら、受取日時を注文セッションに格納
			 *
			 * 受取日時確認画面へ
			 */
			if (isChangingReceiveDatetime) {
				Date newReceiveDatetime = receiveDate;
				order.setReceiveDate(newReceiveDatetime);
				log("新しい受取日時を注文セッションに格納しました。");
				response.sendRedirect("ConfirmNewDatetime");
				return;
			}
			/*
			 * 通常時(受取日時変更中でない)なら、受取日時を注文セッションに保存
			 *
			 * 注文内容確認画面へ
			 */
			log("[格納前] session.order:" + order);
			order.setReceiveDate(receiveDate);
			log("[格納後] session.order:" + order);
			response.sendRedirect("ConfirmOrder");
			return;
		}

		/**
		 * (DT) 以下、ビューのデータの用意
		 */

		/**
		 * 今日から一週間先の日付を配列に格納 ex:{28, 29, 30, 1, 2, 3, 4}
		 */
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE);
		int dow = cal.get(Calendar.DAY_OF_WEEK); // 日曜 = 1, 月曜 = 2
		int month = cal.get(Calendar.MONTH) + 1; // 今月6月ならCalendar.MONTH=5なので+1する
		int monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] datesTowards7DaysAhead = new int[7];
		String[] daysOfWeekTowards7DaysAhead = new String[7];
		int[] monthOfEachDateTowards7DaysAhead = new int[7]; // SelectDatetimeコントローラで必要
		String[] dowKanjis = { "日", "月", "火", "水", "木", "金", "土" };
		for (int i = 0, day = today; i < 7; i++, day++, dow++) {
			datesTowards7DaysAhead[i] = (day <= monthDays) ? day : day % monthDays;
			daysOfWeekTowards7DaysAhead[i] = (dow <= 7) ? dowKanjis[dow - 1] : dowKanjis[dow % 7 - 1];
			monthOfEachDateTowards7DaysAhead[i] = (day <= monthDays) ? month : month + 1;
		}

		/**
		 * 注文テーブルから混雑度を算出して二次配列に格納
		 */
		OrderDAO odao = new OrderDAO();
		int[][] congestionArray = odao.getCongestionArray();

		/**
		 * ビューの描画
		 *
		 * datesDataArray[何日目] = 日付 ex:{28, 29, 30, 1, 2, 3, 4}
		 *
		 * congestionArray[時間帯][何日目] = 受取人数 (int)
		 *
		 * 引数1: 時間帯 ([0]10~11時...[7]17~18時)
		 *
		 * 引数2: 今日から何日目 ([0]今日~[6]6日後)
		 */
		request.setAttribute("datesTowards7DaysAhead", datesTowards7DaysAhead);
		request.setAttribute("daysOfWeekTowards7DaysAhead", daysOfWeekTowards7DaysAhead);
		request.setAttribute("monthOfEachDateTowards7DaysAhead", monthOfEachDateTowards7DaysAhead);
		request.setAttribute("congestionArray", congestionArray);
		request.setAttribute("isChangingReceiveDatetime", isChangingReceiveDatetime); // 戻るボタンを非表示にするため

		getServletContext().getRequestDispatcher("/selectDatetime.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 受取日時の有効性を検証する
	 *
	 * @param m
	 *            選択された月
	 * @param d
	 *            選択された日付（曜日ではない）
	 * @param h
	 *            選択された時間(12～13に受け取りなら12)
	 * @return 有効な受取日時ならtrue
	 */
	private boolean validDate(int m, int d, int h) {
		return (1 <= m && m <= 12) && (1 <= d && d <= 32) && (10 <= h && h <= 18);
	}

}
