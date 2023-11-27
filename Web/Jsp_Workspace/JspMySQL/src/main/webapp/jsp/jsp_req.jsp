<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTTP 요청 정보</title>
</head>
<body>
	<h1>jsp_req</h1>
	요청정보 인코딩 (getCharacterEncoding) : <%=request.getCharacterEncoding() %> <br />
	요청정보 전송 방식 (getMethod) : <%=request.getMethod() %> <br />
	요청 URL (getRequestURL) : <%=request.getRequestURL() %> <br />
	Context Path (getContextPath) : <%=request.getContextPath() %> <br />
	서버 이름 (getServerName) : <%=request.getServerName() %> <br />
	서버 포트 (getServerPort) : <%=request.getServerPort() %> <br />
	
	<a href="<%=request.getContextPath() %>/index.jsp">뒤로가기</a> <br />
</body>
</html>