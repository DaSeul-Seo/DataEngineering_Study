
public class ex02_throw {
	
	private static void isError1() throws MyException{
		System.out.println("isError1() 실행");
		throw new MyException();
	}
	
	private static void isError2() throws Exception{
		System.out.println("isError2() 실행");
		throw new Exception("부모 오류");
	}

	public static void main(String[] args) {
		try {
			isError2();
			isError1();
		}
		catch (MyException e) {
			System.out.println("첫 번째 catch");
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println("두 번째 catch");
			System.out.println(e.getMessage());
		}
	}
}
