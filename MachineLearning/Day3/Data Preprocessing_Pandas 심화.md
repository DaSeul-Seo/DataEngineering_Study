### 집합 - 합치기

### merge()

- 병합
- 합칠 때는 기준이 필요하다. (on)
    - on에 컬럼명을 써준다.
- 기준이 꼭 있어야 한다.
- 데이터를 기준으로 합쳐진다.

```python
left = pd.DataFrame(
    {
        "key": ["K0", "K1", "K2", "K3"],
        "A": ["A0", "A1", "A2", "A3"],
        "B": ["B0", "B1", "B2", "B3"],
    }
)

right = pd.DataFrame(
    {
        "key": ["K0", "K1", "K2", "K3"],
        "C": ["C0", "C1", "C2", "C3"],
        "D": ["D0", "D1", "D2", "D3"],
    }
)

result = pd.merge(left, right, on="key")
result
```

![1](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/2dcfb751-9c68-4a9c-b3b0-9cccf61b9ae4)


- 기준이 2개일 수도 있다.
    - 두 개가 다 참일 경우에 merge() 된다. (inner join ⇒ default)
    - 여러 개일 경우 여러 줄로 된다.

```python
left = pd.DataFrame(
    {
        "key1": ["K0", "K0", "K1", "K2"],
        "key2": ["K0", "K1", "K0", "K1"],
        "A": ["A0", "A1", "A2", "A3"],
        "B": ["B0", "B1", "B2", "B3"],
    }
)

right = pd.DataFrame(
    {
        "key1": ["K0", "K1", "K1", "K2"],
        "key2": ["K0", "K0", "K0", "K0"],
        "C": ["C0", "C1", "C2", "C3"],
        "D": ["D0", "D1", "D2", "D3"],
    }
)

result = pd.merge(left, right, on=["key1", "key2"])
result
```

![2](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/cf102be4-52af-4bd3-b5dc-cdf07cea181a)

### merge() → how=”left”

- 어떤 방식으로 merge()를 할 것인가?
    - 단순히 merge()를 하면 상당 수의 데이터가 달라지기 때문에
- left를 주로 쓴다.
- right의 데이터가 없더라도 left 값들은 살리겠다.
- right의 데이터가 없으면 결측치로 처리된다.

```python
result = pd.merge(left, right, how="left", on=["key1", "key2"])
result
```

![3](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/0f911dc0-8e69-43a7-a3ed-f05218517d77)


### merge() → how=”right”

- right는 잘 쓰지 않는다.
- 단순히 left와 right를 바꾸어주면 된다.
    - ⭐ result = pd.merge(right, left, how="left", on=["key1", "key2"])

```python
result = pd.merge(left, right, how="right", on=["key1", "key2"])
result
```

![4](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/66b021f1-b48b-4fcb-a976-2156ab26c992)


### merge() → how=”outer”

- left, right 다 살리겠다.
- 가장 데이터가 많을 것이다. ⇒ 모든 데이터를 다 살리기 때문에

```python
result = pd.merge(left, right, how="outer", on=["key1", "key2"])
result
```

![5](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/1c2152f3-095e-4334-bd82-22a29f831374)


### merge() → how=”inner”

- 교집합
- 두 개가 다 참일 경우에 merge() 된다.

```python
result = pd.merge(left, right, how="inner", on=["key1", "key2"])
result
```

![6](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/2c46fc9d-a28a-4958-bc16-922422c111f0)


### merge() 문제

```python
import seaborn as sns

df = sns.load_dataset('titanic') # 타이타닉 데이터 받아오기
df.head()

# sex의 unique한 값 확인
df['sex'].unique()
```

```python
# 여자 DataFrame
df_female = df.groupby(['sex']).get_group("female")
df_female.head()
df_female.shape

# 남자 DataFrame
df_male = df.groupby(['sex']).get_group("male")
df_male.head()
df_male.shape
```

- 1번
    
    ```python
    # inner join
    # keys = ['survived', 'pclass']
    # 결과 shape 으로 나타내기
    result = pd.merge(df_female, df_male, on=['survived', 'pclass'])
    result.shape
    ```
    
- 2번
    
    ```python
    # left join
    # df_male => 기준 데이터 셋
    # keys = ['who', 'embarked']
    # shape
    result = pd.merge(df_male, df_female, how="left", on = ['who', 'embarked'])
    result.shape
    ```
    
