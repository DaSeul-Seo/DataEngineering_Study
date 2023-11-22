import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ex12_example {

	public static void main(String[] args) {
		// 데이터 : 20, 40, 70, 70, 80, 70, 100
		// 최반값
		// 정렬 : 20, 40, 70, 70, 70, 80, 100
		
		List<Integer> intList = new ArrayList<Integer>(Arrays.asList(20, 40, 70, 70, 80, 70, 100));
		
		// 최빈값
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int mode = 0;
		
		// 데이터별 빈도수
		for (int i : intList) {
			int cnt = 1; // 무조건 1개 존재
			if (map.containsKey(i)) { // 이미 map에 존재하는 값이면 +1
				map.replace(i, map.get(i) + 1);				
			}
			else { // 새로운 값이면
				map.put(i, cnt);
			}
		}
		
		int temp = 0; 
		for (Entry<Integer, Integer> entrySet : map.entrySet()) { // map 데이터 하나씩 불러온다
			temp = Math.max(temp, entrySet.getValue()); // map의 값과 temp를 비교해 최대값 update
//			System.out.println(temp);
			if (entrySet.getValue().equals(temp)) { // Key값 update
				mode = entrySet.getKey();		
			}
		}
		System.out.println("최빈값 : " + mode);
		
		// 정렬
		System.out.println("정렬 전 : " + intList);
		// 방법 1
		intList.sort(null);
		System.out.println("정렬 후_방법1 : " + intList);
		
		// 방법 2 - linked 하고 싶은데 못했음
//		List<Integer> sortList = new ArrayList<Integer>();
//		for (int i : intList) {
//			
//		}
	}

}
