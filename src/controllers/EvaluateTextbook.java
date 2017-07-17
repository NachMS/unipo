package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;
import models.Student;
import models.Textbook;
import models.TextbookDAO;

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
		ArrayList<Order> orders = odao.selectOrdersByStudentID(student.getStudentID());

		ArrayList<Textbook> textbooks = new ArrayList<Textbook>();
		ArrayList<Integer> textbookIDs = new ArrayList<Integer>();
		for (Order order : orders) {
			if (order.isCancelFlag() == true) {
				continue;
			}
			for (Textbook textbook : order.getTextbooks()) {
				// 教科書重複防止
				if (!textbookIDs.contains(textbook.getTextbookID())) {
					textbookIDs.add(textbook.getTextbookID());
				} else {
					continue;
				}
				textbooks.add(textbook);
			}
		}

		Collections.sort(textbooks);

		request.setAttribute("textbooks", textbooks);
		getServletContext().getRequestDispatcher("/evaluateTextbook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log("postとんできたああ");
		// HttpSession session = request.getSession();
		TextbookDAO tdao = new TextbookDAO();
		// Student student = (Student) session.getAttribute("student");
		int textbookID = Integer.parseInt(request.getParameter("textbookID"));
		String val = request.getParameter("val");
		System.out.println(textbookID);
		System.out.println(val);
		if (val.equals("like")) {
			log("いいね");
			tdao.addEvaluation(textbookID, 0);
		} else if (val.equals("dislike")) {
			log("死ね");
			tdao.addEvaluation(textbookID, 1);
		}
	}

}
