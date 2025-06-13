<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	ArrayList<String> l = new ArrayList<String>();
	l.add("One");
	l.add("Two");
	l.add("Three");
	request.setAttribute("data", l);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show ArrayList</title>
</head>
<body>

	ArrayListの中身は
	<br> ${data[0]}
	<br> ${data[1]}
	<br> ${data[2]}
	<br> です。

</body>
</html>