- 3번 (현업에서 가장 많이 씀)
    
    ```python
    df_male = df_male[['who', 'survived', 'pclass']]
    df_female = df_female[['who', 'sibsp', 'parch', 'fare']]
    
    # 1. left join -> df_male이 left
    # 2. key = who
    result = pd.merge(df_male, df_female, how = "left", on = "who")
    result
    
    # # 3. is_solo 컬럼 추가 -> 'sibsp', 'parch'의 합이 0인 경우는 True, 아니며 False
    def AddNum(a, b):
      return a + b
    
    result['is_solo'] = result.apply(lambda x: True if AddNum(x['sibsp'], x['parch']) == 0 else False, axis = 1)
    result
    # # 4. ['is_solo', 'who']의 그룹별 생존률은?
    grouped = result.groupby(["is_solo", "who"])
    grouped['survived'].agg('mean')
    ```
    
- 3번 강사님 풀이
    
    ```python
    # 강사님 풀이
    df_male = df_male[['who', 'survived', 'pclass']] 
    df_female = df_female[['who', 'sibsp', 'parch', 'fare']]
    
    # 1. left join -> df_male이 left
    # 2. key = who
    result = pd.merge(
        df_male[['who', 'survived', 'pclass']]
        , df_female[['who', 'sibsp', 'parch', 'fare']]
        , on=['who'], how='left')
    
    # 3. is_solo 컬럼 추가 -> 'sibsp', 'parch'의 합이 0인 경우는 True, 아니며 False 
    def is_solo(row):
      sibsp = row['sibsp']
      if sibsp is None:
        sibsp = 0 
      parch = row['parch']
      if parch is None:
        parch = 0 
    
      if sibsp + parch > 0:
        return False 
      else:
        return True 
    
    result['is_solo'] = result.apply(lambda row: is_solo(row), axis=1)
    # 4. ['is_solo', 'who']의 그룹별 생존률은? 
    result.groupby(['is_solo', 'who'])['survived'].agg('mean')
    ```
    

### concat()

- 기준 없이 그냥 합친다.
- ⭐ row를 합칠 때 주로 많이 쓴다.
- column은 merge()
- 합칠때, index 기준으로 합친다.
- merge()말고 concat()으로도 다 가능한데 merge()를 쓰는 이유
    - merge()는 index를 새로 만들어 준다.
    - concat()은 기존 index를 가지고 만들어진다.
    - concat()은 코드가 너무 길어진다.
    - ⭐ pandas의 대부분의 함수들이 index를 가지고 처리를 하는데 concat()은 index를 그대로 가져다 쓰기 때문에 추가 작업이 필요해서 merge()를 쓰는 것이 좋다.
    - ⭐ concat()을 쓸 경우 index를 재정의 해주어야 한다.
        - ignore_index = True
1. DataFrame & Series
    
    ```python
    df1 = pd.DataFrame(
        {
            "A": ["A0", "A1", "A2", "A3"],
            "B": ["B0", "B1", "B2", "B3"],
            "C": ["C0", "C1", "C2", "C3"],
            "D": ["D0", "D1", "D2", "D3"],
        },
        index=[0, 1, 2, 3],
    )
    
    print(df1.shape)
    df1.head()
    ```
    
    ```python
    s1 = pd.Series(["X0", "X1", "X2", "X3"], name="X")
    
    result = pd.concat([df1, s1], axis=1)
    result
    ```
    
    ![7](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/ec4263eb-68b9-4179-a36d-9ed40575e17a)

    
2. DataFrame & DataFrame
    
    ```python
    df4 = pd.DataFrame(
        {
            "B": ["B2", "B3", "B6", "B7"],
            "D": ["D2", "D3", "D6", "D7"],
            "F": ["F2", "F3", "F6", "F7"],
        },
        index=[2, 3, 6, 7],
    )
    
    print(df4.shape)
    df4.head()
    ```
    
    ```python
    result = pd.concat([df1, df4], axis=1)
    result
    ```
    
    ![8](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/b5bb93f6-d563-421a-b68c-e7ad9e94df96)

    
    - index 재정의
        - ignore_index 사용
    
    ```python
    # 결과 row와 기준(df1) row의 값이 같을때 reindex 사용
    result = pd.concat([df1, df4], axis=1).reindex(df1.index)
    result
    ```
    
    ![9](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/b11a8eb0-8e9c-48c3-a83c-adeaec24cb18)

    ```python
    result = pd.concat([df1, df4], ignore_index=True, sort=False)
    result
    ```
    
    ⭐ 현업에서 많이 쓴다.
    
    ![10](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/ce07f8fe-6c27-468f-ba62-f383f7057f58)


