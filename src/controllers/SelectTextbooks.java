package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.Order;
import models.Student;
import models.StudentDAO;
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
		 * 学生の履修科目をDBに保存
		 */
		StudentDAO sdao = new StudentDAO();
		sdao.deleteStudentCourses(student.getStudentID());
		sdao.insertStudentCourses(student.getStudentID(), student.getCourses());

		/**
		 * 学生の時間割に対応する教科書をDBより引っ張る。 session.studentより科目リストを得る。
		 */
		TextbookDAO dao = new TextbookDAO();
		List<Textbook> suggestedTextbooks = new ArrayList<Textbook>();

		for (Course course : courses) {
			Textbook textbook = dao.selectTextbookByID(course.getCourseID());
			if (textbook != null) {
				suggestedTextbooks.add(textbook);
			}
		}
		Collections.sort(suggestedTextbooks);
		/**
		 * ビューの描画。
		 */
		request.setAttribute("suggestedTextbooks", suggestedTextbooks);
		getServletContext().getRequestDispatcher("/selectTextbooks.jsp").forward(request, response);
	}

	/**
	 * 教科書選択画面で教科書を選んで「確定」を選ぶとここに来ます。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		log("POST");

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
		 * (例外) POSTパラメータが空の場合、教科書選択画面画面へ転送
		 */
		if (session.getAttribute("student") == null) {
			log("param.studentIDが空なのでSelectTextbooksにリダイレクトします");
			log("少なくともひとつは教科書を選べ");
			response.sendRedirect("SelectTextbooks");
			return;
		}

		/**
		 * 選ばれし教科書諸君を取得。
		 */
		String[] textbookIDs = request.getParameterValues("textbookID"); // String→int変換は後で
		log("params:" + Arrays.toString(textbookIDs));

		/**
		 * 教科書リスト(のみ)を注文セッションに格納する。
		 */
		List<Textbook> textbooks = new ArrayList<Textbook>();
		int totalAmount = 0;
		TextbookDAO tdao = new TextbookDAO();
		for (String textbookIDstr : textbookIDs) {
			int textbookID = Integer.parseInt(textbookIDstr);
			Textbook textbook = tdao.selectTextbookByID(textbookID);
			totalAmount += textbook.getPrice();
			textbooks.add(textbook);
		}
		log("[格納前] session.order:" + session.getAttribute("order"));
		if (session.getAttribute("order") == null) {
			log("session.orderが存在しないので空のOrderを格納");
			session.setAttribute("order", new Order());
		}
		Order order = (Order) session.getAttribute("order");
		order.setTextbooks(textbooks);
		order.setTotalAmount(totalAmount);
		log("[格納後] session.order:" + session.getAttribute("order"));

		/**
		 * 受取日時選択画面へ遷移
		 */
		log("役目は果たしたので受取日時選択画面へ遷移します。");
		response.sendRedirect("SelectDatetime");
	}

}
