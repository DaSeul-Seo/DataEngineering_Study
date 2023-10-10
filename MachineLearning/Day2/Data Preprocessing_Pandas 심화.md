- seaborn
    - 기본적으로 DataFrame
- Null 확인
    
    ```python
    df.isnull()
    ```
    
    - 해당 컬럼의 null 비율
    
    ```python
    # 1. 컬런별 null 수
    # 2. 전체 데이터 수 
    # 컬럼별 null 비율 = (1 / 2) * 100
    per = (df.isnull().sum() / df.shape[0] * 100).round(2)
    per
    ```
    
- NaN
    - 데이터가 아니다.
    - 값 치환이나 제거하여 사용
    - 내가 가지고 있는 데이터 중에 컬럼별 Null이 몇 개인지 파악해야 한다.
- category
    - 특정 String : 하나의 그룹화된 범주
    - ex) 남, 여

### 통계

- ⭐ count(), sum(), mean(), median(), min(), max(), abs(), std()
- select
    - 숫자와 숫자가 아닌 것을 분리한다. ⇒ 통계기법 사용하기 위해
    - column만 필터링 된다.
    
    ```python
    # 숫자가 포함된 데이터를 DataFrame으로 추출 (include)
    df_number = df.select_dtypes(include=np.number)
    # 숫자가 아닌 데이터를 DataFrame으로 추출 (exclude)
    df_object = df.select_dtypes(exclude=np.number)
    ```
    
- mean()
    
    ```python
    # DataFrame에서 사용하면 컬럼별 통계값을 얻을 수 있음
    df_number.mean()
    '''
    survived     0.383838  => 생존률
    pclass       2.308642  => 의미 없음
    age         29.699118
    sibsp        0.523008
    parch        0.381594
    fare        32.204208
    dtype: float64
    '''
    
    # 숫자1을 인풋파람에 넣으면, 로우 기준으로 통계값을 얻을 수 있음
    df_number.mean(1)
    '''
    0       5.541667
    1      18.713883
    2       6.320833
    3      15.183333
    4       7.675000
             ...    
    886     7.000000
    887     8.500000
    888     5.890000
    889     9.666667
    890     7.125000
    Length: 891, dtype: float64
    '''
    ```
    
- 표준편차, 분산
    - 평균을 기준으로 얼마나 차이가 있는지
    - 데이터의 흩어짐 정도
    
    ```python
    # 가장 큰수 - (표준편차 + 가장 작은 수)df_number.var() # 분산
    df_number.max() - (df_number.std() + df_number.min())
    
    # 분산
    df_number.var()
    ```
    

### value_counts()

- value에 대한 각각의 개수
- 💡object, category에서 많이 사용
    
    ```python
    df_object['class'].value_counts()
    '''
    Third     491
    First     216
    Second    184
    Name: class, dtype: int64
    '''
    
    df_object['embarked'].value_counts()
    '''
    S    644
    C    168
    Q     77
    Name: embarked, dtype: int64
    '''
    ```
    

### **unique(), nunique()**

- python에서 set
- nunique : unique한 갯수
- 중복이 아닌 유일한 값
- 💡object, category에서 많이 사용
    
    ```python
    df_object['class'].unique()
    '''
    ['Third', 'First', 'Second']
    Categories (3, object): ['First', 'Second', 'Third']
    '''
    
    df_object['class'].nunique()   # 3
    ```
    

### describe()

- 데이터에 대한 통계값들
    
    ```python
    # 수 & 카테고리 데이터 전부에 대한 통계값들
    df.describe(include='all')
    ```
    
    - count : 갯수 ( 숫자, 문자열)
    - unique : 숫자 X, 문자열 O
    - top
        - 최빈값은 뭐야?
        - unique 중 하나의 값이 나옴
        - 숫자 X, 문자열 O
    - freq
        - 최빈값의 갯수
        - 숫자 X, 문자열 O
    - mean : 평균
    - std : 표준편차
    - min : 최소값
    - 25%
    - 50%
    - 75%
    - max : 최대값

### ⭐ map()

- 데이터의 value와 다른 데이터의 index가 동일할 때 치환
- map은 하나의 컬럼(Series)만 적용 가능


![1](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/fc6c2802-653e-459d-8c42-70d42b449139)

```python
x = pd.Series({'one':1,'two':2,'three':3})
y = pd.Series({1:'triangle',2:'square',3:'circle'})

x
'''
one      1
two      2
three    3
dtype: int64
'''
y
'''
y
account_circle
1    triangle
2      square
3      circle
dtype: object
'''
```

```python
# x의 value, y의 index 값이 동일할 때 치환
x.map(y)
'''
one      triangle
two        square
three      circle
dtype: object
'''

# x의 value, y의 index 값이 동일하지 않기에 NaN
y.map(x)
'''
1   NaN
2   NaN
3   NaN
dtype: float64
'''
```

- map 3가지 방법

```python
replace_dict = {"male":1, "female":0}
df['sex'].map(replace_dict)[:5]

# 동일
dt_tmp['sex'] = dt_tmp['sex'].map(lambda x: replace_dict[x])
dt_tmp['sex'][:5]

# 동일
df['sex'].map(lambda value: 1 if value == 'male' else 0)[:5]
```

