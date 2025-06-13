<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("value1", "This is pageScope data");
	application.setAttribute("value2", "This is applicationScope data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Sample2</title>
</head>
<body>

<h3>value1は${pageScope.value1}です</h3>
<!-- データの取得先のスコープがあっていなければ、名前が一致してもデータを取得できない -->
<h3>value2は${requestScope.value2}です</h3>

<h3>value2は${value2}です</h3>

</body>
</html>