package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;
import models.Student;

@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		/**
		 * (例外) 未ログインの場合ログイン画面ヘ転送
		 */
		if (session.getAttribute("login") == null || !(Boolean) session.getAttribute("login")) {
			response.sendRedirect("Login");
			return;
		}

		/**
		 * (例外) 学生セッションが空の場合、学部選択画面画面へ転送
		 */
		if (session.getAttribute("student") == null) {
			log("session.studentが空なのでSelectFacultyにリダイレクトします");
			response.sendRedirect("SelectFaculty");
			return;
		}

		/**
		 * (例外) 注文セッションが空の場合教科書選択画面へ転送
		 */
		if (session.getAttribute("order") == null) {
			log("session.orderが空なのでSelectTextbooksにリダイレクトします");
			response.sendRedirect("SelectTextbooks");
			return;
		}

		/**
		 * (例外) 注文セッションのデータに抜けがある場合教科書選択画面へ転送
		 */
		Order order = (Order) session.getAttribute("order");
		// TODO
		// int dow = order.getReceiveDayOfWeek();
		// int date = order.getReceiveDateInt();
		// int hour = order.getReceiveHour();
		// List<Textbook> books = order.getTextbooks();
		// if () {
		// log("session.orderが空なのでSelectTextbooksにリダイレクトします");
		// response.sendRedirect("SelectTextbooks");
		// return;
		// }

		/**
		 * (非DT) 「確定」が押されたとき。
		 *
		 * 注文内容をDBに記録する。
		 */
		if (request.getParameter("act") != null && request.getParameter("act").equals("confirm")) {
			OrderDAO odao = new OrderDAO();
			order.setOrderDate(new Date()); // 現在時刻を格納
			Student student = (Student) session.getAttribute("student");
			String studentID = student.getStudentID();
			order.setStudentID(studentID);
			try {
				odao.registerOrder(order);
				String[] message = { "success", "注文を受け取りました。（っていうサビースがあればね・・・）" };
				// 注文内容変更なら
				if (session.getAttribute("oldOrder") != null) {
					Order oldOrder = (Order) session.getAttribute("oldOrder");
					odao.cancelOrderByID(oldOrder.getOrderID());
					session.removeAttribute("oldOrder");
					message[1] = "注文内容を変更しました。";
				}
				session.setAttribute("message", message);
				response.sendRedirect("OrderHistory");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/**
		 * (DT) 注文変更時
		 */
		boolean isChangingOrder = false; // ビューで注文変更時かどうかを識別するため
		if (session.getAttribute("oldOrder") != null) {
			isChangingOrder = true;
		}

		/**
		 * (DT) ビューの描画
		 */
		request.setAttribute("order", order);
		request.setAttribute("isChangingOrder", isChangingOrder); // ビューで注文変更時かどうかを識別するため
		getServletContext().getRequestDispatcher("/confirmOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 受取日時の有効性を検証する
	 *
	 * @param m
	 *            選択された月
	 * @param d
	 *            選択された日付（曜日ではない）
	 * @param h
	 *            選択された時間(12～13に受け取りなら12)
	 * @return 有効な受取日時ならtrue
	 */
	private boolean validDate(int m, int d, int h) {
		return (1 <= m && m <= 12) && (1 <= d && d <= 32) && (10 <= h && h <= 18);
	}

}
