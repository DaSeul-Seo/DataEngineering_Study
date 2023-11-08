<aside>
💡 Design Logic

</aside>

### CNN 2D

1. Load Data
2. EDA
    1. 이미지(컬러 / 흑백)
3. Dataset Class
    1. Transform
4. DataLoader
5. Modeling
    1. Train Loop
    2. Test Loop

### NLP

1. Load Data
2. EDA
    1. 긍정/부정
    2. 대소문자확인
3. Text Preprocessing
    1. Cleaning
        1. 공백제거
        2. 특수문자 제거
    2. Tokenization
        1. 글자 쪼개기(토큰화)
    3. Embedding ⇒ 글자(토큰)을 vector(숫자)화
4. Dataset Class
5. DataLoader
6. Modeling

---

### CNN 1D (Convolutional Neural Network 1D)

- 연산의 결과가 vector
    - in_channels : input 의 feature 차원수
    - out_channels: output 의 feature 차원수
    - kernel_size : 입력길이를 얼마만큼 볼것인가
    - stride: kernel 을 얼마만큼씩 이동할 것인가
    - padding: 양방향으로 얼마만큼 패딩할 것인가
    - input tensor shape
        - batch, feature dim, time step (입력길이)

### CNN Code

1. import & Module
    
    ```python
    # 애널리스트 모듈 : 분석 모듈
    import numpy as np
    import pandas as pd
    
    # 딥러닝 모듈
    import torch
    
    # nlp 딥러닝 모듈
    # 토큰(iterator 객체)으로부터 단어(vocab)를 만드는 함수
    from torchtext.vocab import build_vocab_from_iterator
    
    from tqdm.auto import tqdm
    ```
    
    ```python
    # 구글 드라이브 연결(데이터 로드를 위해서)
    try:
        from google.colab import drive
    
        drive.mount('/content/data')
        DATA_PATH = "/content/data/MyDrive/Playdata/06.Deep Learning/Datas/"
    except:
        DATA_PATH = "./data/"
    ```
    
    ```python
    df_ko = pd.read_csv(DATA_PATH+"naver_review/naver_review_train.csv", sep="\t")
    
    # 텍스트 감정분석 NLU
    df_ko = df_ko[:10000]
    print(f'{df_ko.isnull().sum().sum()} / {df_ko.shape}')
    df_ko.head()
    ```
    
2. 결측치 확인
    
    ```python
    # 1. 결측치 데이터 확인 !!
    df_ko.isnull().sum()
    
    print(f'{df_ko.isnull().sum().sum()} / {df_ko.shape}')
    ```
    
    ```python
    # 2. 결측치 데이터 조건 생성
    c = df_ko['document'].isnull()
    # 3. 결측치 아닌 조건 적용
    df_tmp = df_ko.loc[c]
    # 4. 검증
    print(f'{df_tmp.isnull().sum().sum()} / {df_tmp.shape}')
    df_tmp.head()
    
    print(df_ko.shape)
    print(df_ko.dropna().shape)   #.reset_index(drop=False)
    ```
    
    ```python
    df_ko = df_ko.dropna().reset_index(drop=True) # 결측치 제거
    
    print(f'{df_ko.isnull().sum().sum()} / {df_ko.shape}')
    df_ko.head()
    ```
    
