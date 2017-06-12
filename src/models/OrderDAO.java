package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

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

	public boolean registerOrder(Order order) {
		return false;
	}

	public boolean cancelOrderByID(int orderID) {
		String sql = "UPDATE orders SET cancel_flag = 't' WHERE order_id=?";
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
		String sql = "SELECT * FROM orders WHERE order_id=?";
		Connection connection;
		ResultSet resultSet;
		Order order = new Order();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, orderID);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Date timestamp = resultSet.getTimestamp("order_timestamp");
				int total_price = resultSet.getInt("total_price");
				Date retimestamp = resultSet.getTimestamp("receipt_timestamp");
				//
				order.setTotalAmount(total_price);
				order.setOrderDate(timestamp);
				order.setReceiveDate(retimestamp);

			}
			resultSet.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public ArrayList<Order> getOrdersByStudentID(String studentID) {
		String sql = "SELECT * FROM orders WHERE student_id=?";
		Connection connection;
		ResultSet resultSet;
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, studentID);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Order order = new Order();
				int orderID = resultSet.getInt("order_id");
				Date timestamp = resultSet.getTimestamp("order_timestamp");
				int total_price = resultSet.getInt("total_price");
				Date retimestamp = resultSet.getTimestamp("receipt_timestamp");
				order.setOrderID(orderID);
				order.setTotalAmount(total_price);
				order.setOrderDate(timestamp);
				order.setReceiveDate(retimestamp);
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

		return null;
	}

}
