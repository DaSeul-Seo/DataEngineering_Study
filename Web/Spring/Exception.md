- try-catch로 처리가 가능하나 Spring boot의 사상과 맞지 않는다.
- Annotation을 통해 처리
- Exception Handler는 Controller에서 처리
- Exception Handler (Advice)
    - Controller와 View 사이에 만들어 오류를 처리하는 객체 생성
    - @ControllerAdvice
        - 모든 컨트롤러에서 발생한 예외를 정의
    - @ExceptionHandler
        - 발생하는 예외마다 처리할 메소드를 정의
        - Controller 계층에서 발생하는 예외를 잡아서 메소드로 처리해주는 기능
        - value 값으로 어떤 Exception을 처리할 것인지 정의 가능
        - Service, Repository는 제외
    
    ```java
    package com.example.todo.config;
    
    import java.util.HashMap;
    import java.util.Map;
    
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    
    import lombok.extern.slf4j.Slf4j;
    
    @Slf4j
    @RestControllerAdvice
    public class CommonRestAdvice {
    
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<Map<String, Object>> defaultExceptionHandler(Exception e) {
            log.info("[CommonRestAdvice][defaultExceptionHandler] Starg");
            log.info("Exception message : " + e.getMessage());
    
            HttpHeaders headers = new HttpHeaders();
            HttpStatus status = HttpStatus.BAD_REQUEST;
    
            Map<String, Object> errorMsg = new HashMap<>();
            errorMsg.put("msg", "죄송합니다. 다시 시도해 주세요.");
            errorMsg.put("Exception message", e.getMessage());
    
            return new ResponseEntity<>(errorMsg, headers, status);
        }
    }
    ```
    
- Dao 오류 → Service
- Service 오류 → Controller
- Controller 오류 → Exception Handler

<aside>
💡 Reference

</aside>

- [https://velog.io/@kiiiyeon/스프링-ExceptionHandler를-통한-예외처리](https://velog.io/@kiiiyeon/%EC%8A%A4%ED%94%84%EB%A7%81-ExceptionHandler%EB%A5%BC-%ED%86%B5%ED%95%9C-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC)