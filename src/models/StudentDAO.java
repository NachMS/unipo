package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {
	final private static String dbname = "tutorial"; // データベース名
	final private static String user = "wspuser"; // tutorialにアクセスできるユーザ
	final private static String password = "hogehoge"; // wspuserのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;

	/**
	 * studentがDBにあるかどうかを調べる
	 *
	 * @param StudentインスタンスのstudentIDとpasswordだけ使用
	 * @return ログイン成功ならtrue
	 * @throws SQLException
	 */
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
			System.out.println("studentID:" + student.getStudentID() + "password:" + student.getPassword());

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

	/**
	 * 学生テーブルに学部、学科、学年を挿入
	 *
	 * @param student
	 *            学生オブジェクト(学部、学科、学年フィールドのみ使用)
	 * @return 成功ならtrue
	 * @author jun
	 */
	public boolean updateStudentAffiliation(Student student) {
		boolean result = false;
		Connection connection;
		String sql = "UPDATE students SET faculty=?, department=?, grade=? WHERE student_id = ?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, student.getFaculty());
			pstmt.setString(2, student.getDepartment());
			pstmt.setInt(3, student.getGrade());
			pstmt.setString(4, student.getStudentID());
			int count = pstmt.executeUpdate();
			result = (count > 0); // executeUpdate()がうまくいけばtrue
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生の履修科目を履修テーブルに挿入
	 *
	 * @param studentID
	 *            学生の学籍番号
	 * @param courses
	 *            学生の履修科目のリスト(科目IDのみ使用)
	 * @return 成功ならtrue
	 * @author jun
	 */
	public boolean insertStudentCourses(String studentID, List<Course> courses) {
		boolean result = true;
		Connection connection;
		String sql;
		PreparedStatement pstmt;
		sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, studentID);
			for (Course course : courses) {
				pstmt.setInt(2, course.getCourseID());
				int count = pstmt.executeUpdate();
				if (count == 0) {
					result = false; // executeUpdate()がうまくいけばtrue
				}
			}
			pstmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 学生の履修科目を履修テーブルから削除
	 *
	 * @param studentID
	 *            学籍番号
	 * @return 成功ならtrue
	 */
	public boolean deleteStudentCourses(String studentID) {
		boolean result = false;
		Connection connection;
		String sql = "DELETE FROM student_courses WHERE student_id = ?";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, studentID);
			int count = pstmt.executeUpdate();
			result = (count > 0); // executeUpdate()がうまくいけばtrue
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
