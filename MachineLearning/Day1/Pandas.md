## Pandas

- 데이터 처리와 분석을 위한 라이브러리
- Pandas를 쓰는 이유
    - Python에서 최대값, 최소값 등등 직접 구현해야하지만 Pandas에서는 이미 함수로 구현되어 있어 가져다 쓰기만 하면 되기 때문에 편리하다.
- 실제로 데이터 분석은 Numpy로 한다.
    - Numpy는 C언어 기반이라서 빠르다.
    - 대량의 데이터를 한꺼번에 계산해야 하기 때문에

### Series (1차원)

- Numpy에서 Vector
- 1차원 구조로 되어 있는 데이터
- Numpy와 다르게 딕셔너리도 넣을 수 있다.
- Series 생성
    
    ```python
    pd.Series(data=None, index=None, dtype=None, name=None, copy=False)
    ```
    
    ```python
    data = {'a':1, 'b':2, 'c':3} # 딕셔너리
    pd.Series(data=data, dtype=np.int16, name='dict')
    '''
    # 실제 데이터는 1, 2, 3이다.
    # a, b, c는 인덱스역할(식별값)
    a    1
    b    2
    c    3
    Name: dict, dtype: int16
    '''
    
    # 스칼라 값인 경우 인덱스를 제공해야함
    pd.Series(5.0, index=['a', 'b', 'c', 'd', 'e'])
    '''
    # 5.0이 실제 데이터 값
    # a, b, c, d, e는 인덱스
    a    5.0
    b    5.0
    c    5.0
    d    5.0
    e    5.0
    dtype: float64
    '''
    ```
    

### Series 슬라이싱/인덱스

- Python, Numpy와 동일
    
    ```python
    s[0]   # 인덱실
    s[:3]  # 슬라이싱
    # 인덱싱을 여러개 해서 값을 불러올 수 있다. (리스트로 써야함)
    s[[4, 2, 1]]
    s[[4, 1, 2]]  # 순서가 달라진다.
    
    # 해당 데이터를 Update 한다.
    s[[0,1,2]] = [0,1,2]
    ```
    
- 조건을 넣을 수 있다.
    
    ```python
    import pandas as pd
    
    s = pd.Series(data=np.random.randn(5), index=['a', 'b', 'c', 'd', 'e'])
    s[s > 0]  # 데이터가 0보다 큰 것들만 슬라이싱 해줘
    s[s < 0] # 데이터가 0보다 작은 것들만 슬라이싱 해줘
    # 조건이 여러개일경우, 괄호를 해주어야 한다.
    s[(s > 1) | (s < -1)]
    s[(s < 2) & (s > -1)]
    s.median()  # 중위값
    ```
    
- in 사용 가능
    - 인덱스 값이 존재 유무 파악
    
    ```python
    'a' in s  # True
    't' in s  # False
    ```
    
- get
    - 데이터를 찾을 때 없는 값을 넣으면 오류가 나는데 오류를 나지 않게 하는 방법
    
    ```python
    # 데이터가 없지만 오류가 나지 않고 아무런 값도 나오지 않는다.
    s.get("rr")
    # 해당 데이터가 없다면 "None" 출력
    # 해당 데이터가 있다면 값 출력
    s.get("rr", "None")
    ```
    
- 더하기
    - 같은 인덱스의 데이터 값들이 더해진다
        - s+s

### Series ⇒ Numpy로 변환

- to_numpy()

### DataFrame (2차원)

- Numpy에서 Matrix
- 2차원 구조로 되어 있는 데이터
- DataFrame 생성
    - Series
    - Dictionary
    
    ```python
    pd.DataFrame(data=None, index=None, columns=None, dtype=None, copy=None)
    ```
    
    ```python
    # Series
    data = {
        "one": pd.Series([1.0, 2.0, 3.0], index=["a", "b", "c"]),
        "two": pd.Series([1.0, 2.0, 3.0, 4.0], index=["a", "b", "c", "d"]),
    }
    
    df = pd.DataFrame(data=data)
    df.index    # rows
    df.columns  # colums
    
    # Dictionary
    data2 = [{"a": 1, "b": 2}, {"a": 5, "b": 10, "c": 20}]
    pd.DataFrame(data=data2)
    
    # Series가 하나라도 가능하다
    ser = pd.Series(range(3), index=list("abc"), name="ser")
    pd.DataFrame(ser)
    ```
    

### DataFrame ⇒ Dictionary 로 변환

- to_dict()
- to_json()

### DataFrame 컬럼 선택, 추가, 삭제

```python
data = {
    "one": pd.Series([1.0, 2.0, 3.0], index=["a", "b", "c"]),
    "two": pd.Series([1.0, 2.0, 3.0, 4.0], index=["a", "b", "c", "d"]),
}

df = pd.DataFrame(data=data)

# 컬럼 추가 = Feature 추가
# df["컬럼명"] = Series 데이터
df['three'] = df['one']+df['two']
# Series데이터가 아닌 단순 변수라면
# 해당 컬럼 값들은 모든 값이 통일된다.
df['foo'] = 'bar'

# 조건 선택
df['one'] > 2
list(df['one'] > 2)   # [False, False, True, False]

# 컬럼삭제 (del)
# del df["컬럼명"]
del df["two"]

# 컬럼명 수정 => 길이가 맞아야 한다.
# DataFrame의 columns도 데이터 => 변수
df.columns = ['one', 'bar', 'two', 'three']
```

### 데이터 선택

- seaborn ⇒ 데이터를 그림으로 그려준다.

```python
import seaborn as sns

# 기본 제공 학습데이터
iris = sns.load_dataset("iris")
print(type(iris))

iris.head()   # 데이터 상위 5개
iris.head(10) # 데이터 상위 10개
iris.tail()   # 데이터 하위 5개
```

- isin()
    - 해당 값이 들어가 있는가
    
    ```python
    # True인 것만
    cond = iris['petal_length'].isin([1.4,1.3,1.5,1.7])
    iris[cond][:5]
    
    # False인 것만
    # ~ : not
    cond = iris['petal_length'].isin([1.4,1.3,1.5,1.7])
    iris[~cond][:5] # not 적용
    ```
    
- DataFrame으로 만드는 법
    
    ```python
    # DataFrame으로 만든다
    iris[['petal_length']]
    
    # Series로 만든다
    iris['petal_length']
    ```
    
- iloc
    - index location
    - index에 해당되는 값만 가져온다.
    
    ```python
    iris.iloc[4]
    iris.iloc[[1,2,-1]] # -1은 마지막 행번호를 나타냄(loc에서는 사용할 수 없음)
    ```
    
    - 행과 열 조회 (슬라이싱/인덱싱)
    
    ```python
    iris.iloc[:,:3]  # row는 전체, 컬럼은 0,1,2
    
    # 각각 가져오기 가능하다
    # 1번째, 4번째 row / 1번째, 2번째 컬럼
    iris.iloc[[1,4],[1,2]]
    ```
    
- loc
