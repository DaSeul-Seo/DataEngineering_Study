import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
	private static String className = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/examplesdb";
	private static String id = "urstory";
	private static String pw = "u1234";
	
	private static Connection conn;
	// Instance를 한번만 생성하고 재사용
	private static JdbcUtil instance = new JdbcUtil();
	
	static {
		try {
			// MySQL 드라이버 생성
			Class.forName(className);
			// DB Connection 생성
			conn = DriverManager.getConnection(url, id, pw);
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
	
	// 외부에서 JdbcUtil 객체를 사용하기 위해
	public static  JdbcUtil getInstance() {
		return instance;
	}
	
	// 외부에서 connection 사용하기 위해 
	public Connection getConnection() {
		return conn;
	}
	
	// 연결해제
	public void closeAll (Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {rs.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		
		if(pstmt != null) {
			try {pstmt.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
		
		if(conn != null) {
			try {conn.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	// select문
	public List<Map<String, Object>> SelectAll(String query) {
		List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = JdbcUtil.conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				
				for(int i = 1; i <= columnCnt; i++) {
					String colNm = rsmd.getColumnName(i);
					map.put(colNm, rs.getObject(colNm));
				}
				rstList.add(map);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return rstList;
	}
	
	// Insert / Update
	public int InsertOrUpdate(String query) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = JdbcUtil.conn.prepareStatement(query);
			result = pstmt.executeUpdate();
			JdbcUtil.conn.commit();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			this.closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
}
