<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%!public class PersonBean {
		private String name;
		private int age;

		public PersonBean() {
		}

		public PersonBean(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getAge() {
			return age;
		}
	}
%>
<%
	ArrayList<PersonBean> list = new ArrayList<PersonBean>();
	PersonBean bean = new PersonBean("Tanaka", 30);
	list.add(bean);
	bean = new PersonBean("Yoshida", 45);
	list.add(bean);

	request.setAttribute("people", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Sample6</title>
</head>
<body>

	<table border="1">
	<tr><td>名前</td><td>年齢</td></tr>
	<tr><td>${people[0].name}</td><td>${people[0].age}</td></tr>
	<tr><td>${people[1].name}</td><td>${people[1].age}</td></tr>
	</table>

</body>
</html>