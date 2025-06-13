<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="la.bean.PlusBean"%>

<%
	PlusBean bean = (PlusBean)request.getAttribute("plus");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Plus</title>
</head>
<body>

<%=bean.getNumber1()%>+<%=bean.getNumber2()%>=<%=bean.getAnswer()%>

</body>
</html>