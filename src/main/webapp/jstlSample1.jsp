<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("age", "17");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Sample1</title>
</head>
<body>

<c:if test="${age >= 18}">
  成年です。
</c:if>

<c:if test="${age < 18}">
  未成年です。
</c:if>

</body>
</html>