## Annotation

💡 url은 unique 해야 한다.

- @GetMapping(urlPath)
    - method가 고정
    
    ```java
    @GetMapping("/hello")
    ```
    
- @RequestMapping(value, method)
    - method 수정이 가능
    
    ```java
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    ```
    
- @GetMapping({urlVar})
    - url에 입력받은 값을 받는다.
    - Parameter에 @PathVariable 추가
    
    ```java
    @GetMapping("/variable/{param}")
    public String getHello2(@PathVariable String param) {
      log.info("url로부터 param을 받았어 : " + param);
      return param;
    }
    ```
    
- @GetMapping({urlVar}/{urlVar2})
    - url에 입력받은 값이 2개 이상
    - Parameter에 @PathVariable 추가
    
    ```java
    @GetMapping("/variable/{param1}/{param2}")
    public String getHello3(@PathVariable String param1, @PathVariable String param2) {
      log.info("url로부터 param을 받았어 : " + param1);
      return param2;
    }
    ```
    
- @GetMapping({urlVar})
    - url에 입력받은 값이 사용할 변수명과 다를 경우
    - @PathVariable(”urlVar”) 추가
    
    ```java
    @GetMapping("/variable4/{param}")
        public String getHello4(@PathVariable("param") String name) {
            log.info("url로부터 param을 받았어 : " + name);
            return name;
        }
    ```
    
- @GetMapping() : query 사용
    - http://localhost:8080/query?key=value
    - http://localhost:8080/query?key=HelloWorld
    
    ```java
    @GetMapping("query")
    public String getQuery1(@RequestParam String key) {
      log.info("어서와 query는 처음이지?");
      return key;
    }
    
    // 다중 쿼리1
    @GetMapping("query2")
    public String getQuery2(
      @RequestParam String key1,
      @RequestParam String key2,
      @RequestParam String key3
      ) {
      log.info("어서와 다중쿼리는 처음이지?");
      return key1 + key2 + key3;
    }
    
    // ⭐ 다중쿼리2
    @GetMapping("query3")
    public String getQuery3(@RequestParam Map<String, Object> params) {
      log.info("어서와 다중쿼리(map)는 처음이지?");
      String msg = "";
    
      for (String key : params.keySet()){
        msg = msg + " " + (params.get(key)).toString();
      }
      return msg;
    }
    ```
    
- Model 사용
    
    ```java
    // http://localhost:8080/dto1?name=name1&email=email1
    @GetMapping("/dto1")
    public String getDto1(@ModelAttribute BasicDto dto) {
      return dto.toString();
    }
    ```
    
- @PostMapping(urlPath)
    
    ```java
    public String postVariable1(@RequestBody Map<String, Object> params){
      String msg = "";
    
      for (String key : params.keySet()){
        msg += params.get(key).toString();
      }
      return msg;
    }
    ```
    
- Model Dto
    - Annotation을 통한 Getter, Setter, 생성자, toString 자동으로 매핑 가능
    
    ```java
    package com.example.basic.model;
    
    import lombok.*;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class UserDto {
        private String userName;
        private int userAge;
        private String userEmail;
    }
    ```
    
- @Slf4j
    - 로그를 위해 사용
- @RestController
    - RESTful API 구조에서 JSON 타입으로 데이터를 반환 받기 위해 사용
- @Controller
    - Spring MVC 패턴을 사용하기 위해 사용
- @RequestMapper
    - Controller URL Default