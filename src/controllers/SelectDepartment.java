package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;

@WebServlet("/SelectDepartment")
public class SelectDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectDepartment() {
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
			response.sendRedirect("SelectFaculty");
			return;
		}
		Student student = (Student) session.getAttribute("student");
		log("URLのdepartmentパラメータ:" + request.getParameter("department"));

		/*
		 * (非DT) 学生が選択した学科をセッションに格納します
		 */
		if (request.getParameter("department") != null) {
			// URLのfacultyパラメータが格納されている場合（学部をクリックした）
			log("学生が選んだ学科" + request.getParameter("department") + "をsession.student.facultyに格納します。");
			student.setDepartment(request.getParameter("department"));
			log("session.student:" + student);
			response.sendRedirect("SelectGrade");
			return;
		}

		/*
		 * (DT) ビューの描画
		 */
		if (student.getFaculty() == null) {
			response.sendRedirect("SelectFaculty");
			return;
		}
		String faculty = student.getFaculty();
		if (faculty == null) {
			log("session.student.facultyがnullなのでSelectFacultyにリダイレクトします");
			response.sendRedirect("SelectFaculty");
			return;
		}

		String[][] array;
		if (faculty.equals("F")) {
			array = new String[3][2];
			array[0][0] = "FA";
			array[0][1] = "建築学科";
			array[1][0] = "FI";
			array[1][1] = "情報メディア学科";
			array[2][0] = "FR";
			array[2][1] = "ロボメカ学科";
		} else if (faculty.equals("E")) {
			array = new String[6][2];
			array[0][0] = "EJ";
			array[0][1] = "電気電子工学科";
			array[1][0] = "EH";
			array[1][1] = "電子シス工学科";
			array[2][0] = "ES";
			array[2][1] = "応用化学学科";
			array[3][0] = "EK";
			array[3][1] = "機械工学科";
			array[4][0] = "EF";
			array[4][1] = "先端機械工学科";
			array[5][0] = "EC";
			array[5][1] = "情報通信工学科";
		} else if (faculty.equals("A")) {
			array = new String[2][2];
			array[0][0] = "AJ";
			array[0][1] = "情報シス工学科";
			array[1][0] = "AD";
			array[1][1] = "デザイン工学科";
		} else {
			// F,E,A以外のURLのselectionパラメータの場合は学科選択画面にリダイレクト
			array = new String[0][0]; // 未初期化エラーの回避
			faculty = "";// 未初期化エラーの回避
			log("F,E,A以外のURLのselectionパラメータ" + faculty + "が来たので学部選択画面にリダイレクト");
			response.sendRedirect("SelectFaculty");
			return;
		}
		request.setAttribute("viewDataArray", array);
		request.setAttribute("faculty", faculty);
		getServletContext().getRequestDispatcher("/selectDepartment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
