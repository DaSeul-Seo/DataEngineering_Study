
public class ex05_if_for {

	public static void main(String[] args) {
		int[] scores = { 10, 20, 30, 40, 50 };
		
		for (int i = 0; i < scores.length; i++) {
			System.out.println(i + "번째 값 : " + scores[i]);
		}
		
		int max = 0;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > max) {
				max = scores[i];
			}
		}
		System.out.println(max);
	}
}
