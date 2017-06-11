package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;

@WebServlet("/CourseTable")
public class CourseTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String screenName = "[G006履修科目選択画面]";

	public CourseTable() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 学生が選択した学年をセッションに格納します
		String selectionParameter = request.getParameter("selection");
		if (selectionParameter == null) {
			// URLのselectionパラメータが空の場合学年選択画面にリダイレクト
			System.out.println(screenName + "URLのselectionパラメータが空なので学年選択画面にリダイレクトします");
			response.sendRedirect("SelectGrade");
			return;
		}
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		System.out.println(screenName + "session.student:" + student);
		System.out.println(screenName + "学生が選択した学年" + selectionParameter + "をsession.student.gradeに格納します。");
		int gradeInt = Integer.parseInt(selectionParameter);
		student.setGrade(gradeInt);
		System.out.println(screenName + "session.student:" + student);
		request.setAttribute("grade", gradeInt);
		// ビューの描画
		getServletContext().getRequestDispatcher("/courseTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
