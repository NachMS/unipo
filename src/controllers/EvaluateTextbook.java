package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;
import models.Student;

@WebServlet("/EvaluateTextbook")
public class EvaluateTextbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EvaluateTextbook() {
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

		Student student = (Student) session.getAttribute("student");
		OrderDAO odao = new OrderDAO();
		ArrayList<Order> orders = odao.getOrdersByStudentID(student.getStudentID());
		request.setAttribute("orders", orders);

		getServletContext().getRequestDispatcher("/evaluateTextbook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
