<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show All Items</title>
</head>
<body>

	<table border="1">
	<tr><td>NO</td><td>商品名</td><td>値段</td></tr>

	<%-- スコープに保存した「items」を使用して繰り返し処理を実行 --%>
	<c:forEach items="${items}" var="item">
		<%-- ItemBeanのフィールドのcode、name、priceを使用して画面表示 --%>
		<tr><td>${item.code}</td><td>${item.name}</td><td>${item.price}</td></tr>
	</c:forEach>
  
	</table>

</body>
</html>