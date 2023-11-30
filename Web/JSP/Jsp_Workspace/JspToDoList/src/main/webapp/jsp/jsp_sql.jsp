<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP DB Conn</title>
</head>
<body>	
	<%
		String user1 = "jdbc:mysql://localhost:3306/classicmodels";
		String id = "test";
		String pw = "test1234";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String className = "com.mysql.jdbc.Driver";
		
		// Connection 객체 만들기
		try {
			// MySQL 드라이버 생성
			Class.forName(className);
			// DB Connection 생성
			conn = DriverManager.getConnection(user1, id, pw);
			System.out.println("DB Connection Success");
			
			// Query
			String query = "SELECT customerNumber, city, country FROM customers WHERE 1=1 LIMIT 5;";
			
			// Connection 객체에 query 전달
			pstmt = conn.prepareStatement(query);
			
			// Query 결과 받기
			rs = pstmt.executeQuery();
			
			// 결과를 하나씩 가젼오기
			while (rs.next()) {
				String customerNumber = rs.getString("customerNumber");
				String city = rs.getString("city");
				String country = rs.getString("country");
				
				// 자바 코드 끝나는 부분
				%>
				<!-- html 코드 공간 -->
				<p> customerName : <%=customerNumber %></p>
				<p> city : <%=city %></p>
				<p> country : <%=country %></p>
				
				<%
				// 자바 보드 시작하는 부분
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
		finally {
			if(rs != null) {
				try { rs.close(); } 
				catch (SQLException e) { e.printStackTrace(); }
			}
			
			if(pstmt != null) {
				try { pstmt.close(); } 
				catch (SQLException e) { e.printStackTrace(); }
			}
			
			if(conn != null) {
				try { conn.close(); } 
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
	%>

</body>
</html>