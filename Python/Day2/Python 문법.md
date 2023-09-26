- 어떤 가상환경에 있는지 아는 방법

![1](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/5da10f2e-15ea-4110-b66c-76674f0b9ba5)

![2](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/8249e5a9-ea95-48b0-a8e4-c77373ef532f)


### Python Package Upgrade

- pip upgrade
    - pip (Pip Installs Packages) : python 패키지 설치 및 관리 (패키지 매니저)

```python
python -m pip install --upgrade pip
```

### 사용할 모듈 설치

```python
pip install jupyter numpy pandas
```

- 설치 확인

```python
import numpy as np
import pandas as pd

print("Coding Good!") # 작동이 되면 잘 나온다.
```

### jupyter

- 확장자 : ipynb (ipython notebook)
- 환경설정
    - select kernel → 환경 선택 (python → python 버전)
- 코딩 한 줄 한 줄 확인 가능
- 새로운 패키지 및 모듈을 사용할 경우 jupyter 사용
    - 서비스 → python 파일을 써야 좋다.


## 자료구조

### 변수

- 변하는 수
- int : 숫자
- str : 문자열

```python
# a는 숫자가 들어가 있는 변수
a = 1
# b에는 Python 문자열이 들어가 있는 변수
b = "Python"
c = "1"

print("=================")
print(type(a))      # 변수 a의 타입을 확인
print(type(c))
print("=================")
```

### ⭐ 변수 표기법

- 개발자에서 가장 중요한 것
- 변수명으로 한 번에 어떤 값이 들어가 있는지 알 수 있어야 함
- 대표적인 표기법
    - 카멜 표기법 (대문자, 낙타봉) ⇒ Java
        - helloWorld
    - 스네이크 표기법 (소문자, 언더바(_)) ⇒ Python
        - hello_world

```python
# 변수 표기법 - 스네이크 표기법
hello_world = "Hello World"
print(hello_world)
```

### ⭐ 변수 명명

- 코드의 재활용성과 가독성을 높여주고, 중복을 제거하여 유지보수를 용이하게 함.
- 맨 앞에 숫자, 특수문자를 넣으면 안된다.

### 변수 비교

- is / is not (메모리 주소 비교)
- = / ≠ (값 비교)

```python
x = 1001
y = 1001

x is y      # False
x is not y  # True

x == y      # True
x != y      # False
```

### 상수

- 변하지 않는 수
- 대문자 표기법 (개발자끼리 약속)
- enum
    - 서로 관련이 있는 여러 개의 상수의 집합을 정의
    - **외부에서 값 수정 불가능**
    - enum.auto()
        - 메모리 주소 자동 세팅 (1부터 시작)
    
    ```python
    import enum
    
    class RAINBOW(enum.Enum):
    		RED = (enum.auto(), 'red', '빨간색', 0)
        ORANGE = (enum.auto(), 1)
        YELLOW = (enum.auto(), 2)
        GREEN = (enum.auto(), 3)
        BLUE = (enum.auto(), 4)
        NAVY = (enum.auto(), 5)
        PURPLE = (enum.auto(), 6)
    
    print(RAINBOW.RED.name)
    # RAINBOW.RED.name = 'GREEN'  # Error => 상수는 변경 불가능
    print(RAINBOW.RED.name)
    
    # value => 해당 값, 추가 정보들은 index로 기입
    print(RAINBOW.RED.value[2])
    print(RAINBOW.PURPLE.value[0])  # 7
    
    for e in RAINBOW:
        print((e.name, e.value[1]))
    
    red_bool = 'RED' in RAINBOW.__members__
    print(red_bool)
    ```
    
    - enum 비교하기
        - is와 =의 값이 같다. ⇒ 유일한 값인 상수이기 때문에 값과 메모리 주소가 같다.
    
    ```python
    RAINBOW.RED = RAINBOW.RED   # True
    RAINBOW.RED IS RAINBOW.RED  # True
    ```
    

### 자료형

- 상수 또는 변수에 담겨있는 값에 대한 종류
    - 숫자형 : int, float, complex
    - 문자열 : str
    - 튜플 : tuple
    - 리스트 : list
    - 사전 : dict
    - 집합 : set

### 숫자형 연산

- +, -, *, /
- 제곱 : **
- 몫 : //
- 나머지 : %

### 문자열

- 홀따옴표(’)와 쌍따옴표(”)를 구별하지 않는다.
- 홀따옴표 안에 쌍따옴표를 쓸 수 있지만 홀따옴표는 쓰지 못한다.
- 쌍따옴표 안에 홀따옴표를 쓸 수 있지만 쌍따옴표는 쓰지 못한다.
- ‘’’ / “”” : 여러 줄을 하나의 데이터로 쓸 수 있다.
- 이스케이프 코드
    - \n : 줄바꿈
    - \t : 문자 사이 탭 간격
    - \\ : /를 표현
    - \’ : 홀따옴표 표현
    - \” : 쌍따옴표 표현

### 문자열 연산

- + :  문자열 더하기
- * n : 문자열 반복

### ⭐ 인덱싱 (indexing)

- 식별할 수 있는 위치 정보 ⇒ 식별자

### 슬라이싱 (slicing)

- range (범위)를 표현
- 문자 변수[시작인덱스:끝인덱스:간격]

```python
a = "hello world"
print(a[0:5])    # hello -> 끝인덱스 글자를 미포함
print(a[:5])     # hello -> 시작이 처음일 경우
print(a[6:])     # world -> 끝까지
print(a[-1])     # d -> 마지막 글자
print(a[0::2])   # hlowrd
```

### Format

```python
print("I eat {} apples.".format(3))  # I eat 3 apples

num = 10,
day = "three"

print("I ate {0} apples. I was sick for {1} days".format(num, day))
print("I ate {number} apples. I was sick for {day} days".format(number = num, day = day))
print(f"I ate {num} apples. I was sick for {day} days")
```

### 문자열 관련 함수

- count() : 문자 개수
- len() : 문자열 길이
- find() : 해당 문자 위치 알려주기 (인덱싱 위치 알려주기)
    - 없는 경우 -1 반환
- join() : 문자열 삽입
    
    ```python
    a = "Hello"
    ",".join(a)                  # H,e,l,l,o
    ",".join(['a', 'b', 'c'])    # a,b,c
    ```
    
- split() : 문자열 나누기
    
    ```python
    a = "Hello world"
    a.split(" ")           # ['Hello', 'world']
    ```
    
- upper() : 대문자로 바꾸기
- lower() : 소문자로 바꾸기
- strip() : 공백 제거
    - lstrip() : 왼쪽 공백 제거
    - rstrip() : 오른쪽 공백 제거
- replace() : 문자열 바꾸기
    
    ```python
    a = "Hello world"
    a.replace("world", "King")    # Hello King
    ```
