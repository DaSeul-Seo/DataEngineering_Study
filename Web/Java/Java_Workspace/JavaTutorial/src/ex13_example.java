import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ex13_example {

	public static void main(String[] args) {
		// 데이터 : 1, 2, 3, 4, 5, 7, 8, 9
		// 2의 배수인 경우 데이터에 곱하기 2
		// 3의 배수인 경우 데이터에 곱하기 3
		// 정답: 1, 4, 9, 8, 5, 7, 16, 27
		
//		int[] arr = { 1, 2, 3, 4, 5, 7, 8, 9 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9));
		List<Integer> rstList = new ArrayList<Integer>();
		for (int i : list) {
			if (i == 1) {
				rstList.add(i);
				continue;
			}
			else if (i % 2 == 0) {
				rstList.add(i * 2);
			}
			else if (i % 3 == 0) {
				rstList.add(i * 3);
			}
			else {
				rstList.add(i);
			}
		}
		System.out.println(rstList);
	}

}
