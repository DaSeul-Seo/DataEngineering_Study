import java.util.List;
import java.util.ArrayList;

public class ex06_list {

	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		System.out.println("추가 전 : " + strList.size());
		
		// 추가
		strList.add("1");
		strList.add("2");
		strList.add("3");
		strList.add("4");
		
		System.out.println("추가 후 : " + strList.size());

		System.out.println("====================================");
		
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
		
		System.out.println("====================================");
		// 수정
		strList.set(1, "10");
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
		
		System.out.println("====================================");
		// 제거
		strList.remove(0);
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}
}
