<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashMap"%>

<%
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("HTML", "Hyper Text Markup Language");
	map.put("JSP", "Java Server Pages");
	map.put("JSTL", "JSP Standard Tag Library");

	request.setAttribute("terms", map);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Sample3_2</title>
</head>
<body>


	<c:forEach items="${terms}" var="word">
		${word.key}:${word.value}<br>
	</c:forEach>

</body>
</html>