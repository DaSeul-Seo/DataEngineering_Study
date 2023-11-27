import java.sql.*;

public class JdbcUtil {
	private String url = "jdbc:mysql://localhost:3306/classicmodels";
	private String id = "test";
	private String pw = "test1234";
	
	private static Connection conn;
	
	public JdbcUtil() {
		try {
			// MySQL 드라이버 생성
			Class.forName("com.mysql.jdbc.Driver");
			// DB Connection 생성
			JdbcUtil.conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connection Success");
		}
		catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver Error");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Error" + e);
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}
	
}
