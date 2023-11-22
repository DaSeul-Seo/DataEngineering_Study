import java.util.HashMap;
import java.util.Map;

public class ex08_map {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		
		// 추가
		map.put("name", "Abigail");
		map.put("age", "11");
		map.put("address", "Seoul");
		
		// 수정
		map.replace("age", "30");
		
		// map 데이터 수
		System.out.println(map.size());
		// map 저장된 데이터 확인
		System.out.println(map.get("name"));
		// map의 key들
		System.out.println(map.keySet());
		// map 데이터 제거
		map.remove("name");
		System.out.println(map.keySet());
		
		for (String key : map.keySet()) {
			System.out.println(key + "의 value 값은 " + map.get(key));
		}
		
		map.containsKey("name");
		System.out.println(map.containsKey("name"));
		System.out.println(map.containsValue("30"));

	}
}
