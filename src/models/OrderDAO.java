package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
	final private static String dbname = "tutorial"; // データベース名
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
				for (Textbook textbook : order.getTextbooks()) {
					int orderID = resultSet.getInt("order_id");
					int textbookID = textbook.getTextbookID();
					sql = "INSERT INTO order_details (order_id, textbook_id) VALUES (?, ?)";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, orderID);
					pstmt.setInt(2, textbookID);
					pstmt.executeQuery();
					System.out.println("order_id:" + orderID + "でDBに保存しました。");
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

	public boolean login(Student student) throws SQLException {
		boolean result = false;
		Connection connection;
		String sql = "SELECT * FROM students WHERE student_id=? AND password=?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, student.getStudentID());
			pstmt.setString(2, student.getPassword());
			System.out.println(student.getStudentID());
			System.out.println(student.getPassword());

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next())
				result = true;

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
				order.setOrderID(orderID);
				order.setStudentID(studentID);
				order.setTotalAmount(total_price);
				order.setOrderDate(timestamp);
				order.setReceiveDate(retimestamp);
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
		String sql = "SELECT order_id FROM orders WHERE student_id=?";
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
