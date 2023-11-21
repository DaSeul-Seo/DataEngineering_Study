- print
    
    ```java
    System.out.println("Hello world");
    System.out.println("Hello world");
    
    System.out.println("=============================");
    
    System.out.print("world ");
    System.out.print("world ");
    
    // %s: 문자열 
    // %d: 정수 
    // %f: 실수 
    // \n: 줄바꿈 
    System.out.printf("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.\n", "홍길동", 20, 180.5f);
    
    String str2 = String.format("저는 %s입니다. 나이는 %d살이고요, 키는 %fcm입니다.\n", "신사임당", 20, 180.5f); 
    System.out.println(str2);
    ```
    
- Math
- Random
    
    ```java
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
    // 1 ~ 10
    int rand1 = random.nextInt(10) + 1;
    // -10 ~ -1
    int rand2 = random.nextInt(10) -10;
    ```
    