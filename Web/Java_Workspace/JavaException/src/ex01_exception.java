
public class ex01_exception {
	public static void main(String[] args) {
		int a = 0;
		int b = 0;
		int c = 0;
		
		try {
			a = 10;
			b = 0;
			c = a / b;
		}
		catch(ArithmeticException e) {
			System.out.println(e.getMessage());
			
			c = -1;
		}
		finally {
			System.out.println(a);
		}
	}
}
