public class Fish implements Animals{
	
	public void Swim() {
		System.out.println("Fish : 물고기가 헤엄친다.");
	}

	@Override
	public void Animal() {
		System.out.println("Fish : 물고기 입니다.");
	}

	@Override
	public void Oviposit() {
		System.out.println("Fish : 물고기가 알을 낳는다.");
	}

	@Override
	public void Eat() {
		System.out.println("Fish : 물고기가 먹이를 먹는다.");
	}

	@Override
	public void See() {
		System.out.println("Fish : 물고기가 본다.");
	}
}
