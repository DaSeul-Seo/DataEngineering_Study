<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>From Result</title>
</head>
<body>
	<%
		// form태그에서 name 값
		String addr = request.getParameter("addr");
		// 공백제거
		addr = addr.strip();
		
		if (addr.equals("서울")) {
	%>
		<h3>addr : <%=addr %>, 대한민국 수도</h3>		
	<%	
		} else {
	%>
		<h3>addr: <%=addr %></h3>
	<%
		}
	%>
	
</body>
</html>