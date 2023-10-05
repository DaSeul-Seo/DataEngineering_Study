1. 개 & 고양이 문제
    1. 부모클래스 : 포유류
        1. 변수 : 성별 / 나이 / 다리
        2. 기능
            1. 새끼를 낳는다.
            2. 밥 먹는다.
    2. 자식클래스 : 강아지
        1. 기능 : 짖는다. / 꼬리를 흔든다.
    3. 자식클래스 : 고양이
        1. 기능 : 할퀸다. / 구르밍한다.
    
    ```python
    class Animal:
      name = "이름"
      sex = "성"
      age = "나이"
      leg = "다리"
    
      def __init__(self, name, sex, age, leg):
        self.name = name
        self.sex = sex
        self.age = age
        self.leg = leg
      
      def Eat(self):
        print("밥을 먹는다.")
    
      def Baby(self):
        print("새끼를 낳는다.")
    
    class Dog(Animal):    
      def Bark(self):
        print("개가 짖는다.")
    
      def ShakeTail(self):
        print("개가 꼬리를 흔든다.")
    
    class Cat(Animal):    
      def Scratch(self):
        print("고양이가 할퀸다.")
    
      def Guruming(self):
        print("고양이가 그루밍한다.")
    ```
    
    ```python
    jindo = Dog("진돗개", "남", 3, 4)
    doberman = Dog("도베르만", "여", 1, 4)
    persian = Cat("페르시안", "여", 3, 4)
    munchkin = Cat("먼치킨", "남", 2, 4)
    
    jindo.Bark()
    doberman.Eat()
    persian.Guruming()
    munchkin.Baby()
    ```
    
    **개 & 고양이 문제 - 강사님 풀이**
    
    ```python
    class Animal:
      name = ""   # 클래스 변수
      food = "옥수수"
    
      '''
      self = 인스턴스
      self.name은 외부 p_name(입력변수 = 파라미터)을 받아서 업데이트
      self.gender ~ self.baby_name은 새로 변수 생성한 것
    
       '''
      def __init__(self, p_name:str, p_gender:bool, p_age:int = 0, p_leg_cnt:int = 4) -> None:
        self.name = p_name          # 클래스 변수
        self.gender = p_gender      # 인스턴스 변수
        self.age = p_age            # 인스턴스 변수
        self.leg_cnt = p_leg_cnt    # 인스턴스 변수
        self.baby_name = f"{p_name}'s baby"
      
      # p_food가 None이면 옥수수로 하고 아니면 업데이트
      def eat_food(self, p_food:str = None) -> None:
        if (p_food is not None):
          self.food = p_food
    
        print(f"{self.food}를 먹는다.")
    
      def get_baby(self, p_baby_name:str = None) -> str:
        if (p_baby_name is not None):
          self.baby_name = p_baby_name
    
        print(f"아기의 이름은 {self.baby_name} 입니다.")
        return self.baby_name
    ```
    
    ```python
    person = Animal(p_name = "Marlin", p_gender = True, p_age = 27, p_leg_cnt = 2)
    person.eat_food()
    person.eat_food("삽겹살 & 술")
    
    baby_name = person.get_baby()
    baby_name
    ```
    
    ```python
    class Dog(Animal):
      def bark(self):
        print(f"{self.name}의 나이가 {self.age} 만큼 많아서 멍멍소리가 약하다.")
      
      def dog_tail(self):
        print(f"{self.name} 꼬리를 흔들고 있다.")
    ```
    
    ```python
    dog1 = Dog(p_name = "쫑쫑이", p_gender = False, p_age = 20)
    
    dog1.eat_food()
    dog1.bark()
    ```
    
    ```python
    class Cat(Animal):
      def __init__(self, p_name = "쫑쫑이", p_gender = False, p_power = None): # 부모의 필수값을 무조건 써주어야 한다. 추가 변수를 받아도 된다.
        super().__init__(p_name, p_gender)   # 부모 클래스에서 p_age와 p_leg_cnt는 디폴트 값이 있기 떄문에 필수로 넣지 않아도 된다.
        if (p_power is not None):
          self.power = p_power
        else:
          self.power = self.age + 10
    
      def scratch(self):
        print("쓰윽" * self.power)
      
      def groom(self):
        _tmp = self.power - self.age
        print("꽃무장" * _tmp)
    
      # __call__ : 인스턴스()
      def __call__(self):
        self.scratch()
        self.groom()
    ```
    
    ```python
    cat = Cat(p_name="쫑쫑이", p_gender=False, p_power=3)
    
    cat.scratch()
    ```
    
