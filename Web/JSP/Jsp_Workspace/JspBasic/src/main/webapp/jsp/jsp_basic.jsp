<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 기본 문법</title>
</head>
<body>
	<%
		// 자바 코드 작성
		// 스크립틀릿 : 자바로 연산, 처리 등을 할 수 있다.
		String str = "스크립틀릿 입니다.";
		int num = 1;
		num *= 5;
		/* System.out.println("num : " + num); */
	%>
	
	<!-- 자바 변수(스크립틀릿)을 html 문서로 표현 -->
	<h1>num : <%= num %></h1>
	
</body>
</html>