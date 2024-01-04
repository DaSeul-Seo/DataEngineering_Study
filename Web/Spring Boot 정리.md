### Spring Boot

- 자바의 웹 프레임워크
- 스프링 프레임워크에 톰캣 서버를 내장
- 웹 프로그램을 쉽고 빠르게 만들 수 있도록 도와주는 웹 프레임워크
- WAS가 따로 필요 없다.
    - 스프링만 사용하여 웹 어플리케이션을 개발한다면, 웹 어플리케이션을 실행할 수 있는 톰캣과 같은 WAS(Web Application Server)가 필요
    - 스프링부트에는 톰캣 서버가 내장되어 있어 신경 쓸 필요 없다.
    - 배포되는 jar파일에도 톰캣서버가 내장되어 있으므로 서로 다른 WAS들로 인해 발생되는 문제들이 사라진다.

### Gradle 환경설정

- implementation
    - 해당 라이브러리 설치를 위해 일반적으로 사용하는 설정
    - 해당 라이브러리가 변경되더라도 이 라이브러리와 연관된 모든 모듈들을 컴파일 하지 않고 직접 관련있는 모듈들만 컴파일하기 때문에 rebuild 속도가 빠르다.
- developmentOnly
    - 해당 라이브러리가 개발환경에만 적용되는 설정
    - 운영환경에 배포되는 jar, war파일에는 제외
- compileOnly
    - 해당 라이브러리가 컴파일 단계에서만 필요한 경우에 사용
- runtimeOnly
    - 해당 라이브러리가 런타임시에만 필요한 경우 사용

### Lombok

- @Getter, @Setter, 생성자 등을 자동으로 만들어주는 고루
- @RequiredArgsConstructor
    - final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
    - 해당 속성을 필요로 하는 생성자가 자동으로 생성 (final이 없는 속성은 생성자에 포함되지 않는다.)
- @ResponseBody
    - URL 요청에 대한 응답으로 문자열을 리턴
    - 생략한다면 index라는 템플릿 파일을 찾게 된다.
        
        ```java
        @GetMapping("/sbb")
        @ResponseBody
        public String index() {
            return "index";
        }
        ```
        

### ORM (Object-Relational Mapping)

- 자바 문법만으로 데이터베이스를 다룰 수 있다.
- 쿼리를 직접 작성하지 않아도 데이터베이스의 데이터를 처리할 수 있다.

### JPA (Java Persistence API)

- ORM의 기술 표준으로 사용하는 인터페이스 모음
- JPA는 인터페이스이기에 구현하는 실제 클래스가 필요
    - 하이버네이트(Hibernate)가 구현함
- JPA 설정
    - spring.jpa.properties.hibernate.dialect
        - 데이터베이스 엔진 종류를 설정한다.
    - spring.jpa.hibernate.ddl-auto
        - 엔티티를 기준으로 테이블을 생성하는 규칙을 정의한다.
        - none - 엔티티가 변경되더라도 데이터베이스를 변경하지 않는다.
        - update - 엔티티의 변경된 부분만 적용한다.
        - validate - 변경사항이 있는지 검사만 한다.
        - create - 스프링부트 서버가 시작될때 모두 drop하고 다시 생성한다.
        - create-drop - create와 동일하다. 하지만 종료시에도 모두 drop 한다.
        - 개발환경에서는 보통 update 모드를 사용하고 운영환경에서는 none 또는 validate 모드를 사용.

### Entity

⭐ @Entity 사용 시, @Setter는 구현하지 않는 것을 권함.

⭐ 데이터베이스와 바로 연결되어 있으므로 데이터를 자유롭게 변경할 수 있는 Setter 메서드를 허용하는 것이 안정하지 않다

⭐ @Builder 를 통해 데이터 변경을 한다.

- @Entity를 적용해야 JPA 가 엔티티로 인식한다.
- @Id
    - 기본 키 적용
    - 데이터베이스에 저장 시, 동일한 값으로 저장할 수 없다.
- @GeneratedValue
    - 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장된다.
    - strategy는 고유번호를 생성하는 옵션.
    - GenerationType.IDEMTITY는 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용