### concat() 문제

```python
import numpy as np
import pandas as pd
import seaborn as sns

df = sns.load_dataset('titanic') # 타이타닉 데이터 받아오기

df = df.reset_index()
df = df.rename(columns={'index':'user_id'})

df_tmp = df[['user_id', 'age', 'sibsp', 'parch']]
df_tmp1 = df[['user_id', 'alone', 'survived']]
# 위의 df_tmp 사용
# 1. df1 -> age가 10 미만
df1 = df_tmp.loc[df['age'] < 10]
df1.shape

# 2. df2 -> age가 20~30대(20보다 크거나 같고 40보다 작음)
df2 = df_tmp.loc[(df['age'] >= 20) & (df['age'] < 40)]
df2.shape

# 3. df_concat = row 방향으로 합치기
df_concat = pd.concat([df1, df2], axis = 0)
df_concat

# 4. df_concat['is_solo'] 생성

df_concat['is_solo'] = df_concat.apply(lambda row: True if (row['sibsp'] + row['parch']) == 0 else False, axis = 1)
df_concat.shape

# 5. df_merge = df_concat과 df_tmp1를 merge left -> key는  alone과 is_solo를 이용
# 5-1. key에 대한 컬럼명을 통일 시킬 순 없나?
# 5-2. merge()에 이런 이슈가 발생할 때도 처리할 수 있는 방법이 있지 않을까?
df_merge = pd.merge(df_concat, df_tmp1, how = "left", left_on = ["is_solo", "user_id"], right_on = ["alone", "user_id"])
df_merge.shape

# 6. df_merge에서 10미만의 생존률과 20~30대의 생존률 구하시오
result_10 = df_merge[df_merge["age"] < 10]
result_40 = df_merge[(df_merge["age"] >=20) & (df_merge["age"] <40)]
age_10 = result_10["survived"].agg("mean")
age_40 = result_40["survived"].agg("mean")
print(f"10미만 생존률 ==> {age_10}")
print(f"20-30대 생존률 ==> {age_40}")
```

- concat() 문제 - 강사님 풀이

```python
df = sns.load_dataset('titanic')

df = df.reset_index()
df = df.rename(columns={'index':'user_id'})

df_tmp = df[['user_id', 'age', 'sibsp', 'parch']]
df_tmp1 = df[['user_id', 'alone', 'survived']]

# 위의 df_tmp 사용 
# 1. df1 -> age가 10 미만 
df1 = df_tmp[df_tmp['age'] < 10 ]
# 2. df2 -> age가 20~30대(20보다 크거나 같고 40보다 작음)
c1 = df_tmp['age'] >= 20 
c2 = df_tmp['age'] < 40 
c = c1 & c2 
df2 = df_tmp.loc[c]

# 3. df_concat = row 방향으로 합치기
df_concat = pd.concat([df1, df2], ignore_index=True)
# 4. df_concat['is_solo'] 생성
df_concat['is_solo'] = df_concat.apply(lambda row: False if row['sibsp']+row['parch'] else True, axis=1)

# 5. df_merge = df_concat과 df_tmp1를 merge left -> key는  alone과 is_solo를 이용 
# 5-2. merge()에 이런 이슈가 발생할 때도 처리할 수 있는 방법(사용법)이 있지 않을까? 
df_merge2 = pd.merge(df_concat, df_tmp1, how='left', left_on=['user_id', 'is_solo'], right_on=['user_id', 'alone'])
print(df_merge2.shape, df_concat.shape)

# 6. df_merge에서 10미만의 생존률과 20~30대의 생존률 구하시오 
df_merge2['is_10'] = df_merge2['age'].map(lambda x: True if x < 10 else False)
df_merge2.groupby(['is_10'])['survived'].agg('mean')
```
