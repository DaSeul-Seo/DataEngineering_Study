1. 데이터 수집 (Load Data)
    1. total data (train data, test data, submissions data)
2. EDA (train data)
3. Data Preprocessing (train data, test data)
    1. Cleaning
    2. Encoding
    3. Scaling
4. 데이터 분리 (train data)
    1. 학습용 데이터 (train_f, train_t) ⇒ 모델 학습
    2. 학습용 검증 데이터 (valid_f, valid_t) ⇒ 모델 평가
5. Modeling (train_f, train_t)
    1. shape, 결측치 확인 필수
    2. 모델 정의 (+ 하이퍼파라미터)
        1. target에 따라 결정
        2. 분류모델 (catecory)
        3. 예측모델 (미래예측)
6. 학습 (train_f, train_t data)
7. 평가(train_f, train_t, valid_f, valid_t)
8. N번 실험
    1. 오버피팅이 일어나지 않았다면 N번 실험
9. Result 저장
    1. N번의 결과를 저장
    2. Dictionary List
        1. DataFrame 만들기가 쉽다.
        2. sort해서 가장 좋은 model select