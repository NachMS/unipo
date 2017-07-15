package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Order;
import models.OrderDAO;

@WebServlet("/ConfirmNewDatetime")
public class ConfirmNewDatetime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmNewDatetime() {
		super();
	}

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
		if (session.getAttribute("student") == null) {
			response.sendRedirect("Logout");
			return;
		}

		/**
		 * (例外) 受取日時変更時でなければホームへ転送
		 */
		if (session.getAttribute("changing") == null || !session.getAttribute("changing").equals("receiveDatetime")) {
			log("受取日時変更時でないのでホームへ転送");
			response.sendRedirect("Home");
			return;
		}

		/**
		 * (例外) セッションに oldOrder と order 両方ないとホームへ転送
		 */
		if (session.getAttribute("oldOrder") == null || session.getAttribute("order") == null) {
			log("セッションに oldOrder と order がないのでホームへ転送");
			response.sendRedirect("Home");
			return;
		}

		Order before = (Order) session.getAttribute("oldOrder");
		Order after = (Order) session.getAttribute("order");

		/**
		 * (例外)newReceiveDatetime がおかしいとホームへ転送
		 */
		int m = after.getReceiveMonth();
		int d = after.getReceiveDateInt();
		int h = after.getReceiveHour();
		if (!isValidDate(m, d, h)) {
			log("無効な受取日。" + m + "月" + d + "日" + h + "時。ホームへ転送。");
			response.sendRedirect("Home");
			return;
		}

		/**
		 * (非DT) 「確定」が押されたとき。
		 *
		 * 注文テーブルの受取日時を更新する。
		 */
		if (request.getParameterMap().containsKey("confirm")) {
			OrderDAO odao = new OrderDAO();
			if (odao.updateReceiveDate(after.getOrderID(), after.getReceiveDate())) {
				log("受取日時を変更しました。");
				String[] message = { "success", "受取日時を変更しました。" };
				session.setAttribute("message", message);
			} else {
				log("エラーが発生したため、受取日時を変更できませんでした。");
				String[] message = { "error", "エラーが発生したため、受取日時を変更できませんでした。" };
				session.setAttribute("message", message);
			}
			session.removeAttribute("changing");
			session.removeAttribute("oldOrder");
			session.removeAttribute("order");
			response.sendRedirect("OrderHistory");
			return;
		}

		/**
		 * (DT) ビューの描画
		 */
		request.setAttribute("before", before);
		request.setAttribute("after", after);

		getServletContext().getRequestDispatcher("/confirmNewDatetime.jsp").forward(request, response);
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
	 * @author Jun
	 */
	private boolean isValidDate(int m, int d, int h) {
		return (1 <= m && m <= 12) && (1 <= d && d <= 32) && (10 <= h && h <= 18);
	}

}
