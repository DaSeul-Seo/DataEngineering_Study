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
<title>TodoList</title>
</head>
<body>
	<%
		ToDoList todoList = new ToDoList();
		List<Map> rstList = todoList.SelectList();
		
		System.out.println("List Size : " + rstList.size());
	%>
	<div class="app-container d-flex align-items-center justify-content-center flex-column">
		<h3>To do List</h3> <br />
		<h5>total : <%=rstList.size() %></h5> <br />
		<br />
		<form action="./todoListInsert.jsp" method="get">
			<div class="d-flex align-items-center mb-3">
				<div class="form-group mr-3 mb-0">
					<input 
						type="text" 
						class="form-control" 
						name="todo_name"
						id="formGroupExampleInput" 
						placeholder="Enter a task here" />
				</div>
				<button type="submit" class="btn btn-primary mr-3">Save</button>
			</div>
		</form>
		
		<div class="table-wrapper">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>todo_id</th>
						<th>todo_name</th>
						<th>todo_status</th>
					</tr>
				</thead>
				
				<tbody>
					<%
						Map<String, Object> map = new HashMap<String, Object>();
						String td_css = "";
						String tr_css = "";
						for (int i = 0; i < rstList.size(); i++){
								map = rstList.get(i);
								if (map.get("todo_status").equals("true")){
									td_css = "complete";
             			tr_css = "table-danger";
								}
								else{
									td_css = "task";
	                tr_css = "table-info";
								}
					%>
					<tr class=<%=tr_css %>>
						<td><%=map.get("todo_id") %></td>
						<td class=<%=td_css %>><%=map.get("todo_name") %></td>
						<td><%=map.get("todo_status") %></td>
						<td>
							<a href="./todoListDelete.jsp?todo_name=<%=map.get("todo_name") %>">
								<button
									type="button"
									class="btn btn-danger"
									name="todo_name">Delete</button>
              </a>
              <a href="./todoListUpdate.jsp?todo_name=<%=map.get("todo_name") %>">
                <button 
                	type="button"
                	class="btn btn-success"
                	name="todo_name"
                	onclick="">Finished</button>
              </a>
            </td>
					</tr>
					<%				
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>