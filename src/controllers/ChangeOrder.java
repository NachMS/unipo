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
			response.sendRedirect("Logout");
			return;
		}

		/*
		 * (例外) URLにパラメータ ?id=(注文ID) がないとホームへ
		 */
		if (request.getParameter("id") == null) {
			response.sendRedirect("Home");
			return;
		}

		Student student = (Student) session.getAttribute("student");
		int oldOrderID = Integer.parseInt(request.getParameter("id"));
		OrderDAO odao = new OrderDAO();
		Order oldOrder = odao.getOrderByID(oldOrderID);

		/*
		 * (例外) 注文はログイン中の学生の注文か確認
		 */
		if (!oldOrder.getStudentID().equals(student.getStudentID())) {
			log("学生の注文ではありません。");
			response.sendRedirect("Home");
			return;
		}

		/*
		 * 新しい注文セッションを作成して、古い注文のデータをコピー
		 */
		Order newOrder = new Order();
		newOrder.setStudentID(student.getStudentID());
		newOrder.setReceiveDate(oldOrder.getReceiveDate());
		newOrder.setTextbooks(oldOrder.getTextbooks());
		session.setAttribute("oldOrder", oldOrder);
		session.setAttribute("order", newOrder);

		// 注文の教科書の学部学科学年を取得
		String department = oldOrder.getTextbooks().get(0).getCourse().getDepartment();
		int grade = oldOrder.getTextbooks().get(0).getCourse().getGrade();
		String faculty = department.substring(0, 1); // "FI"→"F"

		// それを学生セッションに格納
		student.setFaculty(faculty);
		student.setDepartment(department);
		student.setGrade(grade);

		session.setAttribute("changing", "order"); // これで注文内容変更時だという状態を認識する
		response.sendRedirect("SelectFaculty");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
