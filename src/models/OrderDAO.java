package models;

import java.util.ArrayList;

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
		String sql = "SELECT * FROM orders WHERE student_id =?";
		return null;

	}

	public ArrayList<Order> getAllOrders() {

		return null;
	}

}
