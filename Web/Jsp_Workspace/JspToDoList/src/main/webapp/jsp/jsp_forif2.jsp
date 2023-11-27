<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />

<link rel="stylesheet" href="../css/index.css">

<title>To Do List Ex</title>
</head>
<body>
	<div class="app-container d-flex align-items-center justify-content-center flex-column">
		<h3>Todo App</h3>
		<form action="https://www.daum.net">
			<div class="d-flex align-items-center mb-3">
				<div class="form-group mr-3 mb-0">
					<input type="text" class="form-control" id="formGroupExampleInput" placeholder="Enter a task here" />
				</div>
				<button type="submit" class="btn btn-primary mr-3">Save</button>
				<button type="button" class="btn btn-warning">Get Tasks</button>
			</div>
		</form>

		<!-- To Do List 화면 -->
		<div class="table-wrapper">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>No.</th>
						<th>Todo item</th>
						<th>status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
						String taskName = "";
						String tableColor = "";
						String taskNameCss = "";
						String taskStatus = "";
       			for (int i = 1; i <= 5; i++) {
       				if (i % 2 == 0){
       					taskName = "짝수";
       					tableColor = "table-info";
       					taskNameCss = "task";
       					taskStatus = "In progress";
       				}
       				else{
       					taskName = "홀수";
       					tableColor = "table-danger";
       					taskNameCss = "complete";
       					taskStatus = "Completed";
       				}
       		%>
					<tr class=<%=tableColor %>>
						<td class=""><%=i %></td>
						<td class=<%=taskNameCss %>><%=taskName %></td>
						<td class=""><%=taskStatus %></td>
						<td><a href="https://www.google.com">
								<button class="btn btn-danger">Delete</button>
						</a> <a href="https://www.naver.com">
								<button class="btn btn-success">Finished</button>
						</a></td>
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