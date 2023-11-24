import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ex01 {

	public static void main(String[] args) {
		// DBSM -> jdbc:mysql:
		// MySQL 서버 주소 -> //localhost:3306
		// 어떤 Datebase 사용할거야? -> /classicmodels
		String user1 = "jdbc:mysql://localhost:3306/classicmodels";
		String id = "test";
		String pw = "test1234";
		
		// Connection 객체 만들기
		try {
			// MySQL 드라이버 생성
			Class.forName("com.mysql.jdbc.Driver");
			// DB Connection 생성
			Connection conn = DriverManager.getConnection(user1, id, pw);
			System.out.println("DB Connection Success");
			
			// Query
			String query = "SHOW TABLES;";
			
			// Connection 객체에 query 전달
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			// Query 결과 받기
			ResultSet rs = pstmt.executeQuery();
			
			// 결과를 하나씩 가젼오기
			while (rs.next()) {
				String tableNmae = rs.getString("Tables_in_classicmodels");
				System.out.println(tableNmae);
			}
			
		}
		catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver Error");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		
	}

}
