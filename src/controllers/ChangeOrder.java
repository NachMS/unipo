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

@WebServlet("/ChangeOrder")
public class ChangeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeOrder() {
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
			response.sendRedirect("SelectFaculty");
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
		 * 新しい注文セッションを作成して、古い注文のデータをコピー
		 */
		Order oldOrder = odao.getOrderByID(oldOrderID);
		Order newOrder = new Order();
		newOrder.setStudentID(student.getStudentID());
		newOrder.setReceiveDate(oldOrder.getReceiveDate());
		newOrder.setTextbooks(oldOrder.getTextbooks());
		session.setAttribute("oldOrder", oldOrder);
		session.setAttribute("order", newOrder);
		response.sendRedirect("SelectFaculty");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