3. Text Preprocessing
    1. Cleaning
        
        ```python
        # 무조건 공백제거 필수(문장의 앞뒤)
        df_ko['document'] = df_ko['document'].map(lambda x: x.strip()) # 문자열의 앞뒤 공백 제거
        # 특수문자(..) 제거
        df_ko['document'] = df_ko['document'].map(lambda x: x.replace("..", " "))
        ```
        
    2. Tokenization
        
        ```python
        !pip install kiwipiepy
        ```
        
        ```python
        from kiwipiepy import Kiwi
        from kiwipiepy.utils import Stopwords
        
        stopwords =  Stopwords() # 불용어
        kiwi = Kiwi() # 형태소 분석기
        
        result = kiwi.tokenize(text, stopwords=stopwords)
        result
        ```
        
    3. Stemming / Stopword
        
        ```python
        # 각 review data(txet) 토큰화해서 NJMV 뽑아온다.
        def tokenizer(text):
            tokens = kiwi.tokenize(text, stopwords=stopwords) # 토큰화
            return [ t.form for t in tokens if t.tag[0] in "NJMV" ] # N:명사, J:조사, M:관형사/부사, V: 동사
        ```
        
        ```python
        # iterator 함수 : for문에 yield 넣어주면 iterator가 된다.
        # (review data, 형태소 분석기 인스턴스)
        def yield_tokens(data, tokenizer):
            # data에 있는 text를 각각 tokenizer 함수 적용
            for text in tqdm(data):
                # text를 각각 tokenizer 적용
                yield tokenizer(text)
        ```
        
    4. Vocabulary
        
        ```python
        # iterator 인스턴스 생성
        gen = yield_tokens(df_ko["document"],tokenizer) # 토큰화
        
        # build_vocab_from_iterator
        # iterator 인스턴스를 통해 vocab를 만든다.
        # specials : 학습에 필요한 특변한 토큰을 추가한다
        # <pad> : 각 token의 길이를 통일 -> 모델 input size 동일하게 하려고
        # <unk> : 나의 데이터에 없는 단어/동사와 같은 token을 표현하기 위한 token 값
        # 알수 없는 단어(내가 가지고 있지 않은 단어)가 들어오면 unknown으로 설정
        vocab = build_vocab_from_iterator(gen, specials=["<pad>","<unk>"]) # 어휘집
        
        # 모르는 단어/토큰이 들어오면 <unk>토큰으로 재정의
        vocab.set_default_index(vocab["<unk>"])
        
        # 전체 vocab이 갖고 있는 토큰 수
        len(vocab)
        ```
        
        ```python
        # 네요, karns는 입력되어 있지 않은 단어이므로 <unk> 값이라 1 (special값)
        vocab(['짜증', '짜증', '나', '네요', '목소리', 'karns'])
        ```
        
    5. 토큰화
        
        ```python
        features = []
        
        # review data를 하나씩 가져오기
        for review_text in tqdm(df_ko["document"].tolist()):
          # 하나의 review data를 토큰화
          tokens = tokenizer(review_text)
          # 토큰화된 vocab을 통해 숫자화(index)
          token_indexes = vocab(tokens)
          # feature 리스트에 담기
          features.append(token_indexes)
          break
        ```
        
        ```python
        # matrix -> 전체 데이터를 토큰화한 후, vocab을 이용해 index 리스트로 반환
        features = [ vocab(tokenizer(text)) for text in tqdm(df_ko["document"].tolist()) ]
        
        len(features)
        features
        ```
        
    6. Padding
        
        ```python
        # 토큰 최대 길이
        max_len = max(len(lst) for lst in features)
        max_len
        ```
        
        ```python
        # padding이 적용된 데이터 담는 변수
        features = []
        
        # 기존 padding 적용되지 않은 데이터를 하나씩 뽑아서 list변수에 정의
        for lst in tqdm(features):
          # max_len(최대 토큰 길이)보다 lst의 길이가 작으면
          if len(lst) < max_len:
            # 그 차이만큼 0을 추가 : 0은 <pad>의 index값이므로
            features.append(lst + [0] * (max_len - len(lst)))
          else:
            # 최대 토큰 길이와 같다.
            features(lst)
        ```
        
        ```python
        features = [ lst + [0] * (max_len - len(lst))  if len(lst) < max_len else lst for lst in tqdm(features) ]
        
        features = np.array(features)
        # (전체 데이터 수, 각 review data 토큰 수)
        features.shape
        ```
        
