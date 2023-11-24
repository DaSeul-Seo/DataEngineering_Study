
public class MyException extends Exception{
	
	public MyException() {
		System.out.println("나의 첫번째 Java Exception Class 이다.");
	}

	public MyException(String msg) {
		super(msg);
	}

}
