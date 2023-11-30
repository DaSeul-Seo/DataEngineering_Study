### MySQL Connection

```java
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
```

### JdbcUtil을 이용한 DB Connection

- JdbcUtil

```java
import java.sql.*;

public class JdbcUtil {
	private String url = "jdbc:mysql://localhost:3306/classicmodels";
	private String id = "test";
	private String pw = "test1234";

	private static Connation conn;

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
```

```java
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
```

------------------------------
<aside>
💡 Reference

</aside>

- Maven Repository
    - https://mvnrepository.com/
