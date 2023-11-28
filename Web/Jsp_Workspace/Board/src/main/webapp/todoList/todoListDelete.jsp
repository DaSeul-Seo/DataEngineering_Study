<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="ToDoList.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoListDelete</title>
</head>
<body>
	<%
		ToDoList todoList = new ToDoList();
	
		String taskName = request.getParameter("todo_name");
		taskName = taskName.strip();
		
		int result = todoList.DeleteData(taskName);
		
		String rstMsg = "";
		if (result < 0){
			rstMsg = "오류발생";
		}
		else{
			rstMsg = taskName + " 삭제 성공";
		}
	%>
	<h3>결과 : <%=rstMsg %></h3>
	<a href="./todoList.jsp">To Do List 이동</a>
</body>
</html>