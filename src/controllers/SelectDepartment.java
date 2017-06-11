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
	private String screenName = "[G004学科選択画面]"; // デバッグ用

	public SelectDepartment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 未ログインの場合ログイン画面ヘ転送
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}

		String selectionParameter = request.getParameter("selection");
		System.out.println(screenName + "URLのselectionパラメータ:" + selectionParameter);
		Student studentSession = (Student) session.getAttribute("student");
		System.out.println(screenName + "session.student:" + studentSession);
		String faculty;
		if (selectionParameter == null) {
			// URLのselectionパラメータがnullだった場合（次のページから戻ってきたはず）
			// 学生が前に選んだ学部をセッションから取得します
			if (studentSession.getFaculty() == null) {
				// session.studentが存在しない場合
				// セッションがないはずがないのでホームにリダイレクトします。
				System.out.println(screenName + "URLのselectionパラメータもsession.student.facultyも空でそんなはずないのでホームにリダイレクトします");
				response.sendRedirect("Home");
				return;
			}
			// session.studentが存在する場合
			// 学生が選んだ学部をセッションから取得します
			System.out.println(screenName + "session.student.facultyが格納済みなので取得します。");
			faculty = studentSession.getFaculty();
		} else {
			// URLのselectionパラメータが格納されている場合（順路通り学部選択画面から来た）
			// 学生が選んだ学部をセッションに格納します
			System.out.println(screenName + "学生が選んだ学部" + selectionParameter + "をsession.student.facultyに格納します。");
			faculty = selectionParameter;
			studentSession.setFaculty(selectionParameter);
			System.out.println(screenName + "session.student:" + studentSession);
		}
		// ビューの描画
		String[][] array;
		if (faculty.equals("F")) {
			array = new String[3][2];
			array[0][0] = "FA";
			array[0][1] = "建築科";
			array[1][0] = "FI";
			array[1][1] = "情報メディア科";
			array[2][0] = "FR";
			array[2][1] = "ロボメカ科";
		} else if (faculty.equals("E")) {
			array = new String[6][2];
			array[0][0] = "EJ";
			array[0][1] = "電気電子工学科";
			array[1][0] = "EH";
			array[1][1] = "電子システム工学科";
			array[2][0] = "ES";
			array[2][1] = "応用化学科";
			array[3][0] = "EK";
			array[3][1] = "機械工学科";
			array[4][0] = "EF";
			array[4][1] = "先端機械工学科";
			array[5][0] = "EC";
			array[5][1] = "情報通信工学科";
		} else if (faculty.equals("A")) {
			array = new String[2][2];
			array[0][0] = "AJ";
			array[0][1] = "情報システム工学科";
			array[1][0] = "AD";
			array[1][1] = "デザイン工学科";
		} else {
			// F,E,A以外のURLのselectionパラメータの場合は学科選択画面にリダイレクト
			array = new String[0][0]; // 未初期化エラーの回避
			faculty = "";// 未初期化エラーの回避
			System.out.println(screenName + "F,E,A以外のURLのselectionパラメータ" + faculty + "が来たので学科選択画面にリダイレクト");
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
