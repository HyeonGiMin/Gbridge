package javastduy.week2;

public class ShotGun extends Gun {

	public ShotGun(String model) {
		super(model);
	}
	
	public  void fire() {
		bulletCount-=1;
		System.out.printf("%s =}}} , %d\n", model,bulletCount);
	}
}
