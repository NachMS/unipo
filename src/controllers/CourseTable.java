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
import models.CourseDAO;
import models.Student;
import models.StudentDAO;

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
		System.out.println("やあ、時間割表だよ");

		// DBから学生の履修科目を得て、(あれば)学生セッションに格納
		StudentDAO sdao = new StudentDAO();
		Student studentAtDB = sdao.selectStudentByID(student.getStudentID());
		List<Course> coursesAtDB = studentAtDB.getCourses();
		if (student.getCourses() == null && !coursesAtDB.isEmpty()) {
			// 学部学科学年を再選択した場合、履修科目の学科年と異なるときはやめる。
			boolean sameDepartment = (coursesAtDB.get(0).getDepartment().equals(student.getDepartment()));
			boolean sameGrade = (coursesAtDB.get(0).getGrade() == student.getGrade());
			if (sameDepartment && sameGrade) {
				student.setCourses(coursesAtDB);
			}
		}

		// ビューに渡すデータ
		String[][] selectedCourses = new String[5][5];
		boolean[][] selectalbeTiles = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				selectalbeTiles[i][j] = false;
			}
		}

		if (student.getCourses() != null) {
			// セッションに格納されている学生が選んだ全ての科目を取得
			List<Course> courses = student.getCourses();
			for (Course c : courses) {
				int dow = c.getDayOfWeek() - 1;
				int hour = c.getHour() - 1;
				selectedCourses[dow][hour] = c.getName();
			}

		}
		// 科目がない時限は選択しても無駄だから選択できないようにする
		CourseDAO cdao = new CourseDAO();
		List<Course> selectableCourses = cdao.selectSelectableCourses(student.getDepartment(), student.getGrade(), 1); // TODO
		for (Course sc : selectableCourses) {
			selectalbeTiles[sc.getDayOfWeek() - 1][sc.getHour() - 1] = true;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(selectalbeTiles[i][j]);
				System.out.print(" - ");
			}
			System.out.println();
		}
		// ビューにデータを渡す
		request.setAttribute("selectedCourses", selectedCourses);
		request.setAttribute("selectalbeTiles", selectalbeTiles);
		request.setAttribute("student", student); // 選択された学科学年を表示するため
		getServletContext().getRequestDispatcher("/courseTable.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 選択解除された科目をセッションから除外する
		 */
		if (request.getParameter("act") != null && request.getParameter("act").equals("remove")) {
			int dayOfWeek = Integer.parseInt(request.getParameter("dayOfWeek"));
			int hour = Integer.parseInt(request.getParameter("hour"));
			HttpSession session = request.getSession();
			Student student = (Student) session.getAttribute("student");
			List<Course> courses = student.getCourses();
			int wow = 0;
			for (Course c : courses) {
				log("loop:" + wow);
				if (c.getDayOfWeek() == dayOfWeek && c.getHour() == hour) {
					log(c.getName() + "を注文セッションから削除しました。");
					courses.remove(c);
				}
				wow++;
			}
			student.setCourses(courses);
		}
	}

}
