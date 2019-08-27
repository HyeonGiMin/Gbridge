<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		
		if((uid.equals("hong"))&&(pwd.equals("1234"))) {
			out.println("로그인 성공!!");
		}else {
			out.println("아이디와 비밀번호가 틀렸습니다.!!");
		}
%>
</body>
</html>