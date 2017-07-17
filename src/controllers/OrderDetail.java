package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.Order;
import models.OrderDAO;
import models.Textbook;

/**
 * Servlet implementation class OrderDetail
 */
@WebServlet("/OrderDetail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderDetail() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 未ログインの場合ログイン画面ヘ転送
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}
		// 教科書を注文情報から入手
		int orderID = Integer.parseInt(request.getParameter("id"));
		OrderDAO dao = new OrderDAO();
		Order order = dao.selectOrderByID(orderID);
		ArrayList<Textbook> textbooks = (ArrayList<Textbook>) order.getTextbooks();
		// request.setAttribute("textbooks", textbooks);
		String[] dow = { "月", "火", "水", "木", "金" };
		String[][] array = new String[textbooks.size()][5];
		int i = 0;
		Collections.sort(textbooks);
		for (Textbook textbook : textbooks) {
			Course course = textbook.getCourse();
			array[i][0] = String.valueOf(course.getDayOfWeek());
			array[i][1] = dow[course.getDayOfWeek() - 1] + String.valueOf(course.getHour());
			array[i][2] = course.getName();
			array[i][3] = textbook.getName();
			array[i][4] = String.valueOf(textbook.getPrice());
			i++;
		}
		request.setAttribute("textbooks", array);

		// 注文情報から日時を取得
		String[] dateArray = new String[5];
		SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日 (E) HH:mm", Locale.JAPAN);
		String formatedOrderDate = sdf.format(order.getOrderDate());
		String formatedReceiveDate = sdf.format(order.getReceiveDate());
		try {
			Date date = sdf.parse(formatedReceiveDate);
			DateFormat dowFormat = new SimpleDateFormat("EEE", Locale.JAPANESE);
			DateFormat dateFormat = new SimpleDateFormat("d", Locale.JAPANESE);
			DateFormat timeFormat = new SimpleDateFormat("HH", Locale.JAPANESE);
			dateArray[0] = formatedOrderDate;
			dateArray[1] = String.valueOf(order.getTotalAmount());
			dateArray[2] = dowFormat.format(date);
			dateArray[3] = dateFormat.format(date);
			int h = Integer.parseInt(timeFormat.format(date));
			dateArray[4] = h + "-" + (h + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * (DT) ビューに渡すデータ
		 */
		// 表示データ-- Masa
		request.setAttribute("date", dateArray);
		// 注文キャンセル/注文内容変更/受取日時変更ボタン用
		request.setAttribute("orderID", orderID);
		// 受取日時が過ぎた注文では「変更」「キャンセル」ボタンを表示しないためのboolean変数 -- Jun
		request.setAttribute("changeDeadlineOver", order.isChangeDeadlineOver());
		// キャンセルされた注文では「変更」「キャンセル」ボタンを表示しないためのboolean変数 -- Jun
		request.setAttribute("canceled", order.isCancelFlag());

		getServletContext().getRequestDispatcher("/orderDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
