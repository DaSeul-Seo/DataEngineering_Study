### 함수(Function)

- 함수구조

```python
def 함수명(입력변수):
	수행할 프로그램1
	수행할 프로그램2
	return 출력변수
```

```python
rst = multiplication(5)

def multiplication(input_data):
    out_data = input_data * 5
    return out_data
```

1. 입력변수 x, 출력변수 x
    
    ```python
    def func():
      lst = [1, 2]
      for i in lst:
        print(i)
    
    func()
    ```
    
2. 입력변수 O, 출력변수 x
    
    ```python
    break_num = 2
    
    def func(p_break_num):
      lst = [1, 2, 3, 4, 5]
      for i in lst:
        print(i)
        if p_break_num < i:
          break
    
    func(break_num)
    ```
    
3. 입력변수 x, 출력변수 O
    
    ```python
    def add():
      a = 3
      b = 2
      return a + b
    
    result = add() 
    result
    ```
    
4. 입력변수 O, 출력변수 O
    
    ```python
    a = 3 
    b = 5 
    
    def add(p_a, p_b):
      return p_a + p_b
    
    result = add(a, b)
    result
    ```
    

### ⭐ Scope (변수 사용범위)

- 지역변수(local): 함수 내부에서 만들어진 지역변수는 함수 내에서만 사용가능(파라미터 포함)
- 전역변수(global): 함수 밖에서 만들어진 변수(어디서든 사용 가능)
- 파이썬의 제어문은 해당 안된다. 제어문의 변수는 전역변수이다.
- 전역변수의 변수명과 지역변수의 변수명이 동일하다면 print의 위치에 따라 값이 다르다. ⇒ 변수를 사용하는 위치가 중요

```python
x = 10 
def func():
  ## 함수영역 시작
  x = 20 
  print(f'{x}는 지역변수이다.')
  ## 함수영역 끝

func()
print(f'{x}는 전역변수이다.')
```

### 함수 입력변수 (Parameter)

1. Default Parameter
    1. None 사용 가능
    2. 다른 파라미터 보다 나중에 넣어야 한다.
    
    ```python
    def add(a=3, b=2):
      return a + b
    
    add()     # 5
    add(4,5)  # 9
    add(b=7)  # 10
    ```
    
    ```python
    # None 사용 가능
    # 다른 파라미터 보다 나중에 넣어야 한다.
    def add(a, b=None):   # def add(a=4, b): => Error
      if b is not None:
        return a + b
      else:
        return a + a
    
    add(5, 7)  # 12
    add(5)     # 10
    ```
    
2. 가변 파라미터
    1. 함수를 정의하면서 argument가 n개 이상이 들어올 수 있다면 가변 파라미터를 정의해주면 된다.(0개 포함)
    2. * 를 이용해서 파라미터명을 정의해주면 된다.
    3. 일반적으로 *ages 표현
    4. 함수내부에서는 튜플 묶인다.
    
    ```python
    def add(*args):
      print(sum(args)) # sum()는 모든 변수를의 합을 리턴한다.
    
    add(1, 2, 3)    # 6
    
    lst = [1, 2, 3, 4, 5]
    add(*lst)       # 15
    
    tup = (2, 4, 6, 8)
    add(*tup)       # 20
    ```
    
    ```python
    def add(*args, a=0):
      print(sum(args) * a) # sum()는 모든 변수를의 합을 리턴한다.
    
    add(1, 2, 3)    # 0
    add(1, 2, a=3)  # 9
    ```
    
