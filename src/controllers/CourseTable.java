package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.Student;

@WebServlet("/CourseTable")
public class CourseTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CourseTable() {
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
		 * (DT) ビューの描画
		 */
		Student student = (Student) session.getAttribute("student");
		// ビューに渡すデータ
		String[][] selectedCourses = new String[5][5];
		if (student.getCourses() != null) {
			// セッションに格納されている学生が選んだ全ての科目を取得
			List<Course> courses = student.getCourses();
			for (Course c : courses) {
				int i = c.getDayOfWeek() - 1;
				int j = c.getHour() - 1;
				selectedCourses[i][j] = c.getName();
			}
		}
		// ビューにデータを渡す
		request.setAttribute("selectedCourses", selectedCourses);
		getServletContext().getRequestDispatcher("/courseTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 選択解除された科目をセッションから除外する
		 */
		log("POSTよばれけり");
		if (request.getParameter("act") != null && request.getParameter("act").equals("remove")) {
			log("POSTよし");
			int dayOfWeek = Integer.parseInt(request.getParameter("dayOfWeek"));
			int hour = Integer.parseInt(request.getParameter("hour"));
			HttpSession session = request.getSession();
			Student student = (Student) session.getAttribute("student");
			List<Course> courses = student.getCourses();
			log("POST セッション無罪 dow:" + dayOfWeek + "hour:" + hour);
			int wow = 0;
			for (Course c : courses) {
				log("loop:" + wow);
				if (c.getDayOfWeek() == dayOfWeek && c.getHour() == hour) {
					log(c.getName() + "をremoveしました。");
					courses.remove(c);
				}
				wow++;
			}
			student.setCourses(courses);
		}
	}

}
