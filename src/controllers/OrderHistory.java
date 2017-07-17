package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;

@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderHistory() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		/**
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

		// 注文情報の取得
		String studentID = (String) session.getAttribute("studentID");
		OrderDAO dao = new OrderDAO();
		ArrayList<Order> list = dao.selectOrdersByStudentID(studentID);// ログインIDが入るようにする
		SimpleDateFormat sdf1 = new SimpleDateFormat("y年M月d日 (E) HH:mm", Locale.JAPAN);
		SimpleDateFormat sdf2 = new SimpleDateFormat("d日 (E)", Locale.JAPAN);
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
		String[][] orderList = new String[list.size()][5];
		int i = 0;
		for (Order order : list) {
			orderList[i][0] = sdf1.format(order.getOrderDate());
			orderList[i][1] = String.valueOf(order.getTotalAmount());
			int receiveHour = Integer.parseInt(sdf3.format(order.getReceiveDate()));
			orderList[i][2] = sdf2.format(order.getReceiveDate()) + " " + receiveHour + " - " + (receiveHour + 1) + "時";
			orderList[i][3] = String.valueOf(order.getOrderID());
			orderList[i][4] = String.valueOf(order.isCancelFlag());
			System.out.println(order.isCancelFlag());
			i++;
		}

		/**
		 * 注文がなかったらメッセージを表示 -- Jun
		 */
		if (list.size() == 0) {
			String[] message = {"info", "まだ注文がありません。"};
			request.setAttribute("message", message);
		}

		/**
		 * セッションにメッセージがあれば抜いてjspに繋ぎ渡し -- Jun
		 */
		if (session.getAttribute("message") != null) {
			String[] sessonMessage = (String[]) session.getAttribute("message");
			session.removeAttribute("message");
			log("message:" + sessonMessage[0] + ", " + sessonMessage[1]);
			request.setAttribute("message", sessonMessage);
		}

		request.setAttribute("orders", orderList);
		getServletContext().getRequestDispatcher("/orderHistory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