- @Column
    - 컬럼의 세부 속성 설정
    - length : 컬럼의 길이 설정
    - columnDefinition : 컬럼 속성 정의 설정
        - columnDefinition=”TEXT” 글자 수를 제한할 수 없는 경우 사용
- @Transient
    - 테이블 컬럼으로 인식하고 싶지 않은 경우 사용
- @ManyToOne
    - N:1 관계
    - 실제 데이터베이스에서는 ForeignKey 관계가 생성
    - 부모자식 관계를 갖는 구조에서 사용
    - 답변은 여러개이고 질문은 하나
- @OneToMany
    - 1:N 관계
    - 질문 하나의 답변 여러개
    - mappedBy : 참조 엔티티의 속성명
        - ex) Answer 엔티티에서 Question 엔티티를 참조한 속성명 question을 mappedBy에 전달
    - CascadeType.REMOVE
        - ex) 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해

### JPA Repository

- 데이터 처리를 위해 실제 데이터베이스와 연동하는 Repository
- 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들(find All, save 등)을 사용하기 위한 인터페이스
- CRUD 처리
- JPA Repository를 상속할 때는 엔티티의 타입과 해당 엔티티의 PK 속성 타입을 지정

### @Autowired

- 객체를 주입하기 위해 사용
- Setter, 생성자를 사용하는 방식이 있지만 순환참조 문제와 같은 이유로 Autowired 권장

### 데이터 조회

- findAll
    - 테이블에 저장된 모든 데이터 조회
- findById
    - id 값으로 데이터 조회
    - 리턴 타입은 Optional ⇒ null 처리를 위해 isPresent() 로 확인
        
        ```java
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
        ```
        
- findBy{엔티티 속성명}
    - JPA Repository 인터페이스에 정의 후 사용
        - JPA가 해당 매서드명을 분석하여 쿼리를 만들고 실행함
    - 컬럼명을 기준으로 데이터 조회
- findBy{엔티티 속성명}And{엔티티 속성명}
    - 두 개의 속성을 And 조건으로 조회가능
    
    | 항목 | 예제 | 설명 |
    | --- | --- | --- |
    | And | findBySubjectAndContent(String subject, String content) | 여러 컬럼을 and로 검색 |
    | Or | findBySubjectOrContent(String subject, String content) | 여러 컬럼을 or로 검색 |
    | Between | findByCreateDateBetween(LocalDateTime fromDate, LocalDateTime toDate) | 컬럼을 between으로 검색 |
    | Lessthan | findByIdLessThan(Integer id) | 작은 항목 검색 |
    | GreaterThanEqual | findByIdGraterThanEqual(Integer id) | 크거나 같은 항목 검색 |
    | Like | findBySubjectLike(String subject) | like 검색 |
    | In | findBySubjectIn(String[] subjects) | 여러 값 중에 하나인 항목 검색 |
    | OrderBy | findBySubjectOrderByCreateDateAsc(String subject) | 검색 결과를 정렬하여 전달 |

### @Transactional

- 메서드가 종료될 때까지 DB세션이 유지된다.

### **스프링의 의존성 주입(Dependency Injection) 방식 3가지**

- @Autowired 속성 - 속성에 @Autowired 애너테이션을 적용하여 객체를 주입하는 방식
- 생성자 - 생성자를 작성하여 객체를 주입하는 방식 (**권장하는 방식**)
- Setter - Setter 메서드를 작성하여 객체를 주입하는 방식 (메서드에 @Autowired 애너테이션 적용이 필요하다.)

### Model 객체

- Model 객체는 자바클래스와 템플릿 간의 연결고리
- Model 객체는 따로 생성할 필요없이 컨트롤러 메서드의 매개변수로 지정하기만 하면 스프링부트가 자동으로 Model 객체를 생성

### URL 리다이렉트

- `redirect:<URL>` - URL로 리다이렉트 (리다이렉트는 완전히 새로운 URL로 요청이 된다.)
- `forward:<URL>` - URL로 포워드 (포워드는 기존 요청 값들이 유지된 상태로 URL이 전환된다.)

### Service가 필요한 이유

1. 모듈화
    1. 코드 중복 처리
2. 보안
    1. Repository 없이 서비스를 통해서만 데이터베이스에 접근하도록 구현하는 것이 보안상 안전
