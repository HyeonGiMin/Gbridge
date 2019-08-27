package javastduy.week2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Socket s=new Socket("192.168.152.1",5000);
			System.out.println("## 클라이언트 실행");
			
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
			Scanner scan=new Scanner(System.in);
			System.out.print("Message Input: ");
			String msg=scan.next();
			pw.println("[민현기] "+msg);
			pw.close();
			s.close();
			scan.close();
			System.out.println("## 클라이언트 종료");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
