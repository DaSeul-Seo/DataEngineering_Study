
public class ex02 {
	public static void main(String[] args) {
		Car car = new Car("BMW", "Red", 4);
		
		System.out.println(car.getCompany());
		System.out.println(car.getColor());
		System.out.println(car.getWheels());
		
		System.out.println("color modify");
		car.setColor("Blue");
		System.out.println(car.getColor());
	}
}
