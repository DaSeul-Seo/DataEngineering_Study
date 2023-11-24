public class ex01 {
	public static void main(String[] args) {
		Car car1 = new Car(); // 기본 생성자
		Car car2 = new Car("BMW", "Red", 4);
		
		System.out.println("첫번쨰 자동차 시동 걸기");
		car1.startEngine();
		int move_num1 = car1.moveForward(10);
		
		System.out.println("두번쨰 자동차 시동 걸기");
		car2.startEngine();
		int move_num2 = car2.moveForward(20);
		
	}
}
