<%@ page import="Common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>From Result2</title>
</head>
<body>s
	<%
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		
		int rs_num1 = Integer.parseInt(num1);
		int rs_num2 = Integer.parseInt(num2);
		
// 		int sum = rs_num1 + rs_num2;
		
		Test test = new Test();
		int sum = test.SumAll(rs_num1, rs_num2);
	%>
	
	<h3><%=num1 %> + <%=num2 %> = <%=sum %></h3>
	
	
</body>
</html>