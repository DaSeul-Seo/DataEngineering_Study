
public class App {

	public static void main(String[] args) {
		Bird bird = new Bird();
		Peacock peacock = new Peacock();
		Eagle eagle = new Eagle();
		
		Fish fish = new Fish();
		Nimo nimo = new Nimo();
		Shark shark = new Shark();
		
		System.out.println("========== Bird ==========");
		bird.Eat();
		bird.Fly();
		bird.Oviposit();
		
		peacock.Fly();
		peacock.Woo();
		peacock.See();
		
		eagle.Oviposit();
		eagle.Eat();
		eagle.Fly();
		
		System.out.println("========== Fish ==========");
		fish.Swim();
		fish.See();
		fish.Oviposit();
		
		nimo.Eat();
		nimo.See();
		nimo.Hide();
		
		shark.Eat();
		shark.Oviposit();
		shark.Swim();
		
	}

}
