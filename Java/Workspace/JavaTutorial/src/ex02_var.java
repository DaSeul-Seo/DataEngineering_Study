
public class ex02_var {

	public static void main(String[] args) {
		// 변수 : 변하는 수
		// int형
		 
		int a = 30;
		System.out.println(a);
		  
		a = 40;
		System.out.println(a);
	
		// 형변환
		// 정수형 (long, int, short, byte)
		int x = 30;
		long y = 40L;
		
		System.out.println(x);
		System.out.println(y);
		System.out.println("==============형변환(정수형)==============");

		// 형변환
		long y1 = x; // long > int
		// long이 int보다 큰 범위를 표현할 수 있으므로 형변환을 할 필요 없음 (자동 형변환)
		int x1 = (int)y;
		System.out.println(x1);
		System.out.println(y1);
		

		System.out.println("==============형변환(실수형)==============");
		// 실수형 (double > float)
		double dd = 30.0;
		float ff = 30.0f; // f를 빼면 double로 인식!!

		dd = ff; // 자동 형변환 
		ff = (float) dd; // 형변환 필요!!
		
		System.out.println(dd);
		System.out.println(ff);

		System.out.println("==============boolean==============");
		// 불리언형  
		boolean bool = true; 
		System.out.println(bool);

		bool = false; 
		System.out.println(bool);

		System.out.println("==============String==============");
		// 문자형 
		char c = '한';
		c = 'a'; 
		String str = "여러 글자";
		
		System.out.println(c);
		System.out.println(str);
		
		System.out.println("==============String -> int==============");
		str = "100";
		int strint = Integer.parseInt(str);
		System.out.println("int: " + strint);
		
		System.out.println("==============String -> long==============");
		long strlong = Long.parseLong(str);
		System.out.println("long: " + strlong);
		
		System.out.println("==============String -> double==============");
		double strdouble = Double.parseDouble(str);
		System.out.println("double: " + strdouble);

		System.out.println("==============int -> string==============");
		String str1 = String.valueOf(strint);
		System.out.println(str1);
		
		String str2 = Integer.toString(strint);
		System.out.println(str2);
	}
}
