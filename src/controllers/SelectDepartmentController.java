package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectDepartmentController")
public class SelectDepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelectDepartmentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String facultySelection;
		facultySelection = request.getParameter("selection");

		String[][] array = new String[3][2];
		if (facultySelection.equals("f")) {
			array[0][0] = "FA";
			array[0][1] = "建築科";
			array[1][0] = "FI";
			array[1][1] = "情報メディア科";
			array[2][0] = "FR";
			array[2][1] = "ロボメカ科";
		} else if (facultySelection.equals("e")){
			array[0][0] = "工学部入力まーだ";
		}

		HttpSession session = request.getSession();
		session.setAttribute("viewDataArray", array);

		response.sendRedirect("selectDepartment.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
