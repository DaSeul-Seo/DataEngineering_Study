- 변수 : 변하는 수
- 자료형 & 형변환
    
    ```java
    // 정수형 (long, int, short, byte)
    long l = 30L;
    int x = 30;
    short s = 30; // 잘 사용하지 않음 
    byte b = 30; // 잘 사용하지 않음 
    
    int i = (int) 30L; // 형변환을 해야 함!!
    long ll = 30; // long이 int보다 큰 범위를 표현할 수 있으므로 형변환을 할 필요 없음 (자동 형변환)
    
    // 실수형 (double > float)
    double dd = 30.0;
    float ff = 30.0f; // f를 빼면 double로 인식!!
    
    dd = ff; // 자동 형변환 
    ff = (float) dd; // 형변환 필요!! 
    
    // 불리언형  
    boolean bool = true; 
    bool = false; 
    
    // 문자형 
    char c = '한';
    c = 'a'; 
    String str = "여러 글자";
    ```
    
    ```java
    String str = "100";
    int i = Integer.parseInt(str); // 문자열 -> 숫자열 
    long l = Long.parseLong(str); 
    Double dd = Double.parseDouble(str); 
    
    System.out.println(i);
    
    String str2 = String.valueOf(i); // 숫자열 -> 문자열 
    System.out.println(str2);
    ```
    
- Array
    
    ```java
    String[] beer = {"Kloud", "Cass", "Asahi", "Guinness", "Heineken"};
    
    System.out.println(beer[0]); // Kloud
    System.out.println(beer[1]); // Cass
    
    for (int i=0; i < beer.length; i++) {
      System.out.println(beer[i]);
    }
    ```
    
    ```java
    int[] score = new int[3]; // 크기가 3인 배열 생성
    score[0] = 10; // 0번 index에 값 할당
    score[1] = 15; // 1번 index에 값 할당
    score[2] = 13; // 2번 index에 값 할당
    
    int sum = 0;
    for (int i = 0; i < score.length; i++) { // score.length = 5
      sum += score[i]; // sum = sum + score[i];
    }
    
    double avg = (double) sum / score.length; // 형변환
    System.out.println("점수 합계 : " + sum);
    System.out.println("점수 평균 : " + avg);
    ```
    
- List
    - 길이조절 가능
    - LinkedList, ArrayList
    
    ```java
    import java.util.ArrayList;  // ArratList 선언 시
    import java.util.LinkedList; // LinkedList 선언 시
    import java.util.List;
    
    // 생성방법 
    List<자료형> lst1 = new ArrayList<자료형(생략가능)>();
    List<자료형> lst2 = new LinkedList<자료형(생략가능)>();
    
    // 삽입 
    lst1.add("1");
    lst1.add(0, "2"); // 0번째에 "2" 삽입 
    // 치환 
    lst1.set(0, "5"); // 0번째에 "5"값으로 치환 
    // 출력 
    lst1.get(0); // 0번째 값 출력 
    lst1.size(); // 리스트 크기 출력 
    // 삭제 
    lst1.remove(0); // 0번째 삭제 
    lst1.clear(); // 모든 요소 삭제
    ```
    
- Map
    - Key, Value
    - Key : 중복 O, Value : 중복 X
    
    ```java
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
    ```
    
- Set
    - 집합
    - 중복제거
    - 각 집합의 합집합, 차집합 연산 가능
    - 비순차적
    
    ```java
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
    ```