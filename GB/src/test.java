
import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random rm=new Random();

       

        for(int i=0;i<100;i++){
        	 int ran=rm.nextInt(50)+49;
            System.out.println(ran);
        }

    }

}
