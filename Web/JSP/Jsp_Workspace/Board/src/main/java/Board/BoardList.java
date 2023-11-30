package Board;

import java.util.*;

import Common.JdbcUtil;

public class BoardList {
	
	public List<Map> SelectBoard() {
		JdbcUtil ju = JdbcUtil.getInstance();
		
		String query = """
				SELECT * FROM board;
				""";
		
		List<Map> boardList = ju.selectBySql(query);
		
		return boardList;
	}
}
