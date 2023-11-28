<%@page import="java.util.*"%>
<%@ page import="Board.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
</head>
<body>
	<%
		BoardList boardList = new BoardList();
		List<Map> rstList = boardList.SelectBoard();
		
		System.out.println("List Size : " + rstList.size());
	%>
	
	<h3>Board Size : <%=rstList.size() %></h3>
	<table border="1">
		<thead>
			<tr>
				<th>No.</th>
				<th>Title</th>
				<th>Content</th>
			</tr>
		</thead>
		<tbody>
			<%
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < rstList.size(); i++){
					map = rstList.get(i);
			%>
			<tr>
				<td><%=map.get("id") %></td>
				<td><%=map.get("title") %></td>
				<td><%=map.get("content") %></td>
			</tr>
			<%				
				}
			%>
		</tbody>
	</table>
</body>
</html>