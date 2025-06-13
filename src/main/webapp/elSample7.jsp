<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
  HashMap<String, String> m = new HashMap<String, String>();
  m.put("yamada", "090-1111-1111");
  m.put("kimura", "090-2222-2222");
  m.put("tanaka", "090-3333-3333");

  session.setAttribute("tel", m);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Sample7</title>
</head>
<body>

	電話番号は以下の通り
	<br> 山田さんは${tel.yamada}
	<br> 木村さんは${tel.kimura}
	<br> 田中さんは${tel.tanaka}
	<br> です。

</body>
</html>