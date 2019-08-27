<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hello World</h2>
<hr>
오늘 날짜와 시간은 ; <%=java.time.LocalDateTime.now() %>
<hr>
이름 : <%=request.getParameter("name") %>
</body>
</html>