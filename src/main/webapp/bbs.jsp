<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
	//取得するパラメータの文字コードを設定
	request.setCharacterEncoding("UTF-8");
	// パラメータの取得
	String message = request.getParameter("message");
	String name = request.getParameter("name");

	// セッション領域から投稿リストの取得
	List<String> articles = (ArrayList<String>)session.getAttribute("articles");
	if (articles == null) {
		// 初めてのときは投稿リストを作成
		articles = new ArrayList<String>();
		session.setAttribute("articles", articles);
	}
	// messageが取得できたときだけ、投稿リストに追加する
	if (message != null && message.length() != 0) {
		articles.add(name + "：" + message);
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>掲示板</title>
</head>
<body>
	<form action='/jmaster/bbs.jsp' method='post'>
		名前：<br> <input type="text" name="name"><br> メッセージ：<br>
		<textarea name='message' cols='40' rows='5'></textarea>
		<br> <button>書き込み</button>
	</form>
	<hr>
	<%
		// 繰り返し処理を利用して投稿リストを表示する
		for (String article : articles) {
	%>
			<%= article %>
			<hr>
	<%
		}
	%>
</body>
</html>