package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;

@WebServlet("/SelectGrade")
public class SelectGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectGrade() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log("こんにちは。");
		HttpSession session = request.getSession();
		/*
		 * (例外) 未ログインの場合ログイン画面ヘ転送
		 */
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}

		/*
		 * (非DT) 学生が選択した学年をセッションに格納します
		 */
		if (request.getParameter("grade") != null) {
			Student student = (Student) session.getAttribute("student");
			int gradeInt = Integer.parseInt(request.getParameter("grade"));
			log("session.student:" + student);
			if (student.getCourses() != null && student.getGrade() != 0) {
				if (student.getGrade() != gradeInt) {
					log("G006から戻ってきて別の学年を選んだ場合セッションから科目を破棄");
					student.setCourses(null);
				}
			}
			log("学生が選択した学年" + request.getParameter("grade") + "をsession.student.gradeに格納します。");
			student.setGrade(gradeInt);
			log("session.student:" + student);
			log("CourseTableにリダイレクトします");
			response.sendRedirect("CourseTable");
			return;
		}

		/*
		 * (DT) ビューの描画
		 */
		getServletContext().getRequestDispatcher("/selectGrade.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}