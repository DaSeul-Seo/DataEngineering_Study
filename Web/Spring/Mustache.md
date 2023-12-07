### build

```java
// Gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-mustache'
}
```

### 기본문법

- 변수사용
    - 중괄호 두개로 변수 사용
    - 중괄호 3개는 html 태그 적용 시, 사용
    
    ```java
    {{name}}
    {{age}}
    {{company}}
    {{{company}}}
    ```
    
- 조건문
    
    ```java
    {{#person}}
    	참일 경우 출력
    {{/person}}
    {{^person}}
    	거짓일 경우 출력
    {{/person}}
    ```
    
- 반복문
    
    ```java
    {{#repo}}
    	{{name}}
    {{/repo}}
    ```
    
- 주석
    
    ```java
    {{! 느낌표를 이용해 주석}}
    ```
    
- layout 분할
    - header
    - body
        
        ```java
        {{> /layout/header}}
        {{> /layout/footer}}
        ```
        
    - footer

<aside>
💡 Reference

</aside>

- https://www.tsmean.com/articles/mustache/the-ultimate-mustache-tutorial/
- 기본문법
    - https://taegon.kim/archives/4910