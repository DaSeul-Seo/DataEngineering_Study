### build

```java
// Gradle
implementation 'org.springframework.boot:spring-boot-starter-validation'

// Maven
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### 역할

- 일반적인 검증로직
    - Controller, Service, DAO 등에서 중복작업이 됨 ⇒ Application이 복잡해짐
- 비즈니스 로직 검증
- Input 데이터 검증
- Controller에서 사전 처리

### 사용법

- 요청을 받을 때 @Valid 어노테이션 추가

```java
public ResponseEntity<String> save(@Valid @RequestBody ItemDTO itemDTO) {
    itemService.save(itemDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
}
```

### Annotation

| Annotation | Description |
| --- | --- |
| @Size | 문자의 길이 조건 |
| @NotNull | null 값 불가 |
| @NotEmpty | @NotNull + "" 값 불가 |
| @NotBlank | @NotEmpty + " " 값 불가 |
| @Past | 과거 날짜 |
| @PastOrPresent | @Past + 오늘 날짜 |
| @Future | 미래 날짜  |
| @FutureOrPresent: | @Future + 오늘 날짜 |
| @Pattern | 정규식을 통한 조건 |
| @Max | 최대값 |
| @Min | 최소값 |
| @AssertTrue | 참 |
| @AssertFalse | 거짓 |


### code

```java
public class ProductDto {
  
  private String productId;

  @NotBlank
  @Size(min = 4, max = 10)
  private String ProductName;

  @Min(value = 50)
  private int ProductPrice;

  @Min(value = 0)
  @Max(value = 99)
  private int productStock;

}
```

<aside>
💡 Reference

</aside>

- [https://kdhyo98.tistory.com/80#Jakarta Validation API 살펴보기-1](https://kdhyo98.tistory.com/80#Jakarta%20Validation%20API%20%EC%82%B4%ED%8E%B4%EB%B3%B4%EA%B8%B0-1)