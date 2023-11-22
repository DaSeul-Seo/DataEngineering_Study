
public class ex04_array {

	public static void main(String[] args) {
		// String array
		String[] strArray = { "Kloud", "Cass", "Asahi", "Guinness", "Heineken" };
		
		// array 길이
		System.out.println("strArray 길이 : " + strArray.length);
		
		// array 요소 확인
		System.out.println("strArray의 0번째 : " + strArray[0]);
		System.out.println("strArray의 1번째 : " + strArray[1]);
		System.out.println("strArray의 마지막 : " + strArray[strArray.length - 1]);
		
		// int Array
		int[] scores = { 10, 20, 30, 40, 50 };
		System.out.println(scores.length);
		
		// 평균 = 합 / 갯수
		int sum = 0;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i];
		}
		
		double avg = (double) sum / scores.length;
		System.out.println(sum);
		System.out.println(avg);
		
		// array 선언 -> 값 대입
		int[] arr = new int[3];
		System.out.println(arr.length);
		arr[0] = 30;
		System.out.println(arr[0]);
		arr[1] = 40;
		arr[2] = 50;
	}
}
