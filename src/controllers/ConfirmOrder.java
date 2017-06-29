package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;

@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		/**
		 * (例外) 学生セッションが空の場合、学部選択画面画面へ転送
		 */
		if (session.getAttribute("student") == null) {
			log("session.studentが空なのでSelectFacultyにリダイレクトします");
			response.sendRedirect("SelectFaculty");
			return;
		}

		/**
		 * (例外) 注文セッションが空の場合教科書選択画面へ転送
		 */
		if (session.getAttribute("order") == null) {
			log("session.orderが空なのでSelectTextbooksにリダイレクトします");
			response.sendRedirect("SelectTextbooks");
			return;
		}

		/**
		 * (DT) ビューの描画
		 */
		Order order = (Order) session.getAttribute("order");
		request.setAttribute("order", order);
		getServletContext().getRequestDispatcher("/confirmOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
