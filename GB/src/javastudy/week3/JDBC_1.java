package javastudy.week3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class JDBC_1 {
	
	Connection conn;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Scanner sc=null;
	String sel;
	String sql;
	
	public static void main (String[] args) {
		JDBC_1 jb=new JDBC_1();
		jb.sqlConnect();
	}
	
	public JDBC_1() {
	
		try {
			//Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root","1234"); // JDBC 연결
			String dbURL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(dbURL, "root","1234");
			sc=new Scanner(System.in);
			System.out.println("DB 연결 완료 ---- > OK !!!");
			
			System.out.println("조회 시스템 실행");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sqlConnect() {
	
			while(true){
				System.out.println("====================");
				System.out.println("1-----학생정보");
				System.out.println("2-----교수정보");
				System.out.println("3-----수강성적");
				System.out.println("4-----개설교과");
				System.out.println("q-----종료");
				System.out.print("선택: ");
				sel=sc.next();
				
				switch(sel){
					case "1":
						showStudent();
						break;
					case "2":
						showProfessor();
						break;
					case "3":
						showGrade();
						break;
					case "4":
						showClass();
						break;
					case "q":
						System.out.println("프로그램을 종료합니다");
						try {
							
							sc.close();
							pstmt.close();
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {

							System.exit(0);
						}
						break;
					default:
						System.out.println("잘 몰 입력하셨습니다.");
						
				}
			
			}

			
	}

	public void showClass() {
		sql="select * from class";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println("수업코드   수업이름");
			while(rs.next()) {
				System.out.println(rs.getString("c_code")+"        "+rs.getString("c_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showStudent() {
		String choice=null;
		System.out.print("조회하고 싶은 학생의 이름을 입력하세요(a=전체 조회): ");
		choice=sc.next();
		
		try {
			if(choice=="a") {
				sql="select * from sInfo";
				pstmt=conn.prepareStatement(sql);
			}
			else {
				sql="select * from sInfo where s_name=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, choice);
			}
			rs=pstmt.executeQuery();
			System.out.println("학생코드    학생이름   학년   지도교수");
			while(rs.next()) {
				System.out.println(rs.getString("s_no")+"        "+rs.getString("s_name")+"        "+rs.getString("year")+"       "+rs.getString("p_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showProfessor() {
		
		String choice=null;
		System.out.print("조회하고 싶은 교수님의 이름을 입력하세요(a=전체 조회): ");
		choice=sc.next();
		try {
			System.out.println(choice);
			
		if(choice=="a") {
			sql="select * from pInfo where p_name=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, choice);
			System.out.println("1");
		}else {
		
			sql="select * from pInfo";
			pstmt=conn.prepareStatement(sql);
			System.out.println(choice);
		}
		
			rs=pstmt.executeQuery();
			System.out.println("교수코드    교수이름      학과");
			while(rs.next()) {
				System.out.println(rs.getString("p_code")+"        "+rs.getString("p_name")+"       "+rs.getString("dept"));
			}
			
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showGrade() {
		
		String choice=null;
		System.out.print("조회하고 싶은 학생의 이름을 입력하세요(a=전체 조회): ");
		choice=sc.next();
		try {
			
			if(choice=="a") {
				sql="select * from greade";
				pstmt=conn.prepareStatement(sql);
			}else {
				//sql="select * from grade where s_no in (select s_no from sInfo where s_name=?)";
				sql="select grade.s_no,grade.h_code,grade.degree,sInfo.s_name from grade,sInfo where grade.s_no=sInfo.s_no and grade.s_no in (select s_no from sInfo where s_name=?)";
				pstmt=conn.prepareStatement(sql);
				System.out.println(choice);
				pstmt.setString(1, choice);
			}
			
			rs=pstmt.executeQuery();
			System.out.println("수업코드  학생코드         성적     이름");
			while(rs.next()) {
				System.out.println(rs.getString("h_code")+"     "+rs.getString("s_no")+"        "+rs.getString("degree")+"      "+rs.getString("s_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}