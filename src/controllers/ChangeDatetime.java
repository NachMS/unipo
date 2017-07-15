package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;
import models.Student;

@WebServlet("/ChangeDatetime")
public class ChangeDatetime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeDatetime() {
		super();
	}

	/**
	 * 受取日時変更ボタンの遷移先
	 *
	 * URLにパラメータ ?id=(注文ID) が必要
	 *
	 * @author Jun
	 */
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

		/**
		 * (例外) URLにパラメータ ?id=(注文ID) がないとホームへ
		 */
		if (request.getParameter("id") == null) {
			log("URLにパラメータ ?id=(注文ID) がないのでホームへ");
			response.sendRedirect("Home");
			return;
		}

		Student student = (Student) session.getAttribute("student");
		int oldOrderID = Integer.parseInt(request.getParameter("id"));

		/*
		 * (例外) 注文はログイン中の学生の注文か確認
		 */
		OrderDAO odao = new OrderDAO();
		List<Order> studentOrders = odao.getOrdersByStudentID(student.getStudentID());
		boolean studentOwnsOrder = false;
		for (Order order : studentOrders) {
			if (oldOrderID == order.getOrderID()) {
				studentOwnsOrder = true;
			}
		}
		if (!studentOwnsOrder) {
			log("学生の注文ではありません。");
			response.sendRedirect("Home");
		}

		/*
		 * 注文の古い受取日時を oldReceiveDatetime としてセッションに格納
		 */
		Order oldOrder = odao.getOrderByID(oldOrderID);
		Order order = odao.getOrderByID(oldOrderID); // clone面倒なのでとりあえず
		session.setAttribute("changing", "receiveDatetime");
		session.setAttribute("oldOrder", oldOrder);
		session.setAttribute("order", order);
		response.sendRedirect("SelectDatetime");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
