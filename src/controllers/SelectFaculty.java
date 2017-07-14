package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;
import models.StudentDAO;

@WebServlet("/SelectFaculty")
public class SelectFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectFaculty() {
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

		/*
		 * (非DT) 学生が選択した学年をセッションに格納します
		 */
		Student student = (Student) session.getAttribute("student");
		log("session.student:" + student);
		if (student == null) {
			log("session.student:nullなのでログアウトします。");
			response.sendRedirect("Logout");
			return;
		}
		log("URLのselectionパラメータ:" + request.getParameter("faculty"));
		if (request.getParameter("faculty") != null) {
			// URLのfacultyパラメータが格納されている場合（学部をクリックした）
			// 学生が選んだ学部をセッションに格納します
			log("学生が選んだ学部" + request.getParameter("faculty") + "をsession.student.facultyに格納します。");
			student.setFaculty(request.getParameter("faculty"));
			log("session.student:" + student);
			response.sendRedirect("SelectDepartment");
			return;
		}

		/*
		 * (非DT) 2回目の注文以降は選択しなくていいように 録があれば、履修科目選択へスキップ
		 */
		if (!request.getParameterMap().containsKey("reselect")) {
			// URLのパラメータに?reselectが指定されている場合はスキップ
			StudentDAO sdao = new StudentDAO();
			Student studentAtDB = sdao.selectStudentByID(student.getStudentID());
			String fc = studentAtDB.getFaculty();
			String dp = studentAtDB.getDepartment();
			int gr = studentAtDB.getGrade();
			if (fc != null && dp != null && gr != 0) {
				student.setFaculty(fc);
				student.setDepartment(dp);
				student.setGrade(gr);
				response.sendRedirect("CourseTable");
				return;
			}
		}

		/*
		 * (DT) ビューの描画
		 */
		getServletContext().getRequestDispatcher("/selectFaculty.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
