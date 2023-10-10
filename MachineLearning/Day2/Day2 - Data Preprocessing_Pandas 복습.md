- Pandas를 쓰는 이유
    - 데이터 전처리를 위해
- 자주쓰는 함수
    - abs, max, min, median, mean
    - describe : 전체 통계 보기
- Series(Vector)일 경우 차원을 표기하지 않았지만 Matrix나 Tensor는 차원을 표기해야 한다.
    - max, min 등을 사용하면 차원이 줄어든다.
    - Matrix의 결과값 ⇒ Series
    - Series의 결과값 ⇒ Scalar
- DataFrame
    - shape ⇒ (row, col) ⇒ (데이터량, Feature)
- seaborn
    - 기본적으로 DataFrame
- EDA
    - 내가 가진 데이터를 눈으로 확인하는 과정
- iloc
    - 위치 값을 사용하고 싶을 경우
    - index 사용
    - -1 사용 가능
    
    ```jsx
    iris.iloc[[1,4],[1,2]]
    ```
    
- loc
    - -1 사용 불가능
    - 컬럼명을 사용해야 한다.
    
    ```jsx
    iris.loc[[1,4],['sepal_width', 'petal_length']]
    ```
    

### 문제1

- 1. species 값이 setosa이고
  2. petal_length가 1.4 또는 1.5 또는 1.3
  3. sepal_length가 5.0보다 크거나 같음
  4. iloc 또는 loc 쓰는 것을 추천
    
    ```jsx
    cond1 = iris['species'] == 'setosa'
    cond1
    
    cond2 = iris['petal_length'].isin([1.4, 1.5, 1.3])
    cond2
    
    cond3 = iris['sepal_length'] >= 5.0
    cond3
    
    cond = cond1 & cond2 & cond3
    iris[cond].head()
    ```
    

### 문제2

- 1. species 값이 setosa이면서 and
  2. petal_length가 1.4 또는 1.5 또는 1.3 포함되지 않고
  3. or 또는 sepal_length가 5.0보다 크거나 같음
  4. iloc 또는 loc 쓰는 것을 추천
    
    ```
    rst1 = iris.iloc[:,4] == 'setosa'
    rst2 = iris.iloc[:,2].isin([1.4, 1.5, 1.3])
    rst3 = iris['sepal_length'] >= 5.0
    
    rst = (rst1 & ~rst2) | rst3
    iris.loc[rst]
    ```
    

### 문제3

- 결과검증
- 문제1, 문제2 결과검증
    
    ```python
    # 코드로 검증하기
    
    # 1. species 값이 setosa가 아닌 데이터 찾기
    # or
    # 2. petal_length가 1.4 또는 1.5 또는 1.3 가 아닌 데이터 찾기
    # or
    # 3. sepal_length가 5.0보다 크거나 같음 가 아닌 데이터 찾기
    
    # 위의 조건을 적용했을 때, 데이터가 없으면 참
    
    cond1 = iris['species'] == 'setosa'
    cond2 = iris['petal_length'].isin([1.4, 1.5, 1.3])
    cond3 = iris['sepal_length'] >= 5.0
    
    cond = cond1 & cond2 & cond3  # 결과를 뽑은 상태로 Series
    rst = iris[cond] # DataFrame 화
    
    rst.loc[~cond1 | ~cond2 | ~cond3].shape
    ```
    
    ```python
    # 1. species 값이 setosa이면서 and 가 아닌 데이터
    # 2. petal_length가 1.4 또는 1.5 또는 1.3 포함되지 않고 가 아닌 데이터
    # 3. or 또는 sepal_length가 5.0보다 크거나 같음 가 아닌 데이터
    # 4. iloc 또는 loc 쓰는 것을 추천
    
    rst1 = iris.iloc[:,4] == 'setosa'
    rst2 = iris.iloc[:,2].isin([1.4, 1.5, 1.3])
    rst3 = iris['sepal_length'] >= 5.0
    
    rst = (rst1 & ~rst2) | rst3 # 결과를 뽑은 상태로 Series
    result = iris.loc[rst] # DataFrame 화
    
    result.loc[(~rst1 | rst2) & ~rst3].shape
    ```
