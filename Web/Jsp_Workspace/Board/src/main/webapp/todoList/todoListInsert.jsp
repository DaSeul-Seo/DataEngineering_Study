<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="ToDoList.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />

<link rel="stylesheet" href="../css/index.css">
<title>todoListInsert</title>
</head>
<body>
	<%
		ToDoList todoList = new ToDoList();
	
		String taskName = request.getParameter("todo_name");
		taskName = taskName.strip();
		
		int result = todoList.InsertData(taskName);
		
		String rstMsg = "";
		if (result < 0){
			rstMsg = "오류발생";
		}
		else{
			rstMsg = taskName + " 저장 성공";
		}
	%>
	<h3>결과 : <%=rstMsg %></h3>
	<a href="./todoList.jsp">To Do List 이동</a>
	
</body>
</html>