package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TextbookDAO {
	ResourceBundle rb = ResourceBundle.getBundle("db");
	final private String url = rb.getString("url");
	final private String user = rb.getString("user");
	final private String password = rb.getString("password");
	final private String driverClassName = rb.getString("driverClassName");

	/**
	 *
	 * @param textbookID
	 * @param likeORDislike
	 *            0がlike 1がdislike
	 */
	public void addEvaluation(int textbookID, int likeORDislike) {
		String sql = "UPDATE textbooks SET likes=likes+1 WHERE textbook_id=?";
		String sql2 = "UPDATE textbooks SET dislikes=dislikes+1 WHERE textbook_id=?";
		Connection connection;
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			if (likeORDislike == 0) {
				pstmt.setInt(1, textbookID);
				pstmt.executeUpdate();
			} else {
				pstmt2.setInt(1, textbookID);
				pstmt2.executeUpdate();
			}
			pstmt2.close();
			pstmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public Textbook selectTextbookByID(int textbookID) {
		try {
			Class.forName(driverClassName);
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM textbooks WHERE textbook_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, textbookID);
			ResultSet resultSet = preparedStatement.executeQuery();
			Textbook textbook;
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String reading = resultSet.getString("reading");
				int courseID = resultSet.getInt("course_id");
				CourseDAO cdao = new CourseDAO();
				Course course = cdao.selectCourseByID(courseID);
				int price = resultSet.getInt("price");
				int stock = resultSet.getInt("stock");
				int likes = resultSet.getInt("likes");
				int dislikes = resultSet.getInt("dislikes");
				textbook = new Textbook(textbookID, name, reading, course, price, stock, likes, dislikes);
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return textbook;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getTextbookByIDの結果nullでした。");
		return null;
	}

	public ArrayList<Textbook> selectTextbookByCourseID(String courseID) {
		String sql = "SELECT textbook_id FROM textbooks WHERE course_id=?";
		Connection connection;
		ResultSet resultSet;
		ArrayList<Textbook> list = new ArrayList<Textbook>();
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, courseID);
			resultSet = pstmt.executeQuery();
			TextbookDAO tdao = new TextbookDAO();
			while (resultSet.next()) {
				int textbookID = resultSet.getInt("textbook_id");
				Textbook textbook = tdao.selectTextbookByID(textbookID);
				list.add(textbook);
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