3. 키워드 가변 파라미터
    1. 함수를 정의하면서 키워드 argument가 n개이상 들어올 수 있다(0개 포함)
    2. 일반적으로 **kwargs로 표현
    3. 함수내부에 딕셔너리 형태로 묶인다.
    4. 키워드 argument에서 키워드가 key 값이 되고, argument가 value 된다.
    
    ```python
    def user_answer(answer, **user):
      print(f'{user["name"]}가 "{answer}"라고 말했어요~')
    
    # 김씨가 "배고파요ㅠㅠ"라고 말했어요~
    user_answer('배고파요ㅠㅠ', **user)
    ```
    
    ```python
    student = {
        'name': '똑똑이',
        'grade': '2학년'
    }
    
    exam_scores = [90, 99, 95, 97]
    
    def get_avg_score(exam_name, *exam_scores, **student):
        print(f'{student["name"]}는 이번 {student["grade"]} {exam_name}에서 평균 {sum(exam_scores)/len(exam_scores)} 받았어요.')
    
    # 똑똑이는 이번 2학년 중간고사 시험에서 평균 95.25 받았어요.
    get_avg_score('중간고사 시험', *exam_scores, **student)
    ```
    
    **⭐ * : list / ** : dictionary**
    
    ### 람다함수(lanbda)
    
    - 한줄 함수
    - 1회용 함수를 만들 때 사용
    - 람다 함수는 아주 간단한 파라미터가 정의되거나 반환하는 함수일 경우 사용
    
    ```python
    def add(num1, num2):
      return num1 + num2 
    
    add(3, 5)
    
    # 람다식
    add_lambda = lambda num1, num2: num1+num2 
    add_lambda(3, 5)
    
    make_even = lambda x: [i for i in x if i%2 == 0]
    make_even([1, 2, 3, 4, 5, 6, 7, 8])
    # [2, 4, 6, 8]
    ```
    
    ### Closure (중급)
    
    - 함수 안에 함수(내부함수 = 지역함수) 만들기
    - 함수 내부 지역변수를 기억하고 있다가, 내부함수(지역함수)를 실행할 때 기억한 지역변수를 처리
    
    ```python
    def func_out(out_param):
        a = 10
        def func_in():
          return a + out_param 
        return func_in
    
    func = func_out(5)
    func  # 내부함수인 func_in이 리턴 되었다.
    ```
    
    ```python
    # 외부에서 func_in 함수 사용 가능
    def func_out(out_param):
        a = 10
        def func_in():
          return a + out_param 
        return func_in  # 함수 자체가 리턴
    
    asd = func_out(5)
    print(asd)    # <function __main__.func_out.<locals>.func_in()>
    asd()  # 이때 내부함수 실행 => 15
    
    # ------------------------------------------------------
    # 외부에서 func_in 함수 사용 불가능
    # 내부함수를 함수 내에서 실행
    def func_out(out_param):
        a = 10
        def func_in():
          return a + out_param 
        return func_in()  # 함수의 값이 리턴
    
    asd = func_out(5)
    print(asd)   # 15
    ```
    
    ```python
    def func_out(out_param):
        a = 10
        def func_in(in_param):
          return a + out_param + in_param 
        return func_in
    
    func = func_out(5)
    func # 내부함수인 func_in이 리턴 되었다.
    func() # 내부함수의 in_param 갑이 없어서 오류 발생
    func(7)    # 22
    ```
    
    ### 콜백함수 (Callback Function) (중급)
    
    - 파라미터로 함수를 받는다.
    - 함수도 변수라고 한다.
    
    ```python
    def call_func(a, func): # 콜백함수(func)를 변수로 받음
      return func(a) 
    
    def add_one(num):
      return num + 1
    
    def add_three(num):
      return num + 3
    
    call_func(3, add_one)       # 4
    call_func(3, add_three)     # 6
    call_func(2, lambda x:x*x)  # 4
    ```
    
    - 여러개 함수를 파라미터로 받을 수 있다.
    
    ```python
    def call_func(a, func1, func2): # 콜백함수(func)를 변수로 받음
      return func1(a) + func2(a)
    
    def add_one(num):
      return num + 1
    
    def add_three(num):
      return num + 3
    
    call_func(3, add_one, add_three)  # 10
    ```
    
    - 함수를 리스트에 넣어 사용 가능하다.
    
    ```python
    def add_one(num):
      return num + 1
    
    def add_three(num):
      return num + 3
    
    def call_func(a, *funcs): # 콜백함수(func)를 변수로 받음
      return funcs[0](a) + funcs[1](a)
    
    lst_funcs = [add_one, add_three]
    call_func(3, *lst_funcs)   # 10
    ```
    
    ### Decorate (중급)
    
    - 내부함수의 주소를 리턴하는 클로져와 비슷하고 함수를 다른함수의 인자로 전달하는 방식과 비슷하다.
    - 그냥 두가지 방식을 합쳤다고 보면 된다.
    
    ```python
    def decorator_func(org_func): # 함수를 인자로 받는다.
        def wrapper_func(): # 내부함수를 구현한다.
            print("org_func 가 실행 되기전 입니다.")
            org_func() # org_func 함수를 실행
        return wrapper_func # 내부함수의 주소를 반환
    
    @decorator_func
    def do_func():
        print("original 함수가 실행 되었습니다.")
    
    '''
    decorator_func의 파라미터로 do_func()이 들어간다.
    '''
    ```
    
    - 함수가 실행되는 시간 측정
    
    ```python
    import time
    
    def elapsed(original_func):   # 기존 함수를 인수로 받는다.
        def wrapper():
            start = time.time()
            result = original_func()    # 기존 함수를 수행한다.
            end = time.time()
            print("함수 수행시간: %f 초" % (end - start))  # 기존 함수의 수행시간을 출력한다.
            return result  # 기존 함수의 수행 결과를 리턴한다.
        return wrapper
    
    @elapsed
    def myfunc():
        print("함수가 실행됩니다.")
    
    @decorator_func
    @elapsed
    def myfunc():
        print("함수가 실행됩니다.")
    
    '''
    실행되는 순서
    1. decorator_func
    2. elapsed
    3. myfunc()
    '''
    ```
    
    ### Iterator (중급)
    
    - for문은 따로 하나하나 실행하는 것과 동일
    - for문과 같은 반복 구문을 적용할 수 있는 리스트와 같은 객체를 반복 가능(iterable) 객체
        - list, tuple, dictionary, set
    
    ```python
    lst = [1, 2, 3]
    next(lst)  # Error
    '''
    lst라는 리스트로 next() 함수를 호출하면
    리스트는 iterator 객체가 아니라는 오류가 발생.
    즉, 반복 가능하다고 해서 iterator는 아니라는 말이다.
    하지만 반복 가능하다면 다음과 같이 iter() 함수를 이용하여
    iterator로 만들 수 있다.
    '''
    
    lst = [1, 2, 3]
    lst_iter = iter(lst)
    type(lst_iter)   # list_iterator
    
    next(lst_iter)   # 1
    next(lst_iter)   # 2
    next(lst_iter)   # 3
    next(lst_iter)   # Error
    ```
    
    ### Generator (중급)
    
    - Iterator를 생성해 주는 함수
    - return 대신 yield키워드 사용
    
    ```python
    def gener():
      for i in [1, 2]:
        yield i
    
    g = gener() 
    type(g)   # generator
    
    next(g)   # 1
    next(g)   # 2
    next(g)   # Erroe
    ```
    

