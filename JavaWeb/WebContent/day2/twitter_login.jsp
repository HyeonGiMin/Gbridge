<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="inc_header.html" %>
<meta charset="UTF-8">
<title>Twitter Login</title>
</head>
<body>
	<div class="container mx-auto m-5 p-5 bg-info w-50 shadow">
		<h2>Twitter:Login</h2>
		<form action="/JavaWeb/twitter" method="post">
		<input type="hidden" name="action" value="login" />
		<div class="input-group">
		<input name="username" type="text" class="form-control" placeholder="login id"/>
		<input type="submit" class="btn btn-warning" value="Login" />
		</div>
		</form>		
	</div>
</body>
</html>