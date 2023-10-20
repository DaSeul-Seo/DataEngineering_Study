- 차원축소 : Feature 줄이기

### 차원의 저주 (The curse of dimensionality)

- 학습을 위해 차원이 증가하면서 학습데이터 수가 차원의 수보다 적어져 성능이 저하되는 현상
- One Hot Encoding 때 많이 일어난다.

![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/e02c24f4-5925-4759-96bf-84d782715109)

- 1차원은 데이터가 밀집되어 있다.
- 2차원은 1차원보다 흩어져 있다.
- 3차원은 2차원보다 흩어져 있다.
- 차원이 증가할수록 데이터량은 그대로
- One Hot Encoding을 하고 차원을 축소하자!!

### 차원축소

1. 주성분 분석(PCA; Principal component Analysis)
    1. n_components: 주성분의 수 ⇒ 몇 개의 차원으로 줄여줄까?
    2. 데이터를 기준으로 수평, 수직으로 재정의 한다.
    
    ![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/72a73820-7fb5-40d1-b184-29e756a602ab)

    ```python
    from sklearn.decomposition import PCA
    
    sparse_features.shape[1]
    
    # n_components : 2개의 차원으로 줄여줘
    pca5 = PCA(n_components=2,random_state=SEED)
    pca5.fit(sparse_features)
    # 주성분에 의해 설명되는 분산비율
    sum(pca5.explained_variance_ratio_)
    
    tmp = pd.DataFrame(pca5.transform(sparse_features)).add_prefix("pca_")
    x_train = pd.concat([features,tmp],axis=1)
    print(f'after: {x_train.shape}')
    
    model = LGBMClassifier(random_state=SEED)
    scores = cross_val_score(model,x_train,y_train,cv = cv , scoring="roc_auc",n_jobs=-1)
    print(f'score: {scores.mean()} / base_score: {base_score}')
    ```
    
2. 특이값 분해(SVD; Singular Value Decomposition)
    1. 음원, 음성 분석할 때 많이 사용
    2. 하나의 행렬을 3개로 분해한다.
    3. Feature 데이터를 새로운 Feature DF로 쪼갤 수 있다.
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/75546fa1-d344-47ea-8388-253b490e6973)

    ```python
    from sklearn.decomposition import TruncatedSVD
    
    sparse_features.shape[1]
    
    svd = TruncatedSVD(n_components=sparse_features.shape[1],random_state=SEED)
    svd.fit(sparse_features)
    sum(svd.explained_variance_ratio_)
    
    tmp = pd.DataFrame(svd.transform(sparse_features)).add_prefix("svd_")
    x_train = pd.concat([features,tmp],axis=1)
    print(f'after: {x_train.shape}')
    
    model = LGBMClassifier(random_state=SEED)
    base_score = cross_val_score(model,x_train,y_train,cv = cv , scoring="roc_auc",n_jobs=-1).mean()
    print(f'base_score: {base_score}')
    ```
    
3. 비음수 행렬 분해(NMF; Non-negative Matrix Factorization)
    1. 추천 시스템 때 많이 사용
    2. 하나의 행렬을 2개로 분해한다.
    3. 양수로만 분류하는 행렬 ⇒ 무조건 양수가 나온다.
    4. 원래데이터 = 가중치 행렬 X 특성행렬
        1. 가중치 행렬을 사용한다.
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/547228df-f551-4bf9-9b4d-ca7133b5d990)
    
    ```python
    from sklearn.decomposition import NMF
    
    nmf = NMF(n_components=sparse_features.shape[1], random_state=SEED, max_iter=500)
    nmf.fit(sparse_features)
    
    (nmf.components_ < 0).sum(), nmf.components_.shape
    
    tmp = pd.DataFrame(nmf.transform(sparse_features)).add_prefix("nmf_")
    x_train = pd.concat([features,tmp],axis=1)
    print(f'after: {x_train.shape}')
    
    model = LGBMClassifier(random_state=SEED)
    base_score = cross_val_score(model,x_train,y_train,cv = cv , scoring="roc_auc",n_jobs=-1).mean()
    print(f'base_score: {base_score}')
    ```
