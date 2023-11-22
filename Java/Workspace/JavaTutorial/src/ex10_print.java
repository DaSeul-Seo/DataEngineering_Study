
public class ex10_print {

	public static void main(String[] args) {
		System.out.println("Hello world");
		System.out.println("Hello world");

		System.out.println("=============================");
		
		System.out.print("world ");
		System.out.print("world ");
		
		// %s: 문자열 
		// %d: 정수 
		// %f: 실수 
		// \n: 줄바꿈 
		System.out.printf("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.\n", "홍길동", 20, 180.5f);

		String str2 = String.format("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.\n", "신사임당", 20, 180.5f); 
		System.out.println(str2);
		
	}

}
