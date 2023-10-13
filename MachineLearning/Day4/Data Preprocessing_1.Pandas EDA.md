### 교차분석

- crosstab
    - 기준에 따라 결과가 달라진다.

```python
pd.crosstab(df_object["sex"],df_object["survived"],margins = True)
```

![1](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/2f1c1509-3630-491f-acc6-41a77f3f5656)


```python
 # 전체 기준 데이터 비율
pd.crosstab(df_object["sex"],df_object["survived"],margins=True, normalize= "all")
```

![2](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/c051c60f-6177-4f87-a405-b59b5c7dc160)


```python
# index 기준 데이터 비율
pd.crosstab(df_object["sex"],df_object["survived"],margins=True, normalize= "index")
```

![3](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/50ea7397-14d6-4cb3-9d1d-52c7a50fa829)


```python
# columns 기준 데이터 비율
pd.crosstab(df_object["sex"],df_object["survived"],margins=True, normalize= "columns")
```

![4](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/d68da03b-3cb2-4dd7-b5ab-2f415a824aed)


### pivot_table 분석

```python
df_pivot = pd.pivot_table(df,                # 피벗할 데이터프레임
                     index = 'pclass',    # 행 위치에 들어갈 열
                     columns = 'sex',    # 열 위치에 들어갈 열
                     values = 'survived',     # 데이터로 사용할 열
                     aggfunc = ['mean', 'sum'])   # 데이터 집계함수

df_pivot
# 3등석 보다 1등석에 탄 경우, 생존여부가 높게 나타나며,
# 1등석, 2등석, 3등석 모두에서 여자가 남자보다 생존여부가 높게 나타나고 있다.
```

![5](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/518e4052-b4d6-486d-8b9d-25e8a1152294)