2. 이동수단 문제
    1. 부모 클래스
        1. 변수 : 바퀴수 = 0 / 탑승인원 = 0
        2. 기능
            1. 후진 = "뒤로 이동"
            2. 전진 = "앞으로 이동"
        
        ```python
        class Transport:
          wheel_num = 0
          person_num = 0
        
          def __init__(self, p_wheel_num:int = 0, p_person_num:int = 0) -> None:
            self.wheel_num = p_wheel_num
            self.person_num = p_person_num
        
          def Go(self) -> None:
            print(f"탑승인원 : {self.person_num}, 바퀴수 : {self.wheel_num}, 앞으로 이동")
        
          def Back(self) -> None:
            print(f"탑승인원 : {self.person_num}, 바퀴수 : {self.wheel_num}, 뒤로 이동")
        
        trans = Transport(p_wheel_num = 4, p_person_num = 3)
        trans.Go()
        ```
        
    2. 자식 클래스 : 오토바이
        1. 변수 : 바퀴수 = 2 / 탑승인원 = 1
        2. 기능
            1. 점프 = "점프한다"
            2. 후진 = "앞바퀴를 들고 후진"
        
        ```python
        class Motorcycle(Transport):
          def __init__(self, p_wheel_num:int = 2, p_person_num:int = 1) -> None:
            super().__init__(p_wheel_num, p_person_num)
        
          def Jump(self) -> None:
            print(f"탑승인원 : {self.person_num}, 바퀴수 : {self.wheel_num}, 점프한다.")
        
          def Back(self) -> None:
            print(f"탑승인원 : {self.person_num}, 바퀴수 : {self.wheel_num}, 앞바퀴를 들고 후진")
        
        print("motorcycle1")
        motorcycle = Motorcycle()
        motorcycle.Jump()
        motorcycle.Go()
        motorcycle.Back()
        print("motorcycle2")
        motorcycle2 = Motorcycle(p_wheel_num=4, p_person_num=2)
        motorcycle2.Jump()
        motorcycle2.Go()
        motorcycle2.Back()
        ```
        
    3. 자식 클래스 : 배
        1. 변수 : 탑승인원 = 4 / 잠수유무
        2. 기능
            1. 전진= "항해한다"
            2. 잠수 = 잠수유무 변수가 True = "잠수한다" / False = "잠수불가"
        
        ```python
        class Ship(Transport):
          def __init__(self, p_wheel_num:int = 0, p_person_num:int = 4, p_dive:bool = False) -> None:
            super().__init__(p_wheel_num, p_person_num)
            self.dive = p_dive
        
          def Go(self) -> None:
            if (self.dive == False):
              print(f"탑승인원 : {self.person_num}, 잠수유무: {self.dive}, 항해 한다.")
            else:
              print(f"탑승인원 : {self.person_num}, 잠수유무: {self.dive}, 잠수 항해.")
          
          def Diving(self) -> None:
            if (self.dive == True):
              print(f"탑승인원 : {self.person_num}, 잠수유무: {self.dive}, 잠수 한다.")
            else:
              print(f"탑승인원 : {self.person_num}, 잠수유무: {self.dive}, 잠수 불가.")
        
        print("ship")
        ship = Ship()
        ship.Diving()
        ship.Go()
        print("ship2")
        ship2 = Ship(p_dive = True)
        ship2.Diving()
        ship2.Go()
        ```
        
3. 직업문제
    1. 부모클래스 : 사람
        1. 변수 : 이름 / 나이
        2. 기능 : 일한다.
        
        ```python
        class Person:
          def __init__(self, p_name:str = "영희", p_age:int = 20) -> None:
            self.name = p_name
            self.age = p_age
          
          def Working(self):
            print(f"{self.name} 이(가) 일한다.")
        
        person = Person()
        person.Working()
        ```
        
    2. 자식클래스 : 요리사
        1. 변수 : 경력
        2. 기능
            1. 일한다. (경력 > 10 이면 코스요리를 만든다. 아니면 디저트 만든다.)
            2. 주문 받는다. ("{이름}" 요리사 입니다.)
        
        ```python
        class Chef(Person):
          def __init__(self, p_career = 20):
            super().__init__()
            self.career = p_career
        
          def Working(self):
            if (self.career >= 10):
              print(f"{self.name} 요리사가 코스요리를 만든다.")
            else:
              print(f"{self.name} 요리사가 디저트를 만든다.")
          
          def order(self):
            print(f"{self.name} 요리사 입니다.")
        
        chef = Chef()
        chef.Working()
        
        chef2 = Chef(9)
        chef2.Working()
        ```
        
    3. 자식클래스 : 경찰
        1. 변수 : power
        2. 기능
            1. 일한다. (power > 10 이면 깡패를 잡는다. 아니면 순찰한다.)
            2. 접수 받는다. ("무엇을 도와드릴까요?”)
        
        ```python
        class Polic(Person):
          def __init__(self, p_name:str, p_age:int, p_power:int):
            super().__init__(p_name, p_age)
            self.power = p_power
          
          def Working(self):
            if (self.power > 10):
              print(f"{self.name} 형사가 깡패를 잡는다.")
            else:
              print(f"{self.name} 형사가 순찰한다.")
          
          def Question(self):
            print("무엇을 도와드릴까요?")
        
        police = Polic("철수", 33, 20)
        police.Working()
        police.Question()
        ```
