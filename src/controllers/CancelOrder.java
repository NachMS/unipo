package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;
import models.Student;

@WebServlet("/CancelOrder")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CancelOrder() {
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

		/**
		 * (例外) URLにパラメータ ?id=(注文ID) がないとホームへ
		 */
		if (request.getParameter("id") == null) {
			log("URLにパラメータ ?id=(注文ID) がないのでホームへ");
			response.sendRedirect("Home");
			return;
		}

		Student student = (Student) session.getAttribute("student");
		int orderID = Integer.parseInt(request.getParameter("id"));
		OrderDAO odao = new OrderDAO();
		Order oldOrder = odao.getOrderByID(orderID);

		/*
		 * (例外) 注文はログイン中の学生の注文か確認
		 */
		if (!oldOrder.getStudentID().equals(student.getStudentID())) {
			log("学生の注文ではありません。");
			response.sendRedirect("Home");
			return;
		}

		/*
		 * 注文のキャンセル
		 */
		if (odao.cancelOrderByID(orderID)) {
			String[] message = { "success", "注文をキャンセルしました。" };
			session.setAttribute("message", message);
		} else {
			String[] message = { "error", "エラーが発生したため、注文をキャンセルできませんでした。" };
			session.setAttribute("message", message);
		}
		response.sendRedirect("OrderHistory");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
