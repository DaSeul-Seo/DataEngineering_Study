
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
