
public class Shark extends Fish {
	
	public Shark() {
		this.speed = 50;
	}
	public Shark(int speed) {
		if (speed < 50) {
			this.speed = 50;
		}
		else {
			this.speed = speed;
		}
	}
	
	@Override
	public String eat(String food) {
		// TODO Auto-generated method stub
		System.out.println(food+"를 먹었지만, 더 먹고 싶다.");
		return "티모 어디있니??";
	}
	
	
}
