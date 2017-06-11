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
		String selectionParameter = request.getParameter("selection");
		HttpSession session = request.getSession();
		System.out.println(screenName + "URLのselectionパラメータ:" + selectionParameter);
		Student studentSession = (Student) session.getAttribute("student");
		System.out.println(screenName + "session.student:" + studentSession);
		String facultyToShow;
		if (selectionParameter == null) {
			// selectionパラメータがnullだった場合（次のページから戻ってきたはず）
			// 学生が前に選んだ学部をセッションから取得します
			if (studentSession.getFaculty() == null) {
				// session.studentが存在しない場合
				// セッションがないはずがないのでホームにリダイレクトします。
				System.out.println(screenName + "URLのselectionパラメータもsession.student.facultyも空でそんなはずないのでホームにリダイレクトします");
				response.sendRedirect("HomeStudent");
				return;
			}
			// session.studentが存在する場合
			// 学生が選んだ学部をセッションから取得します
			System.out.println(screenName + "session.student.facultyが格納済みなので取得します。");
			facultyToShow = studentSession.getFaculty();
		} else {
			// selectionパラメータが格納されている場合（順路通り学部選択画面から来た）
			// 学生が選んだ学部をセッションに格納します
			System.out.println(screenName + "学生が選んだ学部" + selectionParameter + "をsession.student.facultyに格納します。");
			facultyToShow = selectionParameter;
			studentSession.setFaculty(selectionParameter);
			System.out.println(screenName + "session.student:" + studentSession);
		}
		// ビューの描画
		String[][] array = new String[3][2];
		if (facultyToShow.equals("F")) {
			array[0][0] = "FA";
			array[0][1] = "建築科";
			array[1][0] = "FI";
			array[1][1] = "情報メディア科";
			array[2][0] = "FR";
			array[2][1] = "ロボメカ科";
		} else if (facultyToShow.equals("E")) {
			array[0][0] = "工学部入力まーだ";
		}
		request.setAttribute("viewDataArray", array);
		getServletContext().getRequestDispatcher("/selectDepartment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
