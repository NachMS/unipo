package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDAO {
	private String url = "jdbc:postgresql://localhost/tutorial";
	private String user = "wspuser";
	private String password = "hogehoge";

	public boolean registerCourse(Course course) {
		return false;
	}

	public boolean updateCourse(Course course) {
		return false;
	}

	public boolean deleteCourse(Course course) {
		return false;
	}

	public Course getCourseByID(int courseID) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM courses WHERE course_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, courseID);
			ResultSet resultSet = preparedStatement.executeQuery();
			Course course;
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String teacher = resultSet.getString("teacher");
				String department = resultSet.getString("department");
				int grade = resultSet.getInt("grade");
				int semester = resultSet.getInt("semester");
				int dayOfWeek = resultSet.getInt("day_of_week");
				int hour = resultSet.getInt("hour");
				int likes = resultSet.getInt("likes");
				int dislikes = resultSet.getInt("dislikes");
				Date regDate = resultSet.getTimestamp("reg_date");
				course = new Course(courseID, name, teacher, department, grade, semester, dayOfWeek, hour, likes,
						dislikes, regDate);
				System.out.println(course);
				resultSet.close();
				preparedStatement.close();
				connection.close();
				return course;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getCourseByIDの結果nullでした。");
		return null;
	}

	public List<Course> getCoursesByProperties(String department, int grade, int semester, int dayOfWeek, int hour) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM courses WHERE department=? AND grade=? AND day_of_week=? AND hour=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, department);
			preparedStatement.setInt(2, grade);
			preparedStatement.setInt(3, dayOfWeek);
			preparedStatement.setInt(4, hour);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Course> list = new ArrayList<Course>();
			while (resultSet.next()) {
				int courseID = resultSet.getInt("course_id");
				String name = resultSet.getString("name");
				String teacher = resultSet.getString("teacher");
				int likes = resultSet.getInt("likes");
				int dislikes = resultSet.getInt("dislikes");
				Date regDate = resultSet.getTimestamp("reg_date");
				Course course = new Course(courseID, name, teacher, department, grade, semester, dayOfWeek, hour, likes,
						dislikes, regDate);
				list.add(course);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Course> getAllCourses() {
		return null;
	}

}
