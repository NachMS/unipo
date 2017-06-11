package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//未ログインの場合ログイン画面ヘ転送
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}
		//ログアウト後にブラウザの戻るボタンで前の画面に戻らないようにするおまじない
		//ブラウザがこの画面をcacheで保存しないようにしていると思われる
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
