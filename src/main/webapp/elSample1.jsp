<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setAttribute("data1", "This is sample1");
	request.setAttribute("data2", "This is Request2");
	session.setAttribute("data2", "This is Session2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Sample1</title>
</head>
<body>

<h3>data1は${data1}です</h3>
<h3>data2は${data2}です</h3>
<h3>data2は${data2}です</h3>
<h3>data3は${data3}です</h3>

</body>
</html>