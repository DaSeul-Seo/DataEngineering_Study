### 머신러닝 실습

- sklearn : 가장 많이 사용하는 것
    - https://scikit-learn.org/stable/
1. 기본 변수 생성
    
    ```python
    # easydict : 변수를 key, value
    import easydict
    args = easydict.EasyDict()
    
    # 실무에서는 random 변수를 많이 사용한다.
    # 그래야 학습이 잘 될 확률이 높아진다.
    # 떄문에 결과값이 매번 달라진다.
    # SEED : random 변수의 값을 고정해준다.
    args.SEED = 10
    args.target_col = 'target'  # target컬럼명을 알려준다.
    ```
    
2. 데이터 수집 (or 데이터 로드)
    1. 이진분류 (binary classification)
    2. ex) 암이 양성인지 음성인지 판단
    3. ex) 강아지인지 고양이인지 판단
    
    ```python
    from sklearn.datasets import load_breast_cancer
    
    # 데이터 로드 => breast_cancer에 담는다.
    breast_cancer = load_breast_cancer()
    # 로드된 데이터 확인
    dir(breast_cancer)
    # 컬럼 확인
    breast_cancer.feature_names
    # target 확인
    breast_cancer.target_names
    ```
    
3. 학습용/검증용 데이터 분리
    
    ```python
    import numpy as np
    import pandas as pd
    
    # sklearn 모듈에서 학습, 검증 데이터를 쪼개는 함수를 쓰겠다.
    from sklearn.model_selection import train_test_split
    
    # 학습용 데이터와 검증용 데이터를 나눈다.
    # DataFrame = 엑셀 표
    # 학습용 데이터
    df_cancer = pd.DataFrame(breast_cancer.data, columns= breast_cancer.feature_names)
    # 검증용 데이터
    df_cancer[args.target_col] = breast_cancer[args.target_col]
    
    print(df_cancer.shape)
    
    # train_test_split
    # random_state = random 상태
    # => 기본으로 항상 random으로 나누어 준다.
    # SEED => random_state 값을 고정해 항상 동일하게 나누게 한다.
    train, test = train_test_split(df_cancer,  random_state=args.SEED)
    
    # 학습, 검증 데이터 상태 확인
    # ((426, 31), (143, 31)) => 컬럼은 동일함
    # 학습 : 426개의 데이터, 컬럼은 31
    # 검증 : 143개의 데이터, 컬럼은 31
    train.shape, test.shape
    ```
    
4. 데이터 점검 및 탐색
    1. EDA (Exploratory Data Analysis) : 탐색적 데이터 분석
    2. test 데이터는 탐색하지 않음
    
    ```python
    train.head()     # 위에 데이터
    train.tail()     # 마지막 데이터
    train.info()     # 데이터 정보 (전체 컬럼, 데이터 형태, 전체 데이터량 등등)
    train.describe() # 통계값 (최소값, 최대값, 평균, 표준편차 등등)
    ```
    
6. 전처리 및 정제  (Pre-Processing)
    1. 필요없는 데이터 제거
    
    ![5](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/952ac4f1-6a84-48e6-b295-bd93a07311c5)
    ![7](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/c95676ec-dcc6-4bf7-9c95-47b93911c1a1)
    
    ```python
    # drop : 로우를 삭제할 것인지, 컬럼을 삭제할 것인지
    # axis : 축 / 0 = 로우축, 1 = 컬럼축
    # feature (학습 / 정답)
    x_train, x_test = train.drop(args.target_col, axis=1), test.drop(args.target_col, axis=1)
    # target (학습 / 정답)
    y_train, y_test = train[args.target_col], test[args.target_col]
    
    # ((426, 30), (426,)) => 컬럼이 1이면 아무것도 안쓰여진다.
    x_train.shape, y_train.shape
    # ((143, 30), (143,))
    x_test.shape, y_test.shape
    ```
    ```python
    from sklearn.preprocessing import StandardScaler
    
    # standardization
    # 전처리 작업
    # 각 컬럼의 기준이 다르다.  ex) 연봉 1000, 몸무게 500 크고 작은 것 기준을 세원준다.
    scaler = StandardScaler()
    train_scaled = scaler.fit_transform(x_train)
    test_scaled = scaler.transform(x_test)
    
    print(f'train.shape: {train.shape}')
    print('-'*50)
    print(f'train_scaled.shape: {train_scaled.shape}')
    
    train_scaled[:1,:]
    '''
    array([[-0.60408221, -0.30624914, -0.66228757, -0.59759609, -1.43340425,
            -1.28487074, -1.11254551, -1.12909991, -1.61436823, -0.31081757,
            -0.62155456, -0.5783191 , -0.6928    , -0.50501258, -0.76703944,
            -1.07161825, -1.2493379 , -1.30374665, -0.74688387, -0.81051627,
            -0.67577053, -0.55621057, -0.74396171, -0.63568485, -1.45739321,
            -1.18985953, -1.29522303, -1.34634798, -1.23731664, -0.76362748]])
    '''
    ```
    
5. 모델링 및 훈련
    1. 기본적으로 모델은 클래스이다.
    
    ```python
    # LogisticRegression => 회귀이지만 이진분류가 가능하다.
    from sklearn.linear_model import LogisticRegression
    
    # logistic regression
    lr_clf = LogisticRegression()
    
    # 훈련
    # fit : 학습
    # 학습용 Feature, 학습용 target
    lr_clf.fit(x_train, y_train)
    ```
    
6. 평가
    
    ```python
    from sklearn.metrics import accuracy_score
    
    # 예측
    # predict : 예측, 검증
    pred = lr_clf.predict(x_test)
    
    # 정확도 측정
    # 검증용 target
    accuracy_score(y_test, pred)
    ```
