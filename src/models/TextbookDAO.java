package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class TextbookDAO {
	private String driverClassName = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost/tutorial";
	private String user = "wspuser";
	private String password = "hogehoge";

	public boolean registerTextbook(Textbook textbook) {
		return false;

	}

	public boolean updateTextbook(Textbook textbook) {
		return false;
	}

	public boolean deleteTextbookByID(Textbook textbook) {
		return false;
	}

	public void registerEvaluation(int textbookID, int likeORDislike) {

	}

	public Textbook getTextbookByID(int textbookID) {
		try {
			System.out.println("getTextbookByID(" + textbookID + ")");
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
				Course course = cdao.getCourseByID(courseID);
				int price = resultSet.getInt("price");
				int stock = resultSet.getInt("stock");
				int likes = resultSet.getInt("likes");
				int dislikes = resultSet.getInt("dislikes");
				Date regDate = resultSet.getTimestamp("reg_date");
				textbook = new Textbook(textbookID, name, reading, course, price, stock, likes, dislikes, regDate);
				System.out.println(textbook);
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

	public ArrayList<Textbook> getTextbooksByCourseID(String courseID) {
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
				Textbook textbook = tdao.getTextbookByID(textbookID);
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
