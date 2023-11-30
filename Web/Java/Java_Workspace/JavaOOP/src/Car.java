// 클래스 정의
public class Car {
	// 속성 (데이터)
	private String company;
	private String color;
	private int wheels;
	
	// 생성자
	public Car() {} // 기본 생성자 (생략 가능)
	
	public Car(String company, String color, int wheels) {
		this.company = company;
		this.color = color;
		this.wheels = wheels;
	}
	
	// 함수 (기능)
	public void startEngine() {
		System.out.println("부릉부릉 ~~ ");
	}
	
	public int moveForward(int num) {
		int move_num = num * 10;
		System.out.println("앞으로 " + move_num + "만큼 이동");
		
		return move_num;
	}
	
	private void openWindow() {
		System.out.println("비밀 스킬을 습득했습니다. ^^");
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

}
