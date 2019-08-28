<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="inc_header.html" %>
<meta charset="UTF-8">
<title>Tweet List</title>
</head>
<body>
	<div class="container mx-auto m-5 p-5 w-60 shadow">
		<h3>My Simple Twitter!!</h3>
		<hr>
		<form action="/JavaWeb/twitter" method="post">
		<input type="hidden" name="action" value="tweet" />
			<div class="input-group w-75">
			<button type="button" class="btn btn-outline-success">@${user}</button>&nbsp;
			<input class="form-control" type="text" name="msg">
			<input type="submit" class="btn btn-warning" value="Tweet" />&nbsp;
			<a class="btn btn-secondary" href="/JavaWeb/twitter">Sign out</a>
			</div>
		</form>		
		<hr>
		<ul class="list-group">
		<c:forEach var="msg" items="${msgs}">
			<li class="list-group-item list-froup-item-action">${msg}</li>
		</c:forEach>
		</ul>
	</div>
</body>
</html>