3. 엔티티 객체와 DTO 객체의 변환
    1. Entity는 데이터베이스와 직접 맞닿아 있는 클래스이기 때문에 Controller나 템플릿에 전달하여 사용하는 것은 권장하지 않음
    2. Controller나 템플릿에서 사용하는 데이터는 비즈니스적인 요구를 처리해야 하는 경우가 많기 때문에 엔티티 자체를 변경한다면 테이블 컬럼이 변경되어 엉망이 될 수 있음
    3. Controller나 템플릿에서 사용할 클래스 ⇒ DTO (Data Transfer Object)
    4. 엔티티와 DTO 객체 변환하는 일을 Service가 담당
    5. Controller와 Repository 중간입장에서 서로 변환하여 양방향에 전달하는 역할

### @PathVariable

- URL의 변하는 값을 얻을 때 사용한다.
- 매개변수 이름과 동일해야 한다.

### @RequestParam

- 템플릿에서의 값을 얻을 때 사용한다.
- 템플릿에서의 name 속성명으로 가져온다.

### Spring Boot Validation

- 화면에서 전달받은 입력 값을 검증하기 위해 사용

| 항목 | 설명 |
| --- | --- |
| @Size | 문자 길이를 제한한다. |
| @NotNull | Null을 허용하지 않는다. |
| @NotEmpty | Null 또는 빈 문자열("")을 허용하지 않는다. |
| @Past | 과거 날짜만 가능 |
| @Future | 미래 날짜만 가능 |
| @FutureOrPresent | 미래 또는 오늘날짜만 가능 |
| @Max | 최대값 |
| @Min | 최소값 |
| @Pattern | 정규식으로 검증 |
- 검증하기 위한 전용 클래스를 따로 만들어야 한다.

### BindingResult

- @Valid 로 인해 검증이 수행된 결과를 의미하는 객체
- 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
- bindResult.hasErrors() 를 호출해 오류가 있는지 확인해야 한다.

```java
// 질문 작성 후 등록할 때
// 템플릿의 subject, content는 자동적으로 QuestionForm 객체로 바인딩된다.
@PostMapping("/create")
public String questionCreate(@Valid QuestionForm questionForm, 
                            BindingResult bindingResult) {
    // 오류가 있는 경우에는 다시 폼을 작성하는 화면을 렌더링
    if (bindingResult.hasErrors()) {
        return "question_form";
    }
    // 오류가 없을 경우에만 질문 등록이 진행
    this.questionService.create(questionForm.getSubject(), questionForm.getContent());
    return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
}
```

### 오류 발생 시 입력한 내용 유지 (thymeleaf)

```java
th:field="*{subject}"
```

- 해당 태그의 id, name, value 속성이 모두 자동으로 생성되고 타임리프가 value 속성에 기존 값을 채워 넣어 오류가 발생하더라도 기존에 입력한 값이 유지

### Paging

- JPA 관련 라이브러리에 이미 페이징을 위한 패키지들이 들어있다.
    - org.springframework.data.domain.Page
    - org.springframework.data.domain.PageRequest
    - org.springframework.data.domain.Pageable
- Service
    
    ```java
    // 데이터 전체를 조회하지 않고 해당 페이지의 데이터만 조회하도록 쿼리가 변경
    public Page<Question> getList(int page) {
    		// 한페이지에 10개씩
        Pageable pageable = PageRequest.of(page, 10);
        return this.questionRepository.findAll(pageable);
    }
    ```
    
- Controller
    - 스프링부트의 페이징은 첫페이지 번호가 1이 아닌 0이다.
    - `http://localhost:8080/question/list?page=0` 처럼 GET 방식으로 요청된 URL에서 page값을 가져오기 위해 `@RequestParam(value="page", defaultValue="0") int page` 매개변수가 list 메서드에 추가
    
    ```java
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        // List<Question> questionList = this.questionService.getList();
        // model.addAttribute("questionList", questionList);
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
    ```
    

