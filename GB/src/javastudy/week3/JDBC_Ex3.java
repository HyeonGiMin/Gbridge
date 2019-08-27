package javastudy.week3;

import java.io.*;
import java.sql.*;

public class JDBC_Ex3 {
	public static void main (String[] args) {
		Connection conn;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Seoul", "root","Aquea6725!"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			stmt.executeUpdate("insert into sInfo (s_name, s_no, year) values('아무개', '0893012', '3');"); // 레코드 추가
			printTable(stmt);
			stmt.executeUpdate("update sInfo set s_no='0189011' where s_name='아무개'"); //데이터 수정
			printTable(stmt);
			stmt.executeUpdate("delete from sInfo where s_name='아무개'"); // 레코드 삭제
			printTable(stmt);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류");
		}
	}
	// 레코드의 각 열의 값 화면에 출력
	private static void printTable(Statement stmt) throws SQLException {
		ResultSet srs = stmt.executeQuery("select * from sInfo");
		while (srs.next()) {
			System.out.print(srs.getString("s_name"));
			System.out.print("\t|\t" + srs.getString("s_no"));
			System.out.println("\t|\t" + srs.getString("year"));
		}
	}
}