### 내장 함수

- Python이 제공하는 내장 함수
- abs() : 절대값 반환
- min() : 최소값 반환
- max() : 최대값 반환
- sorted()
    - 정렬 후 결과를 리스트로 반환
- range()
    - range([start, ] stop[, step]) : stop은 필수. start, step은 옵션
    - list(range(3)) ⇒ [0, 1, 2]
    - list(range(3, 7)) ⇒ 3, 4, 5, 6
    - list(range(1, 10, 2)) ⇒ 1, 3, 5, 7, 9
    - list(range(10, 1, -2)) ⇒ 10, 8, 6, 4, 2
- zip()
    - 동일한 개수로 이루어진 데이터들을 묶어서 반환
    - 리스트의 길이(len)가 동일해야 한다.
    
    ```python
    lst1 = [1,2,3]
    lst2 = ['a', 'b', 'c']
    
    for item in zip(lst1, lst2):
      print(item[0], item[1])
    
    for item1, item2 in zip(lst1, lst2):
      print(item1, item2)
    
    '''
    1 a
    2 b
    3 c
    '''
    
    for item in zip(lst1, lst2):
      print(item)
    
    '''
    (1, 'a')
    (2, 'b')
    (3, 'c')
    '''
    ```
    
- enumerate()
    - 순서가 있는 데이터(list, tuple, str)를 입력 받아 인덱스 값을 포함하는 enumerate 객체를 반환
    - 위치의 값이 필요할 경우
    - **⭐enumerate()가 속도가 더 빠르다**
    
    ```python
    for index, name in enumerate(['body', 'foo', 'bar']):
      print(f'{index}번째, 이름: {name}')
    '''
    0번째, 이름: body
    1번째, 이름: foo
    2번째, 이름: bar
    '''
    ```
    
- isinstance()
    - is : 이다 아니다 ⇒ True/False
    - instance : 객체화
    - isinstance(object, class)는 첫 번째 인수로 객체, 두 번째 인수로 클래스를 받는다.
    - 입력으로 받은 객체가 그 클래스의 인스턴스인지를 판단하여 참이면 True, 거짓이면 False를 반환.
    
    ```python
    a = 3 
    isinstance(a, int), type(a)   # (True, int)
    
    a = 3.14 
    isinstance(a, float), type(a) # (True, float)
    
    a = [1, 2, 3]
    isinstance(a, list), type(a)  # (True, list)
    
    a = 'Hello world'
    isinstance(a, str), type(a)   # (True, str)
    ```
