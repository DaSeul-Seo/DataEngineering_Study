### 선형회귀

- 미래를 예측하는 모델
1. 단순 선형 회귀 분석(Simple Linear Regression Analysis)
    1. $y = wx + b$
    2. 가중치 W(weight), 편향b(bias)
    3. x는 Feature, y는 Target
    
    ![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/f0f0683f-64d5-4a3d-b1be-c1e38dc820c9)
    
2. 다중 선형 회귀 분석(Multiple Linear Regression Analysis)
    1. $y = w1x1 + w2x3 + ... + wnxn + b$
    2. 집의 매매 가격은 단순히 집의 평수가 크다고 결정되는 게 아니라 집의 층 수, 방의 개수, 지하철 역과의 거리 등에도 영향이 있습니다. 이러한 다수의 요소를 가지고 집의 매매 가격을 예측할때 다중 선형 회귀 분석을 사용합니다.
    
    ![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/db9dbbfe-00e1-439c-9ca1-cbe0dda57136)

### ⭐ 정규화 (Regularization)

- 모델을 학습할 때 비용(cost) 즉, 오류를 최소화하는 방향으로 진행
- W(weight)가 너무 큰 값들을 가지지 않도록 하는 것이다.
- W가 너무 큰 값을 가지게 되면 과하게 구불구불한 형태의 함수가 만들어지는데, Regularization은 이런 모델의 복잡도를 낮추기 위한 방법이다.
- Regularization은 단순하게 cost function을 작아지는 쪽으로 학습하면 특정 가중치 값들이 커지면서 결과를 나쁘게 만들기 때문에 cost function을 바꾼다.
1. L1 Regularization
    1. 가중치의 합을 더한 값에 learning rate(학습률) λ 를 곱하여 오차에 더한다.
    2. L(로스함수) : 실제 값 - 예측값
    3. 알파(λ) : 학습률
    4. 절대값이 존재
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/07dc6cc8-bccd-400f-a946-4887013b3f66)

2. L2 Regularization
    1. 각 가중치 제곱의 합에 learning rate(학습률) λ 를 곱한다.
    2. learning rate(학습률) λ 를 크게 하면 가중치가 더 많이 감소되고, λ 를 작게 하면 가중치가 증가한다.
    3. L2 규제가 L1 규제에 비해 더 안정적이라 일반적으로는 L2 규제가 더 많이 사용된다.
    4. 제곱값이 존재
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/b20f38e5-780d-4c4d-8d7b-22a0ea6b900a)
    

### 회귀의 선형 모델

1. LinearRegression
    1. 최소제곱법(Ordinary Least Squares)을 활용.
    2. 실제값에서 예측값을 뺀 차이의 제곱에 합
    
    ![5](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/35818ddf-fa58-406f-8b60-f55ece92087f)