### ⭐ apply()

- map과 다르게 2 ~ 3개의 데이터를 조작할 수 있다.
- matrix 단위로 처리하고 싶을 경우 사용
- axis의 default값은 0(column )이기에 생략 가능

```python
# column 합계 axis=0 생략 => 0이 default
df_tmp.apply(lambda x:x.sum())
# df_tmp.apply(lambda x:x.sum(), axis=0)

# row 합계
df_tmp.apply(lambda x:x.sum(), axis=1)
```

```python
# row 합
df_tmp['row_sum'] = df_tmp.apply(lambda x:x.sum(), axis=1)
# a+2
df_tmp['a+2'] = df_tmp.apply(lambda x:x['a']+2, axis=1)

# ⭐ 함수 사용 가능
def tmpFnc(a,b):
  return a+b

df_tmp['a+b'] = df_tmp.apply(lambda x:tmpFnc(x['a'], x['b']), axis=1)
```

### apply() 문제

```python
# 문제1
# 'group_number' : sibsp + parch
def PlusValue(x, y):
  return x + y

df1['group_number'] = df1.apply(lambda x: PlusValue(x['sibsp'], x['parch']), axis = 1)

# 문제2
# 1. sex값이 male 이면서 who 값이 man이면 True
# 2. sex값이 female 이면서 who 값이 woman이면 True
# 3. 그 외는 False
# 'is_gender'에 해당 결과 저장

def CheckGender(a, b):
  if (a == 'male' and b == 'man') | (a == 'female' and b == 'woman'):
    return True
  else:
    return False

df1['is_gender'] = df1.apply(lambda x: CheckGender(x['sex'], x['who']), axis=1)
```

### ⭐ 집합

- 집합 만들기
1. pivot_table
    1. 특정 컬럼을 index와 cloumn을 재정의 한다.
    2. value : 값을 넣어준다.
    3. aggfunc : 집합 함수
    
    ```python
    pd.pivot_table(df_groupby, index='sex', columns='pclass', values='age', aggfunc='mean')
    ```
    
    ![2](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/260fc47a-b7af-42db-99d7-6a4201e14fd9)

    
    - female 이면서 pclass가 1인 사람들의 평균나이는 34세 이다.
2. groupby
    1. unique한 값들로 그룹핑
    2. 좀 더 detail 한 insite를 얻을 수 있다.
    3. 여러 개 groupby도 가능하다.
    
    ```python
    grouped = df_groupby.groupby(['sex','pclass'])
    ```
    
3. agg
    1. 그룹핑할 때 사용. groupby보다 좀 더 직관적
    2. ex) 요즘에 대한 합과 평균
        
        ```python
        df_groupby['fare'].agg(['sum', 'mean'])
        df_groupby['fare'].agg(lambda x:x.sum())
        ```
        
    3. groupby()와 같이 사용
        
        ```python
        grouped = df_groupby.groupby(['sex'])
        grouped.agg(['min', 'max'])
        ```
        
        ![3](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/acdf0b40-e310-48d1-bfb8-fe6d78365e46)

        
        - dictionary로 사용 가능
            - 특정 컬럼별로 값을 조정 가능
        
        ```python
        agg_dict = {
            'fare': ['min', 'max'],
            'age': 'mean'
        }
        
        grouped.agg(agg_dict)
        ```
        
        ![4](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/9ad5680d-f295-48e8-9c6c-e29dec0ef846)

        
        - 함수 사용 가능
        
        ```python
        def min_max(x):
          return x.max() - x.min()
        
        grouped.agg(min_max)
        ```
        
        ![5](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/2ae9f364-c84d-48ef-b088-124d60eddd44)

        
4. transform()

### 집합문제

```python
# sibsp + parch => 동반자들의 숫자 (가족 숫자)
def PlusValue(x, y):
  return x + y

df['family_num'] = df.apply(lambda x: PlusValue(x['sibsp'], x['parch']), axis = 1)

# 가장 생존률이 높은 가족 숫자는?
# 1. family로 groupby 한다.
grouped = df.groupby(['family_num'])
# 2. survived를 agg 이용해서 평균을 구한다.
grouped['survived'].agg('mean')
# 3. 어떤 family가 가장 높은 생존률을 갑는지 알 수 있음
```

```python
# sibsp + parch => 동반자들의 숫자 (가족 숫자)
# 가장 생존률이 높은 가족 숫자는?
def PlusValue(x, y):
  return x + y

df['family_num'] = df.apply(lambda x: PlusValue(x['sibsp'], x['parch']), axis = 1)

pd.pivot_table(df, index = 'family_num', values = 'survived', aggfunc = 'mean')
```

```python
# sibsp + parch => 동반자들의 숫자 (가족 숫자)
# 가장 생존률이 높은 가족 숫자는?
def PlusValue(x, y):
  return x + y

df['family_num'] = df.apply(lambda x: PlusValue(x['sibsp'], x['parch']), axis = 1)

grouped = df.groupby(['family_num'])

grouped['survived'].agg('mean')
```

- 교집합, 합집합, 차집합
