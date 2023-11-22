import java.util.Random;

public class ex11_math_random {
	public static void main(String[] args) {
		System.out.println(Math.max(10, 30));
		System.out.println(Math.min(10, 30));
		
		int[] arr = { 10, 20, 30, 40, 50 };
		int max = 0;
		for (int i : arr) {
			max = Math.max(max, i);
		}
		System.out.println(max);
		
		Random random = new Random();
		// 0~9까지 랜덤 추출
		int rand = random.nextInt(10);
		System.out.println(rand);
		// 1 ~ 10
		int rand1 = random.nextInt(10) + 1;
		System.out.println(rand1);
		// -10 ~ -1
		int rand2 = random.nextInt(10) -10;
		System.out.println(rand2);
		
		
	}
}
