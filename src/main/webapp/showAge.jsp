<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Age</title>
</head>
<body>

<c:if test="${not empty person.name}">
  ${person.name}さんの年齢は
</c:if>

<h3>${person.age}歳</h3>
です。

</body>
</html>