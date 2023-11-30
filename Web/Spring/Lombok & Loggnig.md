### Lombok

- Model에서 사용 (DAO, DTO, Entity)
- Annotation 사용
    - @Getter
    - @Setter
    - @NoArgConstructor : 기본 생성자
    - @AllArgConstructor : 내가 생성한 변수를 모두 사용해서 생성자 생성
    - @ToString

### Logging

> ✔️ 로그레벨
TRACE < DEBUG < INFO < WARN < ERROR
> 
- 자원을 많이 잡아먹지 않는다.
- 로그 파일을 만들 수 있다.
- 개발, 운영 버전에 따라 Log 레벨을 다르게 설정 가능

💡 C#은 단계가 하나 더 존재

TRACE < DEBUG < INFO < WARN < ERROR < FATAL

### 사용방법

- Slf4j 사용

```java
@Slf4j
public class BasicController {
	@GetMapping("/logging")
	public String getLogging() {
	  String logMsg = "logging 들어왔어";
	  log.info(logMsg);
	  return "logging 했어";
	}
}
```