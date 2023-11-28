package Common;

import java.sql.*;
import java.util.*;

public class JdbcUtil {
	private static String url = "jdbc:mysql://localhost:3306/examplesdb";
	private static String id = "urstory";
	private static String pw = "u1234";
	private static String className = "com.mysql.jdbc.Driver";

	private static Connection conn;
	private static JdbcUtil instance = new JdbcUtil();
	
	static {
		try {
			Class.forName(className);
			// connection 생성 
			conn = DriverManager
					.getConnection(url, id, pw);
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
	
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	private void closeAll(
			Connection conn
			, PreparedStatement pstmt
			, ResultSet rs) {
		
		if(rs != null) {
			try { rs.close(); } 
			catch (SQLException e) { e.printStackTrace(); }
		}
		
		if(pstmt != null) {
			try { pstmt.close(); } 
			catch (SQLException e) { e.printStackTrace(); }
		}
		
//		if(conn != null) {
//			try { conn.close(); } 
//			catch (SQLException e) { e.printStackTrace(); }
//		}
	}
	
	public int insertOrUpdate(String query) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = this.conn.prepareStatement(query);
			result = pstmt.executeUpdate();
//			this.conn.commit();
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			this.closeAll(conn, pstmt, rs);
			
		}
		return result;
	}

	public List<Map> selectBySql(String query) {
		List<Map> lst = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = this.conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<>();
				
				for(int i=1;i<=columnCnt;i++) {
					String colNm = rsmd.getColumnName(i);
					map.put(colNm, rs.getObject(colNm));
				}
				lst.add(map);
			}
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return lst;
	}
}
