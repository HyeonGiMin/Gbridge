package javabasic1;

import java.util.Arrays;

public class Loop {

	public static void main(String[] args) {
		int power =13;
		String members[]= {"홍길동","김길동","김사랑","아무개"};
		

        // 0 ~ 9 까지 즉 10회 반복
        for(int i=0;i<10;i++) {
            System.out.println(i);
        }


		for(int i=0;i<members.length;i++) {
			System.out.println(members[i]);
		}
		
		System.out.println("------------------------");
		 // 배열 데이터 크기만큼 반복하면서 String 타입 데이터를 가지고 옴.
		for(String name:members) {
			System.out.println(name);
		}
		Arrays.asList(members).forEach(s -> System.out.println(s));
		
		
		// power > 10 보다 큰 경우에는 go() 함수를 호출해 동작시키고 power를 1감소
		while(power>10) {
			System.out.println("Go");
			power--;
		}
		System.out.println("Stop");
	}

}
