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
	private String screenName = "[G005学年選択画面]"; // デバッグ用

	public SelectGrade() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 学生が選択した学科をセッションに格納します
		String selectionParameter = request.getParameter("selection");
		if (selectionParameter == null) {
			// URLのselectionパラメータが空の場合学科選択画面にリダイレクト
			System.out.println(screenName + "URLのselectionパラメータが空なので学科選択画面にリダイレクトします");
			response.sendRedirect("SelectDepartment");
			return;
		}
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		System.out.println(screenName + "session.student:" + student);
		System.out.println(screenName + "学生が選択した学科" + selectionParameter + "をsession.student.departmentに格納します。");
		student.setDepartment(selectionParameter);
		System.out.println(screenName + "session.student:" + student);
		// ビューの描画
		getServletContext().getRequestDispatcher("/selectGrade.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}