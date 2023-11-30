### Runtime Memory

- Method
    - static
    - 상수 ⇒ Unique한 값
    - 프로그램 ㅌ코드
        - Class → 실행 시, Stack → new 연산자를 통해 Instance가 됨.
- Stack
    - Function
- Heap
    - Instance

### Singleton Pattern

- 클래스 하나 당 하나의 Instance를 만든다.
    - 재사용
- 단점
    - 사용하지 않으면 GC가 처리할 수 있다.

### static

- Instance 생성없이 필드와 메소드를 생성하고자 할 때 활용
- 필드와 메소드를 객체마다 다르게 가져야 한다면 인스턴스로 생성
- 공통 변수, 인스턴스 필드를 포함하지 않는 메소드를 선언하고자 할 때 사용
- 객체없이 바로 사용 가능
    
    ```java
    public class PlusClass{
      static int field1 = 15;
    
      static int plusMethod(int x, int y){ return x+y; } 
    }
    ```
    
    ```java
    int ans1 = PlusClass.plusMethod(15,2);
    int ans2 = PlusClass.field1 + 2;
    ```
    

### final

- 선언되면 수정이 불가능

### static final

- 모든 영역에서 고정된 값으로 사용하는 상수

### DB Pool Module

- JdbcUtil
    
    ```java
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
    ```
    
- Main
    
    ```java
    public class ex01_jdbcConn {
    	public static void main(String[] args) {
    		JdbcUtil ju = JdbcUtil.getInstance();
    		
    		String selectQuery = 
    				"""
    				CREATE TABLE mytable (
    					id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    					name VARCHAR(50) NOT NULL,
    					modelnumber VARCHAR(15) NOT NULL,
    					series VARCHAR(30) NOT NULL,
    					PRIMARY KEY(id)
    				);
    				""";
    		String insertQuery = 
    				"""
    				insert into mytable(name, modelnumber, series) values('name1', '11111', '111');
    				""";
    		
    		String updateQuery = 
    				"""
    				update mytable set modelnumber = '22222', series = '222' where id = 1;
    				""";
    		int rst = ju.InsertOrUpdate(insertQuery);
    		System.out.println(rst);
    	}
    }
    ```
    

<aside>
💡 Reference

</aside>

- static
    - https://gobae.tistory.com/3