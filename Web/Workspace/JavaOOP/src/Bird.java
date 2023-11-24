public class Bird implements Animals{

	public void Fly() {
		System.out.println("Bird : 새가 날다.");
	}

	@Override
	public void Animal() {
		System.out.println("Bird : 새 입니다.");
	}

	@Override
	public void Oviposit() {
		System.out.println("Bird : 알을 낳는다.");
	}

	@Override
	public void Eat() {
		System.out.println("Bird : 먹이를 먹는다.");
	}

	@Override
	public void See() {
		System.out.println("Bird : 보다.");
	}
}
