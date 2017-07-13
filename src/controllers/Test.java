package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Order;
import models.OrderDAO;
import models.Textbook;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderDAO odao = new OrderDAO();
		// Order order = odao.getOrderByID(1);
		request.setCharacterEncoding("Shift_JIS");
		// System.out.println(order);
		// for (Textbook textbook : order.getTextbooks()) {
		// response.getWriter().print(textbook.getTextbookID());
		// response.getWriter().println(textbook.getName());
		// }
		List<Order> orders = odao.getOrdersByStudentID("15FI001");
		for (Order order : orders) {
			response.getWriter().println(order.getOrderID());
			for (Textbook textbook : order.getTextbooks()) {
				response.getWriter().println(textbook.getTextbookID() + ", " + textbook.getName());
			}
			response.getWriter().println();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