4. Dataset
    
    ```python
    # target
    # 형식 통일
    target = df_ko["label"].to_numpy()
    # 차원 통일
    target = target.reshape(-1,1)
    target.shape
    ```
    
    ```python
    class ReviewDataset(torch.utils.data.Dataset):
        def __init__(self,features,target=None):
            super().__init__()
            self.features = features # features
            self.target = target # target
    
        def __len__(self):
            return self.features.shape[0]
    
        # feature, target 값 반환 -> torch형 데이터 여야 한다.
        def __getitem__(self,idx):
            item = {}
            # 중요!! 정수 = embedding할 때 무조건 longtensor 여야 한다.
            item["x"] = torch.LongTensor(self.features[idx])
            if self.target is not None:
                # 중요!! float = target은 무조건 tensor
                item["y"] = torch.Tensor(self.target[idx])
            return item
    ```
    
    ```python
    # dataset class의 인스턴스
    dt = ReviewDataset(features, target)
    ```
    
5. DataLoader
    
    ```python
    dl = torch.utils.data.DataLoader(dt, batch_size=256, shuffle=False)
    len(dl) # 전체 데이터를 / batch_size -> 데이터 로더의 작업 수
    ```
    
    ```python
    batch = next(iter(dl))
    
    len(batch['x']), len(batch['y'])
    ```
    
6. CNN 1D Model
    
    ```python
    import torch
    
    class Conv1dModel(torch.nn.Module):
        def __init__(self,vocab_size,embedding_dim=128):
            super().__init__()
            # embedding : token(index 값)을 의미있는 숫자 리스트(vector)로 표현
            # vocab_size : 전체 토큰 수
            # embedding_dim : 각 토큰을 표현할 수 있는 벡터 크기
            self.emb_layer = torch.nn.Embedding(vocab_size,embedding_dim)
            self.seq = torch.nn.Sequential(
                # CNN 1D
                torch.nn.Conv1d(in_channels=embedding_dim, out_channels=embedding_dim*2, kernel_size=3),
                torch.nn.ReLU(),
                torch.nn.MaxPool1d(2),
                torch.nn.Conv1d(in_channels=embedding_dim*2,out_channels=embedding_dim*4,kernel_size=3),
                torch.nn.ReLU(),
                torch.nn.MaxPool1d(2),
                torch.nn.AdaptiveAvgPool1d(1),
                # FC
                torch.nn.Flatten(),
                torch.nn.Linear(embedding_dim*4, 1)
            )
    
        def forward(self,x): # x (배치 크기, 문장 최대 길이)
            emb_out = self.emb_layer(x) # emb_out(배치 크기, 문장 최대 길이, 임베딩 아웃풋 크기)
            emb_out_premute = emb_out.permute(0,2,1) # emb_out_premute(배치 크기, 임베딩 아웃풋 크기, 문장 최대 길이)
            seq_out = self.seq(emb_out_premute) # seq_out(배치 크기, 1)
            return  seq_out
    ```
    
    ```python
    model = Conv1dModel(len(vocab))
    model
    ```
    
7. Engine
    
    ```python
    # train 데이터를 학습하는 프로세스
    def train_loop(dataloader,model,loss_fn,optimizer,device): # epoch 단위 학습 프로세스
        # epoch_loss를 초기화
        epoch_loss = 0
        # 모델을 학습 모듈 변경
        model.train()
        # batch만큼 학습을 진행
        for batch in dataloader:
            # 모델 학습 및 예측
            pred = model(batch["x"].to(device))
            # 모델의 학습 결과 평가 (실제 값에서 예측값의 차이)
            loss = loss_fn(pred, batch["y"].to(device))
            # 모델의 역전파(모델의 파라미터를 loss 차이만큼 조절)
            optimizer.zero_grad()
            loss.backward()
            optimizer.step()
            # batch별 학습의 loss 값을 추가
            epoch_loss += loss.item()
        # 평균 batch별 학습 loss 값 계산
        # batch별 학습 loss 평균 값 계산
        # 학습 loss 평균 값 계산
        # 학습 loss 값
        epoch_loss /= len(dataloader)
    
        return epoch_loss
    ```
    
    ```python
    from sklearn.metrics import accuracy_score
    
    @torch.inference_mode()
    def test_loop(dataloader,model,loss_fn,device):
        epoch_loss = 0
        total_acc = 0
        model.eval()
    
        pred_list = []
        sig = torch.nn.Sigmoid()
    
        for batch in dataloader:
    
            pred = model(batch["x"].to(device)) # pred: 예측값(음수 값을 갖고 있다.)
    
            pred_ = sig(pred) # pred_ 확률값(양의 값으로 변경), pytorch 객체
            pred_ = pred_.to("cpu").numpy() # pred_: numpy 객체로 변경
            pred_list.append(pred_) # 파이썬 리스트에 추가 pred_(모델의 예측값)
    
            if batch.get("y") is not None:
                loss = loss_fn(pred, batch["y"].to(device))
                epoch_loss += loss.item()
    
                pred_ = (pred_ > 0.5).astype(int) # 확률값을 정의(1, 0)
                total_acc += accuracy_score(batch["y"].to("cpu").numpy(), pred_)
    
        epoch_loss /= len(dataloader)
        total_acc /= len(dataloader)
    
        # epoch_pred = np.concatenate(pred_list)
        return epoch_loss , total_acc
    ```
    
