package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

jdbc:postgresql://ec2-54-243-28-109.compute-1.amazonaws.com:5432/d4s46s6unm9mqe
?user=ktcewfifsoxmjf
&password=a86e684ae7f7d4c7f20aff89dea5d1d008c0a516e58b11c12bcfbc06b4d79a05
&sslmode=require

public class CourseDAO {
	String dbUrl = System.getenv("JDBC_DATABASE_URL");

	public Course selectCourseByID(int courseID) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(dbUrl);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM courses WHERE course_id=?";
			String sql2 = "SELECT course_id FROM textbok WHERE textbook_id=?";
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
				Date regDate = resultSet.getTimestamp("reg_date");
				course = new Course(courseID, name, teacher, department, grade, semester, dayOfWeek, hour, regDate);
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

	public List<Course> selectCoursesByProperties(String department, int grade, int semester, int dayOfWeek, int hour) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(dbUrl);
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
				Date regDate = resultSet.getTimestamp("reg_date");
				Course course = new Course(courseID, name, teacher, department, grade, semester, dayOfWeek, hour,
						regDate);
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

	public List<Course> selectSelectableCourses(String department, int grade, int semester) {
		System.out.println("selectableSelectableCourses(" + department + ", " + grade + ", " + semester + ")");

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(dbUrl);
			PreparedStatement preparedStatement;
			String sql = "SELECT * FROM courses WHERE department=? AND grade=? AND semester=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, department);
			preparedStatement.setInt(2, grade);
			preparedStatement.setInt(3, semester);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Course> list = new ArrayList<Course>();
			while (resultSet.next()) {
				int courseID = resultSet.getInt("course_id");
				String name = resultSet.getString("name");
				String teacher = resultSet.getString("teacher");
				int dayOfWeek = resultSet.getInt("day_of_week");
				int hour = resultSet.getInt("hour");
				Date regDate = resultSet.getTimestamp("reg_date");
				Course course = new Course(courseID, name, teacher, department, grade, semester, dayOfWeek, hour,
						regDate);
				list.add(course);
			}
			resultSet.close();
			preparedStatement.close();
			connection.close();
			for (Course c : list) {
				System.out.println(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
