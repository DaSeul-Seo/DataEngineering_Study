public class Shark extends Fish{
	
	@Override
	public void Swim() {
		System.out.println("Shark : 상어가 헤엄친다.");
	}
	
	@Override
	public void Eat() {
		System.out.println("Shark : 상어가 먹이를 잡아 먹는다.");
	}
}
