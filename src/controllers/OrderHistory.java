package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		OrderDAO dao = new OrderDAO();
		ArrayList<Order> list;
		list = dao.getOrdersByStudentID("15FI001"); //ログインIDが入るようにする
		request.setAttribute("orders", list);
		request.setAttribute("ordersLength", list.size());
		getServletContext().getRequestDispatcher("/orderHistory.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
