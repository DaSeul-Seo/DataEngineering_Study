- JS = Document 객체를 통해 html 문서의 데이터를 조작
- 랜더링
    - html을 DOM 객체를 통해 화면을 만든다.
- window

### BOM (Browser Object Model)

- navigator
    - 브라우저와 운영체제에 대한 정보
        - 접속 매체(모바일, 테블릿 등), 브라우저 버전 등
    
    ```jsx
    document.write("현재 사용 중인 브라우저의 이름은 " + navigator.appName + "입니다.<br>");
    document.write("또한, 해당 브라우저의 코드명은 " + navigator.appCodeName + "입니다.");
    document.write("현재 사용 중인 브라우저의 버전 정보는 " + navigator.appVersion + "입니다.<br><br>");
    document.write("userAgent 프로퍼티로 알 수 있는 추가 정보는 " + navigator.userAgent + "입니다.");
    document.write("현재 브라우저가 실행되고 있는 운영체제는 " + navigator.platform + "입니다.");
    document.write("현재 브라우저의 기본 언어 설정은 " + navigator.language + "입니다.");
    ```
    
- location
    - 현재 URL 읽고 변경(redirect)
    
    ```jsx
    document.write("현재 문서의 주소는 " + location.href + "입니다.<br>");
    document.write("현재 문서의 호스트 이름은 " + location.hostname + "입니다.<br>");
    document.write("현재 문서의 파일 경로명은 " + location.pathname + "입니다.<br>");
    ```
    
    - assign() : 브라우저 창에 지정된 URL 주소에 존재하는 문서 불러오기
    - replace() : assign()과 비슷하지만, 브라우저의 히스토리에서 제거

### DOM(Document Object Model)

- html 조작
- getElementById(string) : 요소의 id 속성 가져오기
    
    ```html
    <div id="elem">
      <div id="elem-content">Element</div>
    </div>
    
    <script>
      // 요소 얻기
      let elem = document.getElementById('elem');
    
      // 배경색 변경하기
      elem.style.background = 'red';
    </script>
    ```
    
- getElementByTagName(string) : 요소의 태그 가져오기
    
    ```html
    <table id="table">
      <tr>
        <td>나이:</td>
    
        <td>
          <label>
            <input type="radio" name="age" value="young" checked> 18세 미만
          </label>
          <label>
            <input type="radio" name="age" value="mature"> 18세 이상, 60세 미만
          </label>
          <label>
            <input type="radio" name="age" value="senior"> 60세 이상
          </label>
        </td>
      </tr>
    </table>
    
    <script>
      let inputs = table.getElementsByTagName('input');
    
      for (let input of inputs) {
        alert( input.value + ': ' + input.checked );
      }
    </script>
    ```
    
- getElementByClassName(string) : class 속성 값을 기준으로 대응하는 요소를 담은 컬렉션 반환
    
    ```html
    <form name="my-form">
      <div class="article">글</div>
      <div class="long article">내용이 긴 글</div>
    </form>
    
    <script>
      // name 속성을 이용해 검색(아주 드물게 사용)
      let form = document.getElementsByName('my-form')[0];
    
      // form 내에서 클래스 이름을 이용해 검색
      let articles = form.getElementsByClassName('article');
      alert(articles.length); // 2. 클래스 속성값이 'article'인 요소는 2개입니다.
    </script>
    ```
    
- querySelectorAll(css) : 여러 요소 검색
    
    ```html
    <ul>
      <li>1-1</li>
      <li>1-2</li>
    </ul>
    <ul>
      <li>2-1</li>
      <li>2-2</li>
    </ul>
    <script>
      let elements = document.querySelectorAll('ul > li:last-child');
    
      for (let elem of elements) {
        alert(elem.innerHTML); // "1-2", "2-2"
      }
    </script>
    ```
    

### Event

- Mouse
    - click, mouseover, mouseout, mousedown, mouseup, mousemove
- Form
    - submit : <form> 을 제출할 때 발생
    - focus : <input>같은 요소에 포커스 할 때 발생
- Event Handler
    - HTML 속성 이용
        - HTML에서 on 속성 핸들러 할당
        
        ```html
        <input value="클릭해 주세요." onclick="alert('클릭!')" type="button">
        
        <script>
          function countRabbits() {
            for(let i=1; i<=3; i++) {
              alert(`토끼 ${i}마리`);
            }
          }
        </script>
        
        <input type="button" onclick="countRabbits()" value="토끼를 세봅시다!">
        ```
        
    - DOM 프로퍼티 이용
        - DOM 프로퍼티에 on<event> 사용
        
        ```html
        <!--1번째-->
        <input id="elem" type="button" value="클릭해 주세요.">
        <script>
          elem.onclick = function() {
            alert('감사합니다.');
          };
        </script>
        
        <!--2번째-->
        <input type="button" id="elem" onclick="alert('이전')" value="클릭해 주세요.">
        <script>
          elem.onclick = function() { // 기존에 작성된 핸들러를 덮어씀
            alert('이후'); // 이 경고창만 보입니다.
          };
        
          elem.value = "코딩좋아";
        </script>
        ```
        
- addEventListener
    - 하나의 이벤트에 복수의 핸들러를 할당
    
    ```html
    <input id="elem" type="button" value="클릭해 주세요."/>
    
    <script>
      function handler1() {
        alert('감사합니다!');
      };
    
      function handler2() {
        alert('다시 한번 감사합니다!');
      }
    
      elem.onclick = () => alert("안녕하세요.");
      elem.addEventListener("click", handler1); // 감사합니다!
      elem.addEventListener("click", handler2); // 다시 한번 감사합니다!
    </script>
    ```
    
- removeEventListener
    - 핸들러 삭제
    
    ```jsx
    function handler() {
      alert( '감사합니다!' );
    }
    
    input.addEventListener("click", handler);
    input.removeEventListener("click", handler);
    ```
    
- Object Handler

```jsx
<input type="button" value="modify text" id="textInput" />
// innerHTML
let spanTag = document.getElementById("spanTag");

let changeTxt = {
    handleEvent(event) {
        switch(event.type) {
            case 'mousedown':
                spanTag.innerHTML = "Mouse Down";
                break;
            case 'mouseup':
                spanTag.innerHTML = "Mouse Up";
                break;
        }
    }
}

document.getElementById("textInput").addEventListener("mousedown", changeTxt);
document.getElementById("textInput").addEventListener("mouseup", changeTxt);
```

<aside>
💡 Reference

</aside>

- Event
    - https://ko.javascript.info/introduction-browser-events