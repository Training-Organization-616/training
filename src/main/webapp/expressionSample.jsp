<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expression Sample</title>
</head>
<body>

	<%
		int i = 10;
		LocalDate today = LocalDate.now();
	%>

	<h2>式で値を表示</h2>
	iの値は<%= i %>です。
	<br> todayの値は<%= today %>です。

</body>
</html>