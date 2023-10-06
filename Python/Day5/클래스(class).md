⭐ 완벽한 설계가 필수, 중요하며 그 후 개발.

### 클래스 (class)

- 변수와 함수를 묶어놓은 개념
- 클래스 선언
    - class {클래스명}:
- 클래스 내부 변수 = 속성(attribute) = 지역변수
- 클래스 내부 함수 = 메소드(method)
- 클래스 함수(생성함수)의 첫 번째 인자로 self를 작성해야 한다.
- **self는 인스턴스를 가리킴**

```python
class GrandMother:            # 클래스
  family = "grandparents"     # 클래스 내부 변수 = 속성

  def pring_self(self):       # 클래스 내부 함수 = 메소드
    print(self)

# Test클래스가 생성이 되면서 Lee는 Test 클래스의 인스턴스 객체가 주입된다.
LEE = GrandMother()    # 인스턴스화 / 인스턴스 선언

GrandMother.family          # 속성 호출 => hello
GrandMother.print_self()    # 메소드 호출 => **Error**
LEE.family                  # hello
LEE.print_self()            # <class GrandMother>
```

### 인스턴스

- self
    - 인스턴스를 가리킨다.
    - 객체 생성을 통해서만 사용 가능.
- __init__
    - 생성함수 ⇒ 인스턴스를 만들어 준다.
        - ⭐ self 함수를 절대 return을 쓰면 안된다.
    - 따로 정의하지 않으면 Python이 내부적으로 생성해준다.
    - 재정의 할 수 있음(오버라이딩)
    
    ```python
    class father(GrandMother):
      FAMILY = "parents"    # 클래스 내에 저장된 값. 인스턴스와 공유
    	
      # name, age : 인스턴스 선언시 꼭 입력해야하는 속성
      def __init__(**self**, name, age):  # 생성자
        self.name = name
        self.age = age
        sele.home = "seoul"   # 인스턴스 선언 시 자동으로 생기는 속성(클래스에는 없음)
    
    # Error => 선언 시 name, age를 입력해야 함.
    KIM = father()
    
    # Good
    KIM = father("Yong", 40)
    ```
    
    ```python
    class Person :
      name    = "person name"
      age     = 18
    
      def __init__(self,name) :
        self.name = name
    
    # 클래스의 속성값
    # Person.name: person name / Person.age: 18
    print(f'Person.name: {Person.name} / Person.age: {Person.age}')
    
    # 인스턴스에서 값 변경
    p_instant1 = Person("인스턴트1")
    p_instant1.age = 20
    
    # 인스턴스의 속성값
    # p_instant1.name: 인스턴트1 / p_instant1.age: 20
    print(f'p_instant1.name: {p_instant1.name} / p_instant1.age: {p_instant1.age}')
    
    # 인스턴스에서 값 변경
    p_instant2 = Person("인스턴트22")
    # 인스턴스의 속성값
    # p_instant2.name: 인스턴트22 / p_instant2.age: 18
    print(f'p_instant2.name: {p_instant2.name} / p_instant2.age: {p_instant2.age}')
    
    # 클래스의 속성값
    # Person.name: person name / Person.age: 18
    print(f'Person.name: {Person.name} / Person.age: {Person.age}')
    
    # ⭐p_instant1과 p_instant2는 서로다른 객체이다.!!
    ```
    

### ⭐ 상속 (inheritance)

- 부모 클래스의 속성(attribute), 메소드를 자식 클래스도 가지게 된다.
- 코드 재사용 가능하다.
- 여러번 상속 가능
- public, private 다 상속 가능

```python
class 부모클래스:
  ...부모 코드(속성, 메소드)....

class 자식클래스(부모클래스):
  ...자식 코드(속성, 메소드).... # 부모클래스의 코드도 사용 가능!!
```

```python
# 부모 클래스
class GrandMother :
  family = "Hello world"
  name = "GrandMother"

  def print_self(self) :
    print(self)
  
  def print_HI():
    print("HI!")

# 자식 클래스
class father(GrandMother) :
  name = "father"

  def __init__(self,name,age) :
    self.name = name
    self.age  = age
    self.home = "seoul"

class child(father) :
  name = "child"

father.print_HI()   # HI!
child.print_HI()    # HI!
child.family        # Hello world!
```

