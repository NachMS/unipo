package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDAO {
	private String url = "jdbc:postgresql://localhost/test";
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

	public Course getCourseByID(String courseID) {
		return null;
	}

	public List<Course> getCoursesByProperties(String department, int grade, int semester, int dayOfWeek,
			int hour) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM courses WHERE department=?, grade=?, day_of_week=?, hour=?";
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
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Course> getAllCourses() {
		return null;
	}

}