| 항목 | 설명 |
| --- | --- |
| paging.isEmpty | 페이지 존재 여부 (게시물이 있으면 false, 없으면 true) |
| paging.totalElements | 전체 게시물 개수 |
| paging.totalPages | 전체 페이지 개수 |
| paging.size | 페이지당 보여줄 게시물 개수 |
| paging.number | 현재 페이지 번호 |
| paging.hasPrevious | 이전 페이지 존재 여부 |
| paging.hasNext | 다음 페이지 존재 여부 |
| paging.getTotalElements | 전체 게시물 개수 |
| paging.number | 현재 페이지 번호 |
| paging.size | 페이지당 게시물 개수 |
| loop.index | 나열 인덱스(0부터 시작) |
---
### Paging기능
| 페이징 기능 | 코드 |
| --- | --- |
| 이전 페이지가 없으면 비활성화 | th:classappend="${!paging.hasPrevious} ? 'disabled'" |
| 다음 페이지가 없으면 비활성화 | th:classappend="${!paging.hasNext} ? 'disabled'" |
| 이전 페이지 링크 | th:href="@{|?page=${paging.number-1}|}" |
| 다음 페이지 링크 | th:href="@{|?page=${paging.number+1}|}" |
| 페이지 리스트 루프 | th:each="page: ${#numbers.sequence(0, paging.totalPages-1)}" |
| 현재 페이지와 같으면 active 적용 | th:classappend="${page == paging.number} ? 'active'" |

### Spring Security

- 스프링 기반 애플리케이션의 인증과 권한을 담담하는 프레임워크
    - 인증 (Authenticate) : 로그인
    - 권한(Authoriz) : 인증된 사용자가 어떤 것을 할 수 있는지 의미
- SecurityConfig 파일을 통해 관리
    - @Configuration : 스프링의 환경설정 파일임을 의미하는 어노테이션
    - @EnableWebSecurity : 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션
        - 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용
    - 스프링 시큐리티의 세부 설정은 SecurityFilterChain 빈을 생성하여 설정
    - CSRF (Cross Site Request Forgery)
        - 웹 사이트 취약점 공격을 방지를 위해 사용하는 기술
        - 스프링 시큐리티가 CSRF 토큰 값을 세션을 통해 발행하고 웹 페이지에서는 폼 전송 시에 해당 토큰을 함께 전송하여 실제 웹 페이지에서 작성된 데이터가 전달되는지를 검증하는 기술
        - clickjacking 공격을 막기위해 사용함
    - AuthenticationManager
        - 스프링 시큐리티의 인증 담당
        - 사용자 인증 시 UserSecurityService와 PasswordEncoder를 사용
    - 템플릿에서 로그인 여부
        - sec:authorize="isAnonymous()" - 이 속성은 로그인 되지 않은 경우에만 해당 엘리먼트가 표시되게 한다.
        - sec:authorize="isAuthenticated()" - 이 속성은 로그인 된 경우에만 해당 엘리먼트가 표시되게 한다.
    
    ```java
    package com.mysite.sbb;
    
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
    import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
    
    // 스프링의 환경설정 파일임을 의미하는 어노테이션
    @Configuration
    // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션
    @EnableWebSecurity
    public class SecurityConfig {
        // SecurityFilterChain 빈을 생성하여 시큐리티 세부설정 
        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            // 모든 인증되지 않은 요청을 허락한다는 의미 => 로그인을 하지 않더라도 모든 페이지에 접근할 수 있다.
            http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                    .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .csrf((csrf) -> csrf    // 웹 사이트 취약점 공격을 방지
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                .headers((headers) -> headers   // clickjacking 공격을 막기위해 사용함
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) -> formLogin // 로그인 설정 담당 메소드 (.formLogin)
                    .loginPage("/user/login")   // 로그인 페이지의 URL은 /user/login
                    .defaultSuccessUrl("/"))    // 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL(/)
                .logout((logout) -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))    // 로그아웃 URL
                    .logoutSuccessUrl("/")  // 로그아웃이 성공하면 루트(/) 페이지로 이동
                    .invalidateHttpSession(true))   // 로그아웃시 생성된 사용자 세션도 삭제하도록 처리
            ;
            return http.build();
        }
    
        // PasswordEncoder는 BCryptPasswordEncoder의 인터페이스
        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
        // AuthenticationManager : 인증
        // 사용자 인증 시 UserSecurityService와 PasswordEncoder를 사용
        @Bean
        AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }
    }
    ```