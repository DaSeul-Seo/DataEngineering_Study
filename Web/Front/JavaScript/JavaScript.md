### Javascript

- Web Browser 컨트롤 하는 언어
- 브라우저의 이벤트(키보드 Action, 마우스 Action 등)를 처리가 필요한 경우 사용

### 변수 선언

- var
    - 중복선언 가능
    - 데이터 재할당 가능
    
    ```jsx
    var title = "book";
    var title = "book2"; // 중복선언
    title = "book3"; // 재할당
    ```
    
- var를 사용하면 안되는 이유
    - var를 사용해 변수 선언을 해주고 동일한 변수명으로 정의를 해주었을 때 오류가 나지 않는다.
    - 선언을 여러번 해도 문제가 발생하지 않는다. (문제!)
    - ES6 버전부터는 let과 const가 추가되어 var를 지양한다.
    
    ```jsx
    // 보통의 변수 선언 및 정의
    var title = "book";
    title = "test";
    console.log(title)
    
    // 오류가 나지 않지만 적절하지 않은 변수 선언 및 정의
    var title = "book";
    var title = "test"; // 오류가 나지 않는다.
    console.log(title)
    ```
    
- let
    - 중복선언 불가
    - 재할당 가능
    
    ```jsx
    let variable = 10;
    let variable = 20; // Error!: 중복선언 불가
    console.log(variable); // 10
    variable = 20; // 재할당 가능
    console.log(variable); // 20
    ```
    
- const
    - 중복선언 불가
    - 재할당 불가
        
        ```jsx
        const constVariable = 10;
        const constVariable = 20; // Error!: 중복선언 불가
        console.log(constVariable); // 10
        constVariable = 20; // Error!: 재할당 불가
        ```
        
    - 객체 내부의 속성값은 재할당 가능
        
        ```jsx
        const person = {
        									name: "Alice",
        									age: 30
        								}
        console.log(person); // { name: "Alice", age: 30 }
        
        person.age = 31 // 객체 내부 속성값 변경
        console.log(person); // { name: "Alice", age: 31 }
        ```
        

### 자료형

- 숫자형
    - Infinity (=1/0)
        - 무한대 (숫자)
- 문자형
    - 중간에 변수 사용 가능
    
    ```jsx
    let name = "John";
    alert(`Hello, ${name}!`); // 억음부호 사용 (backtick)
    ```
    
- null & undefined
    - null
        - 존재하지 않는값(nothing), 비어있는 값(empty), 알수 없는 값(unknown)
        
        ```jsx
        let age = null;
        ```
        
    - undefined
        - 값이 할당되지 않은 상태
        
        ```jsx
        let age;
        ```
        

### 형변환

```jsx
let value = true;
typeof value; // boolean
// 중요! boolean이 string으로 변환
value = value + "";
typeof value; // string

let num = 3.14;
typeof num; // int
num = num + ""; // int를 string으로 변환
typeof num; // string
```

### 배열

- 배열선언
    
    ```jsx
    let arr = new Array();
    let arr2 = []
    ```
    
- ⭐ 배열 요소 자료형 제약이 없다.
    
    ```jsx
    let arr = [ '사과',
    						{ name: '이보라' }, 
    						true, 
    						function() { alert('안녕하세요.'); } ];
    
    // 인덱스가 1인 요소(객체)의 name 프로퍼티를 출력합니다.
    alert( arr[1].name ); // 이보라
    
    // 인덱스가 3인 요소(함수)를 실행합니다.
    arr[3](); // 안녕하세요.
    ```
    
- 배열 함수
    - length : 배열 길이
    - pop() : 배열 끝 요소 제거
    - push(string) : 배열 끝에 요소 추가
    - shift() : 배열 앞 요소 제거
    - unshift(string) : 배열 앞에 요소 추가
    - slice(?int, ?int) : 배열 자르기
    - arr.concat(arr) : 배열 합치기
    - foreach : 반복
    - indexOf(int / bool / null) : 인덱스 찾기 (없으면 -1 반환)
    - find : 요소 찾기 (true면 멈추고 해당 요소 반환)
    - filter : 조건 충족 요소 **모두** 반환 (리스트로 반환)
    - map : 배열의 각 원소별로 지정된 함수를 실행한 결과로 구성된 새로운 배열을 반환
    - sort : 배열 정렬
    - reverse : 배열 역순 정렬
    - split(string, int) : 배열 자르기
    - join(string) : 배열 요소 합치기

### JSON

- 데이터 교환
- HTTP 통신 시, 데이터 교환 형식
- 직렬화
    - JS = JSON.stringify()
    - C# = JsonConvert.SerializeObject()
- 역직렬화
    - JS = JSON.parse
    - C# = JsonConvert.DeserializeObject()

---

### 연산자

- == : 값 비교
- === : 타입 비교

### 함수

- 함수 선언문
    
    ```jsx
    function showMessage1() {
      alert('showMessage1');
    }
    ```
    
- 함수 표현식
    
    ```jsx
    let showMessage2 = function () {
      alert('showMessage2');
    };
    ```
    
- 성능 상의 차이는 없음.
- 함수 선언문은 호이스팅(hoisting)이 발생하여 스크립트가 실행되기 전에 함수가 메모리에 등록
- 따라서 함수를 선언하기 전에 호출해도 에러가 발생하지 않음.
- 함수 표현식은 변수에 함수를 할당하는 것이므로 변수가 선언된 이후에만 함수를 호출가능.

<aside>
💡 Reference

</aside>

- https://ko.javascript.info/