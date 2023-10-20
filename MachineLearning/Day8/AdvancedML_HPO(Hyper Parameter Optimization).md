### Parameter

1. Model Parameter
    1. weight, bias
    2. 모델이 스스로 해당 파라미터 값을 초기화하고 업데이트
2. Hyper Parameter
    1. 사람이 임의로 setting

![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/4093eedc-1ce3-47fa-90d4-02ec93d7500a)

### HPO 탐색 방법

1. Grid Search
    1. 사람이 범위를 정해준다.
    2. 모델이 스스로 그 중 알아서 조합해서 학습한다.
    
    ```python
    from sklearn.model_selection import GridSearchCV
    
    hp={
        "max_depth" : np.linspace(5,12,8,dtype = int), # 깊이
        "criterion" : ["gini","entropy"], # 순수도 척도
        "n_estimators" : np.linspace(800,1200,5, dtype = int), # 부스팅 단계수
        "learning_rate" : np.logspace(-3, -1, 3)
    }
    model = LGBMClassifier(random_state=random_state)
    
    # 그 중에 가장 좋은 것 추출
    # cv : cross validation
    gs=GridSearchCV(model, hp, scoring='roc_auc', n_jobs=-1, cv=kf, verbose=False).fit(X_tr,y_tr)
    ```
    
    ```python
    # 제일 좋은 파라미터
    gs.best_params_
    
    # 제일 좋은 점수
    gs.best_score_
    
    # test 점수 확인
    gs.score(X_te,y_te)
    ```
    
    - 영향을 미치는 파라미터들
        - learning_rate
    
    ```python
    gs_results_df=pd.DataFrame(np.transpose([gs.cv_results_['mean_test_score'],
                                             gs.cv_results_['param_learning_rate'].data,
                                             gs.cv_results_['param_max_depth'].data,
                                             gs.cv_results_['param_n_estimators'].data]),
                               columns=['score', 'learning_rate', 'max_depth', 'n_estimators'])
    gs_results_df.plot(subplots=True,figsize=(10, 10))
    ```
    
    ![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/f13f8a0f-518f-4d17-887f-88be9bfcd075)

2. Random Search
    1. 많이 사용
    2. 랜덤 구간을 정해서 학습
    3. 속도도 빠르다.
    
    ```python
    from sklearn.model_selection import RandomizedSearchCV
    
    hp={
        "max_depth" : np.linspace(5,12,8,dtype = int), # 깊이
        "criterion" : ["gini","entropy"], # 순수도 척도
        "n_estimators" : np.linspace(800,1200,5, dtype = int), # 부스팅 단계수
        "learning_rate" : np.logspace(-3, -1, 3)
    }
    model = LGBMClassifier(random_state=random_state)
    
    rs=RandomizedSearchCV(model, hp, scoring='roc_auc', n_iter=n_iter, n_jobs=-1, cv=kf, verbose=False).fit(X_tr,y_tr)
    rs.best_score_, rs.score(X_te,y_te)
    ```
    
    - 영향을 미치는 파라미터들
        - learning_rate
    
    ```python
    rs_results_df=pd.DataFrame(np.transpose([rs.cv_results_['mean_test_score'],
                                             rs.cv_results_['param_learning_rate'].data,
                                             rs.cv_results_['param_max_depth'].data,
                                             rs.cv_results_['param_n_estimators'].data]),
                               columns=['score', 'learning_rate', 'max_depth', 'n_estimators'])
    rs_results_df.plot(subplots=True,figsize=(10, 10))
    ```
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/4e8d18ff-42fc-434f-9308-300d5eddd881)

    - Grid Search VS Random Search
        - 낮은 값이 좋다. (loss 값)
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/9725ea45-2450-4c1a-a6fd-6af78ab4e5af)

3. Bayesian Search 알고리즘
    1. Grid나 Random은 최고의 점수를 찾을 수 있다는 보장이 없다.
    2. 최고점의 그래프를 알 수만 있다면 최고의 점수를 찾을 수 있다.
    3. 연산을 통해서 최고의 그래프를 그릴 수 있다.
    4. 시간이 오래 걸린다는 단점이 존재.
    5. 최후의 방법으로 사용. 그 전까지는 Random Search 사용하자.
    
    ```python
    # 라이브러리 설치
    !pip install optuna
    ```
    
    ```python
    class Objective:
        def __init__(self,x_train,y_train,seed):
            self.x_train = x_train
            self.y_train = y_train
            self.seed = seed
            num_folds=2 # 학습시간을 줄이기 위해 2로 하였다. 일반적으로는 5
            self.cv = KFold(n_splits=num_folds,shuffle=True,random_state=self.seed) # 수정
        def __call__(self,trial):
          # 수정
            hp = {
                "max_depth" : trial.suggest_int("max_depth",2,5),
                "min_samples_split" : trial.suggest_int("min_samples_split",2,5),
                "criterion" : trial.suggest_categorical("criterion",["gini","entropy"]),
                "max_leaf_nodes" : trial.suggest_int("max_leaf_nodes",5,10),
                "n_estimators" : trial.suggest_int("n_estimators",10,500,50),
                "learning_rate" : trial.suggest_float("learning_rate",0.01,0.1)
            }
            # 모델 변경 시 수정
            model = LGBMClassifier(random_state=self.seed,**hp)
            scores = cross_val_score(model,self.x_train,self.y_train, cv = self.cv , scoring="roc_auc")
            return np.mean(scores)
    ```
    
    ```python
    sampler = TPESampler(seed=random_state) # 대체모델 부분
    
    # 스터디 객체
    study = optuna.create_study(
    		# 그래프에서 높은 것을 기준으로 좋은 것인지, 낮은 것이 좋은 것인지 설정
        direction = "maximize", # maximize or minimize
        sampler = sampler
    )
    objective = Objective(X_tr,y_tr,random_state)
    study.optimize(objective,n_trials=50)
    
    print("Best Score:", study.best_value) # 최고점수
    print("Best hp", study.best_params) # 최고점수의 하이퍼파라미터조합
    ```
    
    ```python
    model = LGBMClassifier(random_state=random_state, **study.best_params)
    model.fit(X_tr,y_tr)
    
    pred = model.predict_proba(X_te)[:,1]
    roc_auc_score(y_te,pred)
    ```
    
    - 파라미터 중요도
    
    ```python
    optuna.visualization.plot_param_importances(study)
    ```
    
    ![5](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/26fe78dc-9c54-44c2-bfbd-644286ea1c20)
