import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ex02_example {

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
			String query = "select \r\n"
					+ "	.od.orderNumber as order_num, max(od.orderLineNumber) as order_max\r\n"
					+ "from orders o, orderdetails od\r\n"
					+ "where 1=1\r\n"
					+ "  and o.status = 'Shipped'\r\n"
					+ "  and o.orderNumber = od.orderNumber\r\n"
					+ "group by o.orderNumber\r\n"
					+ "limit 5\r\n"
					+ ";";
			
			// Connection 객체에 query 전달
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			// Query 결과 받기
			ResultSet rs = pstmt.executeQuery();
			
			Map<String, Integer> map =  new HashMap<String, Integer>();
			// 결과를 하나씩 가져오기
			while (rs.next()) {
				String orderNum = rs.getString("order_num");
				int orderMax = rs.getInt("order_max");
				map.put(orderNum, orderMax);
			}
			
			for (Entry<String, Integer> entrySet : map.entrySet()) {
				System.out.print("order_num : " + entrySet.getKey() + " / ");
				System.out.println("order_max : " + entrySet.getValue());
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
