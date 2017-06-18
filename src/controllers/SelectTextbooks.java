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
import models.Textbook;
import models.TextbookDAO;

@WebServlet("/SelectTextbooks")
public class SelectTextbooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectTextbooks() {
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
		 * (例外) 学生セッションが空の場合、学部選択画面画面へ転送
		 */
		if (session.getAttribute("student") == null) {
			log("session.studentが空なのでSelectFacultyにリダイレクトします");
			response.sendRedirect("SelectFaculty");
			return;
		}

		/*
		 * (例外) 学生セッションの履修科目リストが空の場合履修科目一覧画面へ転送
		 */
		Student student = (Student) session.getAttribute("student");
		List<Course> courses = student.getCourses();
		if (courses == null) {
			log("session.student.coursesが空なのでCourseTableにリダイレクトします");
			response.sendRedirect("CourseTable");
			return;
		}

		/**
		 * 学生の時間割に対応する教科書をDBより引っ張る。 session.studentより科目リストを得る。
		 */
		TextbookDAO dao = new TextbookDAO();
		List<Textbook> suggestedTextbooks = new ArrayList<Textbook>();

		for (Course course : courses) {
			Textbook textbook = dao.getTextbookByID(course.getCourseID());
			if (textbook != null) {
				suggestedTextbooks.add(textbook);
			}
		}

		/**
		 * ビューの描画。
		 *
		 * dataArray[i][0]:textbookID, dataArray[i][1]:曜日と時限(例："金1"),
		 * dataArray[i][2]:科目名, dataArray[i][3]:教科書名
		 */
		String[][] dataArray = new String[suggestedTextbooks.size()][4];
		CourseDAO cdao = new CourseDAO();
		String[] dow = { "月", "火", "水", "木", "金" };
		int i = 0;
		for (Textbook t : suggestedTextbooks) {
			Course c = cdao.getCourseByID(t.getCourseID());
			dataArray[i][0] = String.valueOf(t.getTextbookID()); // textbookID
			dataArray[i][1] = dow[c.getDayOfWeek() - 1] + c.getHour(); // 曜日と時限(例："金1")
			dataArray[i][2] = c.getName(); // 科目名
			dataArray[i][3] = t.getName(); // 教科書名
			i++;
		}
		request.setAttribute("dataArray", dataArray);
		getServletContext().getRequestDispatcher("/selectTextbooks.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
