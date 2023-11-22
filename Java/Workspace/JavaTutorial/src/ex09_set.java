import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ex09_set {

	public static void main(String[] args) {
		// 집합
		// 중복 데이터 제거
		// 각 집합의 합집합, 차집합 연산 가능
		
		Set<String> set = new HashSet<String>();
		set.add("H");
		set.add("H");
		set.add("H");
		set.add("T");
		set.add("T");
		set.add("T");
		set.add("T");
		set.add("T");
		
		System.out.println(set.size());
		System.out.println(set);
		System.out.println("===========================");
		
		// 교집합
		Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
	    Set<Integer> s2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9));
	    
	    System.out.println("교집합 전 : " + s1);
	    s1.retainAll(s2);
	    System.out.println("교집합 후 : " + s1);
		
	    // 합집합
	    s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
	    s2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9));
	    
	    System.out.println("합집합 전 : " + s1);
	    s1.addAll(s2);
	    System.out.println("합집합 후 : " + s1);
	    
	    // 차집합
	    s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
	    s2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9));
	    
	    System.out.println("차집합 전 : " + s1);
	    s1.removeAll(s2);
	    System.out.println("차집합 후 : " + s1);
	}
}
