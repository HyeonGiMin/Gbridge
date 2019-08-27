package javastduy.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileEditor {

	public static void main(String[] args) {
		System.out.println("## 간단 메모장 v0.1 ##");
		System.out.printf("## 저장할 파일명: ");
		Scanner scan=new Scanner(System.in);
		String filename=scan.next();
		System.out.println("## 저장은 마지막 라인에 q입력");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter("/Users/hyeon/Documents/Temp/"+filename));
			String s;
			while(!(s = reader.readLine()).equals("q")) {
				writer.write(s+"\n");
			}
			reader.close();
			writer.close();
			scan.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.printf("## 파일이 저장 되었습니다. 프로그램을 종료합니다.!!");

	}

}
