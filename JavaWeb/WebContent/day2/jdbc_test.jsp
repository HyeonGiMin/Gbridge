<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<% 
	request.setCharacterEncoding("utf-8"); 
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	String jdbc_driver= "com.mysql.cj.jdbc.Driver";
	String jdbc_url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Seoul";

	try{
		Class.forName(jdbc_driver);
		
		conn=DriverManager.getConnection(jdbc_url,"root","1234");
		
		String sql="insert into jdbc_test values(?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,request.getParameter("username"));
		pstmt.setString(2,request.getParameter("email"));
		
		if(request.getParameter("username")!=null){
			pstmt.executeUpdate();
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC 테스트</title>
</head>
<body>
<div align="center">
<h2>이벤트 등록</h2>
<hr>
<form name=form1 method=post action=jdbc_test.jsp>
등록이름 : <input type=text name=username>
email주소 : <input type=text name=email size=20>
<input type=submit value="등록">
</form> 
<hr>
</div>
# 등록 목록<p>
<%
	String sql="select username,email from jdbc_test";

	pstmt=conn.prepareStatement(sql);
	
	ResultSet rs=pstmt.executeQuery();
	int i=1;
	
	while(rs.next()){
		out.println(i+" : "+rs.getString("username")+" , "+rs.getString("email")+"<br>");
		i++;
	}
	
	rs.close();
	pstmt.close();
	conn.close();
 %>
</body>
</html>