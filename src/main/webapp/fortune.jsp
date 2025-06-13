<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>占い</title>
	</head>
	<body>
		<h1>占いたい月を入力してね</h1>
		<form action="FortuneServlet" method="get">
			<select name="month">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
				<option>6</option>
				<option>7</option>
				<option>8</option>
				<option>9</option>
				<option>10</option>
				<option>11</option>
				<option>12</option>
			</select>月
			<button>占ってみる</button>
		</form>
		<br>
		<!-- 以下は初期非表示。占ってみるボタンクリックで表示制御 -->
		<c:if test="${not empty result}">
			${result.month}月の運勢は、、、<br>
			・ラッキーカラー：${result.color}<br>
			・ラッキーアイテム：${result.item}<br>
			・順位は：${result.rank}位<br>
		</c:if>
	</body>
</html>