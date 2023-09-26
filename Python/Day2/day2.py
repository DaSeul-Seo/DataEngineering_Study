import enum


# a는 숫자가 들어가 있는 변수
a = 1
# b에는 Python 문자열이 들어가 있는 변수
b = "Python"
c = "1"

print("=================")
print(type(a))      # 변수 a의 타입을 확인
print(type(c))
print("=================")

# 변수 표기법 - 스네이크 표기법
hello_world = "Hello World"
print(hello_world)

# int_1 -> 1 -> print(1)
int_1 = 1
print(int_1)



class RAINBOW(enum.Enum):
    RED = (enum.auto(), 'red', '빨간색', 0)
    ORANGE = (enum.auto(), 1)
    YELLOW = (enum.auto(), 2)
    GREEN = (enum.auto(), 3)
    BLUE = (enum.auto(), 4)
    NAVY = (enum.auto(), 5)
    PURPLE = (enum.auto(), 6)

# name => 변수명
print(RAINBOW.RED.name)
# RAINBOW.RED.name = 'GREEN'  # Error => 상수는 변경 불가능
print(RAINBOW.RED.name)

# value => 해당 값, 추가 정보들은 index로 기입
print(RAINBOW.RED.value[2])
print(RAINBOW.PURPLE.value[0])

for e in RAINBOW:
    print((e.name, e.value[1]))

red_bool = 'RED' in RAINBOW.__members__
print(red_bool)



a, b = 3, 4
print(a+b)

print(7%3)
print(7//3)



a = 'asd"ds"'
print(a)

b = 'I\'m a student'
c = "I'm a student"
print(b)
print(c)

d = "hello world"
print(d[0:5])
print(d[:5])
print(d[6:11])
print(d[:-6])