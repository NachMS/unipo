package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
		// 未ログインの場合ログイン画面ヘ転送
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}

		/**
		 * メッセージがあれば抜いてjspに繋ぎ渡し @author jun
		 */
		if (session.getAttribute("message") != null) {
			String[] message = (String[]) session.getAttribute("message");
			session.removeAttribute("message");
			log("message:" + message[0] + ", " + message[1]);
			request.setAttribute("message", message);
		} else {
			log("message:空");
		}

		// 注文情報の取得
		String studentID = (String) session.getAttribute("studentID");
		OrderDAO dao = new OrderDAO();
		ArrayList<Order> list = dao.getOrdersByStudentID(studentID);// ログインIDが入るようにする
		SimpleDateFormat sdf = new SimpleDateFormat("y年M月d日 HH:mm");
		String[][] orderList = new String[list.size()][5];
		int i = 0;
		for (Order order : list) {
			orderList[i][0] = sdf.format(order.getOrderDate());
			orderList[i][1] = String.valueOf(order.getTotalAmount());
			orderList[i][2] = sdf.format(order.getReceiveDate());
			orderList[i][3] = String.valueOf(order.getOrderID());
			orderList[i][4] = String.valueOf(order.isCancelFlag());
			System.out.println(order.isCancelFlag());
			i++;
		}
		request.setAttribute("orders", orderList);
		getServletContext().getRequestDispatcher("/orderHistory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
