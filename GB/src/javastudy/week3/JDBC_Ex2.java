package javastudy.week3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class JDBC_Ex2 {
	public static void main (String[] args) {
		Connection conn;
		Statement stmt = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root","1234"); // JDBC 연결
			String dbURL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(dbURL, "root","Aquea6725!");
			
			System.out.println("DB 연결 완료 ---- > OK !!!");
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			ResultSet srs = stmt.executeQuery("select * from pInfo"); // 테이블의 모든 데이터 검색
			printData(srs, "교수코드", "교수이름", "학과");
			String SQL = "select p_code, p_name, dept from pInfo where p_code= 'p200' ";
			srs = stmt.executeQuery(SQL); // name이 "이기자"인 레코드만 검색
			printData(srs, "교수코드", "교수이름", "학과");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행오류");
		} 
	}
	// 레코드의 각 열의 값 화면에 출력
		private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
			while (srs.next()) {
				if (!col1.equals(""))
					System.out.print(srs.getString("p_code")); 
				if (!col2.equals(""))
					System.out.print("\t|\t" + srs.getString("p_name"));
				if (!col3.equals(""))
					System.out.print("\t|\t" + srs.getString("dept"));
				else 
					System.out.println("\n");
				
				System.out.println("\n");
			}
		}
}