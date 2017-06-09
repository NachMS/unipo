package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

	public boolean registerOrder(Order oreder) {
		return false;
	}

	public boolean cancelOrderByID(Order order) {
		return false;
	}

	public Order getOrderByID(int orderID) {
		return null;
	}

	public ArrayList<Order> getOrdersByStudentID(String studentID) {
		String sql = "SELECT order_timestamp,total_price,receipt_timestamp FROM orders WHERE student_id='15FI001'";
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			 resultSet = statement.executeQuery(sql);
			//PreparedStatement pstmt = connection.prepareStatement(sql);
			//pstmt.setString(1, studentID);
			while (resultSet.next()) {

				Date timestamp = resultSet.getTimestamp("order_timestamp");
				// System.out.println("toString:" + timestamp);
				// SimpleDateFormat sdf = new SimpleDateFormat("書式設定：y/M/d
				// hh:mm");
				// System.out.println(sdf.format(timestamp.getTime()));
				int total_price = resultSet.getInt("total_price");
				Date retimestamp = resultSet.getTimestamp("receipt_timestamp");
				Order order = new Order();
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
