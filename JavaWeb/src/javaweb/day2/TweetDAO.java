package javaweb.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TweetDAO {
	
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	
	private String jdbc_driver= "com.mysql.cj.jdbc.Driver";
	private String jdbc_url="jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Seoul";
	
	public void Connect() {
		try{
			Class.forName(jdbc_driver);
			
			conn=DriverManager.getConnection(jdbc_url,"testDB","gachon654321");

			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void Disconnect() throws SQLException {
	
			conn.close();
	}
	
	public void insertDB(String msg) throws SQLException {
		
		String sql="insert into tweet values(?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,msg);
		
		if(msg!=null){
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	public List<String> getDB() throws SQLException{
		
		List<String> msgs = new ArrayList<String>();
		String sql="select msg from tweet";
		pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			msgs.add(rs.getString("msg"));
			System.out.println(rs.getString("msg"));
		}
		
		pstmt.close();
		rs.close();
		return msgs;
		
	}
}


