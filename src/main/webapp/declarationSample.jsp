<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!
	int counter = 0;

	int getCounter() {
		counter++;
		return counter;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Declaration Sample</title>
</head>
<body>

あなたは、<%= getCounter() %>番目のアクセスです。
<%-- 
<% out.println("あなたは、" + getCounter() + "番目のアクセスです。");
--%> 

</body>
</html>