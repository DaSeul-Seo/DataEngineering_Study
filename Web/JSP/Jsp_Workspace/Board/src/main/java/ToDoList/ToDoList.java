package ToDoList;

import java.util.*;

import Common.JdbcUtil;

public class ToDoList {
	public List<Map> SelectList() {
		JdbcUtil ju = JdbcUtil.getInstance();
		
		String query = """
				SELECT * FROM todolist;
				""";
		
		List<Map> todoList = ju.selectBySql(query);
		
		return todoList;
	}
	
	public int InsertData(String todoName) {
		JdbcUtil ju = JdbcUtil.getInstance();
		int rst = 0;
		
		String query = """
				INSERT todolist(todo_name, todo_status) VALUES ("%s", "false");
				""";
		query = String.format(query, todoName);
		
		rst = ju.insertOrUpdate(query);
		return rst;
	}
	
	public int UpdateData(String todoName) {
		JdbcUtil ju = JdbcUtil.getInstance();
		int rst = 0;
		
		String query = """
				UPDATE todolist set todo_status = "true" where todo_name = "%s";
				""";
		query = String.format(query, todoName);
		
		rst = ju.insertOrUpdate(query);
		return rst;
	}
	
	public int DeleteData(String todoName) {
		JdbcUtil ju = JdbcUtil.getInstance();
		int rst = 0;
		
		String query = """
				DELETE FROM todolist WHERE todo_name = "%s";
				""";
		query = String.format(query, todoName);
		rst = ju.insertOrUpdate(query);
		return rst;
	}
}