8. Split
    
    ```python
    from sklearn.model_selection import train_test_split
    
    X_tr, X_te, y_tr, y_te = train_test_split(features, target, test_size=0.1, shuffle=True)
    len(X_tr), len(y_tr), len(X_te), len(y_te)
    ```
    
9. KFold
    
    ```python
    from sklearn.model_selection import KFold, StratifiedKFold
    
    SEED = 42
    n_splits = 5 # 몇번 split 해서 학습할지
    
    # cross validation 인스턴스 생성
    # n_splits 번 학습하겠다.
    cv = KFold(n_splits=n_splits, shuffle=True, random_state=SEED)
    ```
    
10. 학습
    
    ```python
    device = 'cuda' if torch.cuda.is_available() else 'cpu'
    device
    ```
    
    ```python
    from torch.utils.data import DataLoader
    ```
    
    ```python
    best_score_list = []
    epochs = 100
    batch_size = 256 #128
    vocab_size = len(vocab)
    
    # 이진분류 -> 감성분류일 때 많이 사용
    loss_fn = torch.nn.BCEWithLogitsLoss()
    # 모델 생성
    # split 할 때마다 모델 새로 생성
    model = Conv1dModel(vocab_size).to(device)
    # optimizer 생성
    optimizer = torch.optim.Adam(model.parameters())
    
    # cv.split(X_tr)를 이용해 tri(학습용 데이터), vai(검증용 데이터)로 나눠짐
    # tri, vai -> index array
    for i,(tri, vai) in enumerate(cv.split(X_tr)):
        # dataset 생성
        train_dt = ReviewDataset(X_tr[tri],y_tr[tri])
        valid_dt = ReviewDataset(X_tr[vai],y_tr[vai])
        # dataloader 생성
        train_dl = DataLoader(train_dt, batch_size=batch_size, shuffle=True)
        valid_dl = DataLoader(valid_dt, batch_size=batch_size, shuffle=False)
    
        # 초기값
        best_score = 0
        patience = 0
    
        for epoch in tqdm(range(epochs)):
            # 모델 학습
            train_loss = train_loop(train_dl, model, loss_fn, optimizer, device )
            # 모델 평가
            valid_loss, score = test_loop(valid_dl, model, loss_fn, device  )
    
            patience += 1
            if best_score < score:
                print(f'best score: {train_loss, valid_loss, score}') # 트레인 로스, 벨리드 로스, 스코어
                patience = 0 # 초기값
                best_score = score # 업데이트!!
                # torch.save(model.state_dict(),f"model_{i}.pth")
    
            if patience == 20:
                break
    
        print(f"Fold ({i}), BEST ACC: {best_score}")
        best_score_list.append(best_score)
    ```
    
11. Test
    
    ```python
    test_dt = ReviewDataset(X_te,y_te)
    test_dl = DataLoader(test_dt, batch_size=batch_size,shuffle=False)
    
    loss , pred = test_loop(test_dl, model, loss_fn, device  )
    
    loss, pred
    ```
