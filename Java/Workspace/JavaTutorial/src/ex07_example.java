import java.util.ArrayList;
import java.util.List;

public class ex07_example {
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		
		// 문제 1
		// 1. list1에 10, 10, 10, 10, 10 넣기
		for (int i = 0; i < 5; i++) {
			list1.add(10);
		}
		
		// 2. for문을 통해 10, 20, 30, 40, 50으로 데이터를 변경
		for (int i = 0; i< list1.size(); i++) {
			list1.set(i, (i+1) * 10);
		}
		// 3. 변경된 값을 print 하기
		for (int i = 0; i< list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		
		// 문제 2
		// 반 성적표 정보 확인
		// 100, 80, 50, 90, 70
		// 평균
		// 가장 큰 수
		// 가장 작은 수
		
		// 방법 1
		int[] scores = { 100, 80, 50, 90, 70 };
		
		int sum = 0;
		int max = scores[0];
		int min = scores[0];
		double avg = 0;
		
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i];
			
			if (max < scores[i]) {
				max = scores[i];
			}
			
			if (min > scores[i]) {
				min = scores[i];
			}
		}
		avg = (double) sum / scores.length;
		System.out.println("반 평균 : " + avg);
		System.out.println("가장 큰 수 : " + max);
		System.out.println("가장 작은 수 : " + min);
				
		// 방법 2
		// 평균 = 합 / 갯수
//		int sum = 0;
//		double avg = 0;
//		for (int i = 0; i < scores.length; i++) {
//			sum += scores[i];
//		}
//		
//		avg = (double) sum / scores.length;
//		System.out.println("반 평균 : " + avg);
//		
//		// 가장 큰 수
//		int max = scores[0];
//		for (int i = 0; i < scores.length; i++) {
//			if (max < scores[i]) {
//				max = scores[i];
//			}
//		}
//		System.out.println("가장 큰 수 : " + max);
//		
//		// 가장 작은 수
//		int min = scores[0];
//		for (int i = 0; i < scores.length; i++) {
//			if (min > scores[i]) {
//				min = scores[i];
//			}
//		}
//		System.out.println("가장 작은 수 : " + min);
	}
}