### Override

- 부모 클래스의 attribute(속성), 메소드를 자식 클래스에서 재정의

```python
class PlayerCharacter:
  def __init__(self,hp=100,exp=0):
    self.hp = hp
    self.exp = exp

  def attack(self):
    print("공격하기")
    self.exp = self.exp + 2

  def defend(self):
    print("방어하기")
    self.exp = self.exp + 1

# 상속 받기
class Wizard(PlayerCharacter):
  def __init__(self,mp):
    super().__init__() # 부모클래스의 생성자를 실행하겠다.
    self.mp = mp

  def magic_skill(self):
    print("마법 공격하기")
    self.mp = self.mp - 2

  # 메소드 오버라이딩
  def defend(self):
    print("마법사가 방어하기")
    self.exp = self.exp + 3
```

### 접근 지정자 (Access Specifiers)

1. Public
    1. 외부에서 접근 가능
2. Private
    1. 클래스 내부에서만 사용가능. 외부에서 접근 불가능.
    2. 변수명, 함수명 앞에 언더바(_) 2개 붙여준다.
    
    ```python
    class Student:
      __schoolName = 'XYZ School' # private class attribute
    
      def __init__(self, name, age):
        self.name = name    # public instance attribute
        self.__age = age    # private instance attribute
      def __display(self):  # private method
        print('This is private method.')
    
    std = Student("Bill", 25)
    std.name         # Bill
    std.__age        # Error => Private
    std.__schoolName # Error => Private
    std.__display()  # Error => Private
    ```
    

### Decorators

1. @Property
2. @classmethod
3. @staticmethod

### Magic Methods → 추후에 또 한다.

- 딥러닝에서 많이 사용한다.
- 메소드 명이 두 개의 언더바(_)로 감싸져 있다.
- 내장 함수
- 조상 함수를 상속 받은 함수들
- https://www.tutorialsteacher.com/python/magic-methods-in-python

```python
'''
__init__ : 생성 함수
__call__ : 변수를 함수처럼 쓰고 싶을 때
	instance = className()
	instance()
__getiten__ : 인덱싱, 슬라이싱을 가능하게 한다.
'''
```

- 딥러닝
    - AI
    - 모델도 데이터를 하나씩 공부한다.
    - 컴퓨터가 스스로 학습
    - __getitem__ 함수로 data를 가지고 와서 __call__ 함수를 통해 학습을 한다.

### 모듈(Module) & 패키지(Package)

- 모듈(Module)
    - import
    - 파이썬 내장 함수들
    - 외부 다른 사람이 만든 것들
    - 변수, 함수, 클래스를 모아놓은 파일
- 패키지(Package)
    - 모듈을 모아놓은 것
- ⭐if _ _ name _ _ = “_ _ main _ _”:
    - 이 것으로 실행해야 안전하다.
```python
# 모듈 불러오기
import <모듈명>

# 패키지에서 모듈 불러오기
from <패키지명> import <모듈명>

# 모듈 안에 함수와 클래스 불러오기
from <모듈명> import <함수 or 클래스>

# 별칭 주기
import <모듈명> as <별칭>
```

- 모듈 패키지 사용하기

💡 corab

```python
# 폴더 만들기
!mkdir common
```

```python
# common 폴더 안에 utils.py를 만들건데 아래의 내용을 채워 넣겠다.
%%writefile common/utils.py

def add(a, b):
  return a + b

def sub(a, b):
  return a - b

⭐
if __name__ == "__main__":
  print(add(3, 5))
  print(sub(5, 3))
```

```python
# 파일 실행
!python common/utils.py

# 코드에서 사용 1
import common.utils as co_util
co_util.__name__

# 코드에서 사용 2
from common.utils import add, sub
add(3, 5)
```

### 표준 라이브러리 (함수)

- Python을 설치 시 자동으로 설치된다.

💡 collections

- Counter
- OrderDict
- defaultdict
- math
- random
- datetime
- time
- json
