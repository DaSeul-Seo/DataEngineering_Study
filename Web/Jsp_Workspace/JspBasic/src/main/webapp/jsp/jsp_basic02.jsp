<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 선언문</title>
</head>
<body>
	<%!
		// Method 정의하는 영역		
		public int sumAll (int num){
			int total = 0;
			for (int i = 0; i <= num; i++){
				total += i;
			}
			return total;
		}
	
	%>
	
	<%
		// 스클립틀릿 : 자바 연산, 처리하는 영역
		int num = 10;
		int total = sumAll(num);
		System.out.println("total : " + total);
	%>

	<h1>1부터 <%= num %> 까지의 합은 <%= total %></h1>
</body>
</html>