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
		// 未ログインの場合ログイン画面ヘ転送
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}
		if (session.getAttribute("student") == null) {
			response.sendRedirect("Logout");
			return;
		}
		// ログアウト後にブラウザの戻るボタンで前の画面に戻らないようにするおまじない
		// ブラウザがこの画面をcacheで保存しないようにしていると思われる
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

		/**
		 * (例外) 注文内容変更or受取日時変更→やっぱやーめた→新しい注文しよ
		 *
		 * 注文内容/受取日時変更状態だという記憶を破棄
		 *
		 * @author Jun
		 */
		if (session.getAttribute("oldOrder") != null) {
			session.removeAttribute("oldOrder");
		}
		if (session.getAttribute("oldReceiveDatetime") != null) {
			session.removeAttribute("oldReceiveDatetime");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
