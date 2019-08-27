package javastduy.week2;

public class AssultRifle extends Gun{

	public void Fire() {
		bulletCount-=5;
		System.out.printf("%s => => => => => , %d\n",model,bulletCount);
	}
	
	public AssultRifle(String model) {
		super(model);
		bulletCount=40;
	}
}
