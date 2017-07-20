import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * studentsテーブルに15FI001～15FI150を自動で入力する
 *
 * パスワードは学籍番号の反転したもの (15FI103→301IF51)
 *
 * @author Jun
 *
 */
public class AutoInserter {
	public static void main(String[] args) {
		for (int i = 1; i <= 150; i++) {
			String iFilledWithZeros = String.format("%3s", i).replace(" ", "0");
			StringBuffer sf = new StringBuffer("15FI" + iFilledWithZeros);
			String id = sf.toString();
			sf.reverse();
			String pass = sf.toString();
			System.out.println(id);
			System.out.println(pass);
			insertStudent(id, pass);
		}
	}

	private static void insertStudent(String id, String pass) {
		String driverClassName = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost/unipodb";
		String user = "wspuser";
		String password = "hogehoge";
		Connection connection;
		PreparedStatement preparedStatement;
		String sql = "INSERT INTO students (student_id, password) VALUES (?, ?)";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user, password);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pass);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}