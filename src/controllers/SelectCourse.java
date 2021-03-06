package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.CourseDAO;
import models.Student;

@WebServlet("/SelectCourse")
public class SelectCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectCourse() {
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
		 * 該当時間の授業をDBより引っ張る。URLパラメータより曜日と時限、session.studentより学科、学年を得る。
		 */
		Student student = (Student) session.getAttribute("student");
		String department = student.getDepartment();
		if (department == null) {
			// 学科が空の場合は選択画面にリダイレクト
			log("session.student.departmentが空なのでSelectDepartmentにリダイレクトします");
			response.sendRedirect("SelectDepartment");
			return;
		}
		int grade = student.getGrade();
		log("grade:" + grade);
		if (grade == 0) {
			// 学年が空の場合も選択画面にリダイレクト
			log("session.student.gradeが空なのでSelectGradeにリダイレクトします");
			response.sendRedirect("SelectGrade");
			return;
		}
		if (request.getParameter("dayOfWeek") == null || request.getParameter("hour") == null) {
			// URLパラメータが空の場合、ホーム画面にリダイレクト
			log("parameter.dayOfWeekもしくはparamater.hourが空なのでHomeにリダイレクトします");
			response.sendRedirect("Home");
			return;
		}
		int dayOfWeek = Integer.parseInt(request.getParameter("dayOfWeek"));
		int hour = Integer.parseInt(request.getParameter("hour"));
		CourseDAO dao = new CourseDAO();
		int semester = 1;
		List<Course> list = dao.selectCoursesByProperties(department, grade, semester, dayOfWeek, hour);
		request.setAttribute("coursesList", list);
		getServletContext().getRequestDispatcher("/selectCourse.jsp").forward(request, response);
	}

	/*
	 * 科目を選択すると、フォームなのでここが呼び出される。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 必要なパラメータが空ならリダイレクト
		if (request.getParameter("courseID") == null) {
			response.sendRedirect("CourseTable");
			return;
		}
		/*
		 * 選ばれた科目をセッションに格納
		 */
		// 選ばれた科目のIDを取得
		int courseID = Integer.parseInt(request.getParameter("courseID"));
		CourseDAO dao = new CourseDAO();
		Course course = dao.selectCourseByID(courseID);
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("student");
		log("session.student:" + student);
		List<Course> courses;
		if (student.getCourses() == null) {
			courses = new ArrayList<Course>();
		} else {
			courses = student.getCourses();
		}
		courses.add(course);
		student.setCourses(courses);

		/*
		 * リダイレクト
		 */
		response.sendRedirect("CourseTable");
	}

}
