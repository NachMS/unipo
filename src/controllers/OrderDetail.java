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

		int orderSelection = Integer.parseInt(request.getParameter("selection"));
		OrderDAO dao = new OrderDAO();
		// TextbookDAO tdao = new TextbookDAO();
		Order order = dao.getOrderByID(orderSelection);
		ArrayList<Textbook> textbooks = (ArrayList<Textbook>) order.getTextbooks();
		// ArrayList<Course> list = new ArrayList<Course>();
		// Textbook course =dao.getOrderByID(orderID);
		request.setAttribute("order", order);
		request.setAttribute("textbooks", textbooks);
		request.setAttribute("num", orderSelection);
		getServletContext().getRequestDispatcher("/orderDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
