- 모델, Feature, Scale 유무, Encoding 유무 작성
- score ⇒ 나의 이유 ( ~~ 이유 때문에 이렇게 나온 것 같다.)
- 증가했다면, 처음 결과와 비교.
    - 다른 방법으로 했더니 이렇게 되었다.
    - Scale을 Standard에서 MinMax로 변경했더니 score가 증가(감소)했다.
    - Feature를 어찌어찌 했더니 증가했더라.

# 모델 성능 평가지표(Metric)

## 회귀 모델 측정 : 예측에 대한 평가지표

### 선형회귀모델 (Linear Regression)

```python
from sklearn.linear_model import LinearRegression

# 모델 생성
model = LinearRegression()
# 모델 학습 (Feature, Target)
# Feature는 결측치가 있으면 안된다.
# 모델은 문자를 인식할 수 없다. 
# (train, test에 문자열이 있으면 안된다. => Encode)
# x_train과 y_train은 row가 같아야 한다.
model.fit(x_train,y_train)
# 모델 예측 (예측할 Feature 데이터)
# x_train과 x_valid는 column이 같아야 한다.
pred = model.predict(x_valid)

# pred와 y_valid는 row 수가 같아야 한다.
pred.shape, y_valid.shape
```

- $y = ax + b$ ⇒ 딥러닝에서도 쓰인다.
- 예측 값과 실제 값의 차이
    - 차이를 줄이고자 한다. ⇒ a와 b의 값을 바꾸면서 학습
- Error는 음수가 나오면 안되기에 제곱, 루트, 절대값을 사용한다.
1. ⭐ $R^2$ (결정 계수 : Confficient of Determination) : 1에 가까울수록 학습 Good
    1. (예측 값(그래프) - 평균 값) / (실제 값 - 평균 값 (변동성))
    2. 분자가 더 크면 0에 가까워진다.
    3. 분모가 더 크면 1에 가까워진다.
        
        ![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/844ed2cd-18ed-462d-a516-3b8712740395)

        ![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/c8cc8a8f-d3c7-445b-9073-e5a9871c93ee)
        
        ```python
        from sklearn.metrics import r2_score
        
        # (실제값, 예측값)
        r2 = r2_score(y_valid, pred)
        r2
        ```
        
2. ⭐MSE (Mean Squared Error) : 평균의 제곱의 차이
    1. Error : 실제값 - 예측값
    2. Error를 제곱한다.
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/e0b8af46-6655-4a22-95c7-aefb4389c535)

    ```python
    from sklearn.metrics import mean_squared_error
    
    # (실제값, 예측값)
    mse = mean_squared_error(y_valid, pred)
    mse
    ```
    
3. RMSE (Root Mean Squared Error)
    1. 일반적으로 많이 사용
    2. Error는 음수가 나오면 안되기에 MSE에 루트를 씌운다.
    3. 실제 오차의 평균 값을 알 수 있다.
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/89dc5f41-fdf8-4fbe-a705-e52159b73a58)

    ```python
    import numpy as np
    
    np.sqrt(mse)
    ```
    
4. RMSLE (Roor Mean Squared Logarithmic Error)
    1. 로그 함수 적용
    2. 0이거나 특이한 값이 나오는 경우가 존재하기에 +1을 해준 것.
        1. +1 정도는 결과에 크게 영향이 없기에 사용한 것이다.
    
    ![5](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/75522fd9-0932-4d3a-b3fb-b41adea162d3)

    ```python
    def rmsle(y, pred, convertExp=False):
      if convertExp:
        y = np.exp(y)
        pred = np.exp(pred)
    
      log1 = np.nan_to_num(np.array([np.log(v + 1) for v in y]))
      log2 = np.nan_to_num(np.array([np.log(v + 1) for v in pred]))
      calc = (log1 - log2)**2
    
      return np.sqrt(np.mean(calc))
    ```
    
    ```python
    rmsle_score = rmsle(y_valid, pred)
    rmsle_score
    ```
    
5. ⭐ MAE (Mean Absolute Error)
    1. Error를 절대값 해서 평균을 낸다.
    2. 기준이 애매하다.
    
    ![6](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/5a90e44e-0b3b-418e-9647-260c80d9e6e2)

    ```python
    from sklearn.metrics import mean_absolute_error
    
    mae = mean_absolute_error(y_valid, pred)
    mae
    ```
    
6. MAPE (Mean Absolute Percentage Error)
    1. Error를 절대 값 해서 평균을 낸다.
    2. 실제 값을 나눈다.
    3. 그 결과를 합한다.
    4. MAE가 결과 기준이 애매하기에 Percentage로 표시한다.
    
    ![7](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/f3104cfe-a0c5-4de0-9228-275c369fc22e)

    ```python
    from sklearn.metrics import mean_absolute_percentage_error
    
    mean_absolute_percentage_error(y_valid, pred)l
    ```
    
7. SMAPE (Symmetric Mean Absolute Percentage Error)
    1. MAPE에서 분모가 0이면 무한이기 때문에 결과 값을 보장을 못한다.
    
    ![8](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/640ffee4-fc4e-413f-a25d-c00ca6e646ed)

    ```python
    def smape(true,pred):
        error = np.abs(true-pred) / (np.abs(true) + np.abs(pred))
        return np.mean(error)
    
    smape(y_valid,pred)
    ```
