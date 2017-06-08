package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Student;
import models.StudentDao;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	// URLでアクセスされたときはここ
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// "/unipo/LoginController"というURLのままunpo.jspを描画
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	// ログインボタン押されたときはここ
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		StudentDao dao = new StudentDao();

		student.setName(request.getParameter("name"));
		student.setPass(request.getParameter("pass"));

		boolean result = false;
		try {
			result = dao.check(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("login", result);
		if (result) {
			// ログインに成功している場合はHomeStudentControllerへ
			session.setAttribute("student", student);
			response.sendRedirect("HomeStudentController");
		} else {
			// ログインに失敗している場合はLoginControllerへ
			response.sendRedirect("LoginController");
		}
	}

}
