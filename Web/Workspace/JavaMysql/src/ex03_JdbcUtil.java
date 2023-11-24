import java.sql.*;

public class ex03_JdbcUtil {
	public static void main(String[] args) {
		JdbcUtil jdbc = new JdbcUtil();
		Connection conn = jdbc.getConn();

		try {
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
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
