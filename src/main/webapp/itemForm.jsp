<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

ソート：
<a href="/jmaster/ItemServlet2?action=sort&key=price_asc">値段の低い順</a> ,
<a href="/jmaster/ItemServlet2?action=sort&key=price_desc">値段の高い順</a><br>

<form action="/jmaster/ItemServlet2" method="post">
	追加：商品名<input type="text" name="name">
	価格<input type="text" name="price" size="5">を<button>追加</button>
	<input type="hidden" name="action" value="add">
</form>

<form action="/jmaster/ItemServlet2" method="post">
	検索：商品名<input type="text" name="itemName" size="5" value="${sessionScope.itemName}">
	価格<input type="text" name="minPrice" size="5" value="${sessionScope.minPrice}">円以上
	<input type="text" name="maxPrice" size="5" value="${sessionScope.maxPrice}">円以下の商品を
	<button>検索</button>
	<input type="hidden" name="action" value="search">
</form>

<form action="/jmaster/ItemServlet2" method="post">
	削除：商品番号<input type="text" name="code" size="5">
	番の商品を<button>削除</button>
	<input type="hidden" name="action" value="delete">
</form>
