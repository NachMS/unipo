package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDAO {
	final private static String dbname = "unipodb"; // データベース名
	final private static String user = "wspuser"; // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	public int[][] getCongestion() {
		return null;
	}

	public String getDoW(int n) {
		return null;
	}

	public boolean registerOrder(Order order) throws SQLException {
		System.out.println("registerOrder(" + order + ")");
		System.out.println("HERE WE GO!");
		try {
			Class.forName(driverClassName);
			Connection connection;
			String sql = "INSERT INTO orders (student_id, order_timestamp, receipt_timestamp, total_price) VALUES (?, ?, ?, ?) RETURNING order_id";
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, order.getStudentID());
			Timestamp orderTimestamp = new Timestamp(order.getOrderDate().getTime());
			pstmt.setTimestamp(2, orderTimestamp);
			Timestamp receiptTimestamp = new Timestamp(order.getReceiveDate().getTime());
			pstmt.setTimestamp(3, receiptTimestamp);
			pstmt.setInt(4, order.getTotalAmount());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				int orderID = resultSet.getInt("order_id");
				System.out.println("【ODAO】  order_id:" + orderID + "でDB(orders)に保存しました。");
				for (Textbook textbook : order.getTextbooks()) {
					int textbookID = textbook.getTextbookID();
					sql = "INSERT INTO order_details (order_id, textbook_id) VALUES (?, ?)";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, orderID);
					pstmt.setInt(2, textbookID);
					pstmt.executeUpdate();
					System.out.println("◆ODAO◆ textbook_id:" + textbookID + "→ DB(order_details)");
				}
			}
			pstmt.close();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cancelOrderByID(int orderID) {
		String sql = "UPDATE orders SET cancel_flag = 'true' WHERE order_id=?";
		Connection connection;
		ResultSet resultSet;
		Order order = new Order();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, orderID);
			resultSet = pstmt.executeQuery();
			resultSet.updateRow();
			// キャンセルフラグをtrueに
			order.setCancelFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 受取日時の更新
	 *
	 * @param orderID
	 *            注文ID
	 * @param receiveDate
	 *            新しい受取日時(Date型)
	 * @return 成功ならtrue
	 */
	public boolean updateReceiveDate(int orderID, Date receiveDate) {
		System.out.println("updateReceiveDate(" + orderID + ", " + receiveDate + ")");
		boolean result = false;
		String sql = "UPDATE orders SET receipt_timestamp = ? WHERE order_id=?";
		Connection connection;
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			Timestamp receiveTimestamp = new Timestamp(receiveDate.getTime());
			pstmt.setTimestamp(1, receiveTimestamp);
			pstmt.setInt(2, orderID);
			int count = pstmt.executeUpdate();
			result = (count > 0); // 成功ならtrue
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Order getOrderByID(int orderID) {
		System.out.println("getOrderByID(" + orderID + ")");
		String sql = "SELECT * FROM orders WHERE order_id=?";
		String sql2 = "SELECT textbook_id FROM order_details NATURAL JOIN textbooks WHERE order_id=?;";
		Connection connection;
		ResultSet resultSet;
		ResultSet resultSet2;
		Order order = new Order();
		TextbookDAO tdao = new TextbookDAO();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt.setInt(1, orderID);
			resultSet = pstmt.executeQuery();
			pstmt2.setInt(1, orderID);
			resultSet2 = pstmt2.executeQuery();
			if (resultSet.next()) {
				String studentID = resultSet.getString("student_id");
				Date timestamp = resultSet.getTimestamp("order_timestamp");
				int total_price = resultSet.getInt("total_price");
				Date retimestamp = resultSet.getTimestamp("receipt_timestamp");
				boolean completeFlag = resultSet.getBoolean("complete_flag");
				boolean cancelFlag = resultSet.getBoolean("cancel_flag");

				order.setOrderID(orderID);
				order.setStudentID(studentID);
				order.setTotalAmount(total_price);
				order.setOrderDate(timestamp);
				order.setReceiveDate(retimestamp);
				order.setCompleteFlag(completeFlag);
				order.setCancelFlag(cancelFlag);
				// 注文に含まれる教科書リストを得る
				List<Textbook> textbooks = new ArrayList<Textbook>();
				while (resultSet2.next()) {
					int textbookID = resultSet2.getInt("textbook_id");
					Textbook textbook = tdao.getTextbookByID(textbookID);
					textbooks.add(textbook);

				}
				order.setTextbooks(textbooks);

			}
			resultSet.close();
			resultSet2.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public ArrayList<Order> getOrdersByStudentID(String studentID) {
		String sql = "SELECT order_id FROM orders WHERE student_id=? ORDER BY order_id DESC";
		Connection connection;
		ResultSet resultSet;
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, studentID);
			resultSet = pstmt.executeQuery();
			OrderDAO odao = new OrderDAO();
			while (resultSet.next()) {
				int orderID = resultSet.getInt("order_id");
				Order order = odao.getOrderByID(orderID);
				list.add(order);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 受取会場の混雑度を取得 (今週中の各時間帯の受取人数)
	 *
	 * @return int[8][7] 配列
	 *
	 *         引数1: 時間帯 ([0]10~11時...[7]17~18時)
	 *
	 *         引数2: 今日から何日目 ([0]今日~[6]6日後)
	 * @author Jun
	 */
	public int[][] getCongestionArray() {
		int[][] ordersNumArray = new int[8][7]; // 戻り値
		System.out.println("selectWeekOrders()");
		String sql = "SELECT receipt_timestamp FROM orders WHERE receipt_timestamp >= ? AND receipt_timestamp <= ? AND cancel_flag = false";
		Connection connection;
		ResultSet resultSet;
		/*
		 * 日付比較のため、今日の午前0時と7日後の午前0時を取得
		 */
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DATE); // 本日の日付(後で結果を配列に格納するときに使う)
		cal.clear(Calendar.HOUR);
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		Date todayMidnight = cal.getTime(); // 今日の午前0時
		cal.add(Calendar.DAY_OF_MONTH, 7);
		Date todayPlus7Midnight = cal.getTime(); // 7日後の午前0時
		System.out.println(todayMidnight + " から " + todayPlus7Midnight + " までのキャンセルされていない注文を湯得します。");
		/*
		 * DBにお問い合わせ
		 */
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			Timestamp todayTimestamp = new Timestamp(todayMidnight.getTime());
			pstmt.setTimestamp(1, todayTimestamp);
			Timestamp todayPlus7Timestamp = new Timestamp(todayPlus7Midnight.getTime());
			pstmt.setTimestamp(2, todayPlus7Timestamp);
			resultSet = pstmt.executeQuery();
			/*
			 * 結果を二次配列に格納
			 */
			while (resultSet.next()) {
				Calendar receiveCal = Calendar.getInstance();
				receiveCal.setTime(resultSet.getTimestamp("receipt_timestamp"));
				int receiveDate = receiveCal.get(Calendar.DATE);
				int receiveHour = receiveCal.get(Calendar.HOUR_OF_DAY);
				// ArrayOutOfBounds から守ってくれる if
				if ((today <= receiveDate && receiveDate <= today + 6) && (10 <= receiveHour && receiveHour <= 18)) {
					ordersNumArray[receiveHour - 10][receiveDate - today]++;
				}
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersNumArray;
	}

	public ArrayList<Order> getAllOrders() {
		String sql = "SELECT order_id FROM orders";
		Connection connection;
		ResultSet resultSet;
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			OrderDAO odao = new OrderDAO();
			while (resultSet.next()) {
				int orderID = resultSet.getInt("order_id");
				Order order = odao.getOrderByID(orderID);
				list.add(order);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
