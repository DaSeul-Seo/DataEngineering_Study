- Seq2Seq(RNN) 와 공통점
    - Encoder와 Decoder로 구성
    - Decoder 이후 Dense Layer와 softmax로 y를 구한다.
- 차이점
    - RNN 계열의 모델이 아닌 Attention으로만 학습 진행
    - Positional Encoding을 통해 RNN처럼 각 토큰에 순서 정보를 매긴다
        - Embedding → Positional Encoding → 학습
    - RNN은 순차적으로 학습이 되어야 한다. ⇒ 문장의 길이가 길어질 수록 오래걸린다.
        - 첫 번째 토큰 → 두 번째 토큰 → …
    - Transformer는 병렬처리가 가능하다.
        - 유사도 기반이기 때문에
        - 모든 토큰이 동시에 학습이 가능하다.

### Transformer 전체

![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/9bca13f7-b09b-4647-912f-0430642f0428)

### Transformer 구조

1. Input, Output
    1. Input : Embedding + Positional Encoding (Matrix)
    2. Output : 
    
    ![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/cb49e0bd-dd2e-4a1c-aeb5-e8b96f4a7d80)

2. Word Embedding (Encoder, Decoder)
    1. One-Hot Encoding을 Embedding
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/54d71131-56ef-4550-b3ab-4d88a45b2c71)

3. Positional Encoding (Encoder, Decoder)
    1. RNN 제거
    2. Embedding 결과에 Positional Encoding 결과를 합친다. (더한다) ⇒ 위치 정보가 포함된 Embedding
    3. 병렬화 가능해짐
    4. sin(짝수), cos(홀수) 사용해 위치 정보 매긴다.
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/f99a4298-7133-4296-8dae-ef4fe02dd826)

4. Self-Attention (시간 단축) (Encoder, Decoder)
    1. 일반적인 Attention은 Keys가 Encoder에, Query는 Decoder에 있다.
    2. Transformer는 Encoder끼리, Decoder끼리 Attention을 한다. (Self-Attention)
    3. 자기와 자기 자신의 유사도를 계산
    
    ![5](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/99d79e92-5dfa-4fb0-b62c-f7023e3c54f5)

5. QKV
    1. Query (I)
        1. 하나의 Input(Embedding + Positional Encoding
    2. Keys (I, an, a, student)
        1. 전체 토큰
        2. Embedding + Positional Encoding
    3. Value
        1. Keys에 각각 Query와 Keys의 유사도의 값을 곱해서 더한 값
        2. I에 대한 Query와 Keys의 유사도 값
        3. Keys에 각각 I에 대한 유사도 값을 곱한다.
            1. I * 0.5 / am * 0.4 / a * 0.05 / student * 0.05
        4. 그 값들을 다 더한다. ⇒ I에 대한 학습 결과
6. Multi-Head Self Attention (Encoding)
    1. 각 Query를 병렬로 각각 처리한다.
    2. 속도가 빠르다
    
    ![6](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/2a6cbbe1-c9a8-4fad-aec5-ab3075b720f8)

7. Masking Multi-Head Self Attention (Decoding)
    1. 특정 데이터를 가린다.
    2. 왜 Decoder에만 있는가? (input이 target이라서)
        1. Encoding은 feature, Decoder에서 target이 Input Data이다.
        2. Decoder에서 예측할 데이터를 전부다 Input으로 넣어버리면 모델이 학습을 하지 않기 때문에 일부 데이터를 마스킹 처리 후 Input Data로 사용
        3. 정답을 알고 학습을 하기 때문에 Masking이 필요
    3. Seq2Seq에서는 Masking을 안하는 이유?
        1.  RNN 특성상 순차적으로 학습하기 때문에 해당 순서에는 다음에 오는 단어를 모른다. ⇒ target 데이터를 모른다.
    
    ![7](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/ccf7aa65-a299-40e3-9d3a-c6c239a0d49c)

8. Feed-Forward (선형회귀모델) (Encoder, Decoder)
    1. Fully-Connected Layer
    2. ReLU
    3. Fully-Connected Layer
    
    ![8](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/9aba988b-8197-4983-ab8c-4940737ea38a)

9. Add & Normalization (Encoder, Decoder)
    1. ResNet 기법 사용
    2. Masked Multi-Head Attention 결과값과 input 합친다. (Decoder)
    3. Multi-Head Attention 결과값과 합친다.
    4. Feed Forward 결과값과 합친다.
    
    ![9](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/eefeede5-0236-423d-85d7-424bf141461c)

10. Output Softmax
    - Encoder의 Keys, Value의 값이 Decoder에 들어간다.
    - 1, 2 Attention은 자기 자신에 대한 유사도 계산
    - 3 Attention은 Encoder의 output(Keys, Values), Decoder 의 input에 대한 유사도 계산
    
    ![10](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/ea74ed86-d5f0-4c06-ba09-7a0125a1c0d3)

### Transformer Code

![11](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/facce55a-3118-47a4-9845-c229d083bbe9)

- nn.Transformer
- Parameter
    - `d_model`: 트랜스포머의 인코더와 디코더에서의 정해진 입력과 출력의 크기를 의미(default=512)
    - `num_encoder_layers`: 트랜스포머 모델에서 인코더가 총 몇 층으로 구성되었는지를 의미(default=6)
    - `num_decoder_layers`: 트랜스포머 모델에서 디코더가 총 몇 층으로 구성되었는지를 의미(default=6)
    - `nhead`: 멀티헤드 어텐션 모델의 헤드 수, 어텐션을 사용할 때 여러 개로 분할해서 병렬로 어텐션을 수행하고 결과값을 다시 하나로 합치는 방식에서 병렬의 수 (default=8)
    - `dim_feedforward`: feedforward network model 의 차원, 피드 포워드 신경망의 은닉층의 크기(default=2048).
- 패키지 설치

```python
!pip install kiwipiepy
```

- Data 연결

```python
from google.colab import drive
drive.mount('/content/drive')
```

```python
DATA_PATH = "/content/data/MyDrive/Playdata/06.Deep Learning/Datas/"
SEED = 42

device = 'cuda' if torch.cuda.is_available() else 'cpu'
device
```

- 모듈 Import

```python
import pandas as pd
import numpy as np
import torch
from tqdm.auto import tqdm
import random
import os
```

- Seed 함수

```python
def reset_seeds(seed):
    random.seed(seed)
    os.environ['PYTHONHASHSEED'] = str(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    torch.cuda.manual_seed(seed)
    torch.backends.cudnn.deterministic = True
```

### 예제 - 분류 문제

- Data Load

```python
df_ko = pd.read_csv(DATA_PATH+"naver_review/naver_review_train.csv", sep="\t")

df_ko = df_ko[:10000]
print(f'{df_ko.isnull().sum().sum()} / {df_ko.shape}')
df_ko.head()
```

- Text Preprocessing
    - Cleaning
        
        ```python
        df_ko['document'] = df_ko['document'].map(lambda x: x.strip()) # 문자열의 앞뒤 공백 제거
        df_ko['document'] = df_ko['document'].map(lambda x: x.replace("..", " "))
        
        print(f'{df_ko.isnull().sum().sum()} / {df_ko.shape}')
        df_ko.head()
        ```
        
    - Tokenize & Voca
        
        ```python
        from kiwipiepy import Kiwi
        from kiwipiepy.utils import Stopwords
        
        stopwords =  Stopwords() # 불용어
        kiwi = Kiwi() # 형태소 분석기
        ```
        
        ```python
        def tokenizer(text):
            tokens = kiwi.tokenize(text, stopwords=stopwords) # 토큰화
            return [ t.form for t in tokens if t.tag[0] in "NJMV" ] # N:명사, J:조사, M:관형사/부사, V: 동사
        
        def yield_tokens(data, tokenizer):
            for text in tqdm(data):
                yield tokenizer(text)
        ```
        
        ```python
        from torchtext.vocab import build_vocab_from_iterator
        
        gen = yield_tokens(df_ko["document"],tokenizer) # 토큰화
        vocab = build_vocab_from_iterator(gen, specials=["<pad>","<unk>"]) # 어휘집
        vocab.set_default_index(vocab["<unk>"])
        
        len(vocab)
        ```
        
        ```python
        features = [ vocab(tokenizer(text)) for text in tqdm(df_ko["document"].tolist()) ]
        len(features)
        ```
        
    - Padding
        
        ```python
        # 최대 토큰 수
        max_len = max(len(lst) for lst in features)
        
        # 최대 토큰 수 만큼 나머지 토큰을 으로 채운다
        features = [ lst + [0] * (max_len - len(lst))  if len(lst) < max_len else lst for lst in tqdm(features) ]
        features = np.array(features)
        features.shape
        
        # feature와 shape을 맞추기 위해
        target = df_ko["label"].to_numpy().reshape(-1,1)
        target.shape
        ```
        
- Dataset

```python
from torch.utils.data import Dataset

class ReviewDataset(Dataset):
    def __init__(self,features,target=None):
        super().__init__()
        self.features = features # features
        self.target = target # target

    def __len__(self):
        return self.features.shape[0]

    def __getitem__(self,idx):
        item = {}
        item["x"] = torch.LongTensor(self.features[idx])
        if self.target is not None:
            item["y"] = torch.Tensor(self.target[idx])
        return item

dt = ReviewDataset(features,target)
len(dt)
```

- DataLoader

```python
from torch.utils.data import DataLoader

dl = DataLoader(dt,batch_size=2)
batch = next(iter(dl))
batch # batch, token
```

- Model

```python
emb = torch.nn.Embedding(len(vocab),512)
x = emb(batch["x"])
x.shape # batch, token, toekn을 설명해주는 embedding 차원
```

```python
enc_layer = torch.nn.TransformerEncoderLayer(512,8,batch_first=True)
encoder = torch.nn.TransformerEncoder(enc_layer,2)
encoder(x).shape # batch, token, toekn을 설명해주는 embedding 차원
```

```python
class Net(torch.nn.Module):
    def __init__(self,vocab_size,max_len,d_model=32,nhead=4,dim_feedforward=64,num_layers=1,device="cpu"):
        super().__init__()
        self.pos = torch.arange(max_len).to(device)
        self.pos_emb_layer = torch.nn.Embedding(max_len, d_model)

        self.emb_layer = torch.nn.Embedding(vocab_size, d_model)
        self.enc_layer = torch.nn.TransformerEncoderLayer(d_model,nhead,dim_feedforward,batch_first=True)
        self.encoder = torch.nn.TransformerEncoder(self.enc_layer,num_layers)
        self.flatten = torch.nn.Flatten()
        self.dropout = torch.nn.Dropout(0.5)
        self.output_layer = torch.nn.Linear(max_len*d_model ,1)
    def forward(self,x):
        # positional Encoding
        pos = self.pos_emb_layer(self.pos) # s , f
        # embedding
        x = self.emb_layer(x) # b, s ,f
        # positional Encoding + embedding
        x = torch.add(x,pos) # b ,s ,f

        # encoder
        x = self.encoder(x) # b, s, f
        # flatten : 선형모델에 넣어야 하니까
        x = self.flatten(x) # b , sxf (2차원 형태)
        x = self.dropout(x)
        return self.output_layer(x)
```

```python
model = Net(len(vocab), max_len)
pred = model(batch["x"])
pred.shape
```

- Engine

```python
# train
def train_loop(dataloader,model,loss_fn,optimizer,device):
    epoch_loss = 0
    model.train() # 모델 객체를 학습모드로 전환
    for batch in dataloader:
        pred = model( batch["x"].to(device) )
        loss = loss_fn( pred,batch["y"].to(device) )

        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        epoch_loss += loss.item()

    epoch_loss /= len(dataloader)

    return epoch_loss
```

```python
# test
@torch.no_grad()
def test_loop(dataloader,model,loss_fn,device):
    model.eval() # 평가 모드
    sig = torch.nn.Sigmoid()
    pred_list = []
    epoch_loss = 0
    for batch in dataloader:
        pred = model(batch["x"].to(device))

        # 검증 평가할 경우
        if batch.get("y") is not None:
            loss = loss_fn(pred,batch["y"].to(device))
            epoch_loss += loss.item()

        # 예측값 만들기
        pred = sig(pred)
        pred = pred.to("cpu").numpy()
        pred_list.append(pred)

    pred = np.concatenate(pred_list)
    epoch_loss /= len(dataloader)
    return epoch_loss, pred
```

- Training

```python
batch_size = 32
loss_fn = torch.nn.BCEWithLogitsLoss() # 손실 객체
epochs = 100
n_splits = 5 # 폴드수
model_hp = {
    "vocab_size": len(vocab),
    "max_len": max_len,
    "d_model": 32,
    "nhead" : 8,
    "dim_feedforward" : 64,
    "num_layers": 1,
    "device" : device
}
```

```python
from sklearn.metrics import accuracy_score
from sklearn.model_selection import KFold
cv = KFold(n_splits=n_splits,shuffle=True, random_state=SEED)
```

```python
is_holdout = False
reset_seeds(SEED)
best_score_list = []
for i,(tri,vai) in enumerate(cv.split(features)):

    model = Net(**model_hp).to(device)
    optimizer = torch.optim.Adam(model.parameters())

    train_dt = ReviewDataset(features[tri],target[tri])
    valid_dt = ReviewDataset(features[vai],target[vai])
    train_dl = torch.utils.data.DataLoader(train_dt, batch_size=batch_size, shuffle=True)
    valid_dl = torch.utils.data.DataLoader(valid_dt, batch_size=batch_size,shuffle=False)

    best_score = 0
    patience = 0

    for epoch in tqdm(range(epochs)):

        train_loss = train_loop(train_dl, model, loss_fn,optimizer,device )
        valid_loss , pred = test_loop(valid_dl, model, loss_fn,device  )
        pred = np.where(pred>0.5,1,0)

        score = accuracy_score(target[vai],pred)
        print(train_loss,valid_loss, score)
        patience += 1
        if best_score < score:
            patience = 0
            best_score = score
            torch.save(model.state_dict(),f"model_{i}.pth")

        if patience == 5:
            break
    print(f"Fold ({i}), BEST ACC: {best_score}")
    best_score_list.append(best_score)

    if is_holdout:
        break
```

### 예제 - 번역

- Data Load

```python
train = pd.read_csv(f"{DATA_PATH}translate_en_ko.csv")
train.shape
```

- Text Preprocessing (한국어)
    - Cleaning
        
        ```python
        train["en"] = train["en"].str.replace("[^a-zA-Z0-9 .,!?\'\"]" , "",regex=True).str.lower()
        train["ko"] = train["ko"].str.replace("[^가-힣0-9 .,!?\'\"]" , "",regex=True)
        ```
        
    - Tokenize & Voca
        
        ```python
        from kiwipiepy import Kiwi
        from kiwipiepy.utils import Stopwords
        
        stopwords =  Stopwords() # 불용어
        kiwi = Kiwi() # 형태소 분석기
        ```
        
        ```python
        def tokenizer(text):
            tokens = kiwi.tokenize(text, stopwords=stopwords) # 토큰화
            return [ t.form for t in tokens if t.tag[0] in "NJMV" ] # N:명사, J:조사, M:관형사/부사, V: 동사
        
        def yield_tokens(data, tokenizer):
            for text in tqdm(data):
                yield tokenizer(text)
        ```
        
        ```python
        from torchtext.vocab import build_vocab_from_iterator
        
        gen = yield_tokens(train["ko"],tokenizer) # 토큰화
        vocab_ko = build_vocab_from_iterator(gen, specials=["<pad>","<unk>"]) # 어휘집
        vocab_ko.set_default_index(vocab_ko["<unk>"])
        len(vocab_ko)
        ```
        
        ```python
        src_data = [ vocab_ko(tokenizer(text)) for text in train["ko"].tolist() ]
        ```
        
- Text Preprocessing (영어)
    - Tokenize & Voca
        
        ```python
        from torchtext.data.utils import get_tokenizer
        tokenizer = get_tokenizer("basic_english")
        ```
        
        ```python
        tgt_data = []
        for text in train["en"]:
            t_list = ["<sos>"] + tokenizer(text) + ["<eos>"]
            tgt_data.append(t_list)
        ```
        
        ```python
        vocab_en = build_vocab_from_iterator(tgt_data,specials=["<pad>","<unk>","<sos>","<eos>"])
        vocab_en.set_default_index(vocab_en["<unk>"])
        len(vocab_en)
        ```
        
        ```python
        tgt_data = [ vocab_en(lst) for lst in tgt_data ]
        ```
        
- Dataset

```python
from torch.utils.data import Dataset

class TranslateDataset(torch.utils.data.Dataset):
    def __init__(self,src,tgt):
        self.src = src
        self.tgt = tgt
    def __len__(self):
        return len(self.src)
    def __getitem__(self,idx):
        item = {}
        item["src"] = torch.LongTensor(self.src[idx])
        item["tgt"] = torch.LongTensor(self.tgt[idx])
        return item

dt = TranslateDataset(src_data,tgt_data)
dt[0]
```

- collate_fn

```python
def collate_fn(lst):
    src= [ item["src"] for item in lst ] # Encoder input data
    tgt= [ item["tgt"] for item in lst ] # Decoder input data(target)
    src = torch.nn.utils.rnn.pad_sequence(src,batch_first=True)
    tgt = torch.nn.utils.rnn.pad_sequence(tgt,batch_first=True)
    return {"src": src, "tgt": tgt}
```

- DataLoader

```python
from torch.utils.data import DataLoader

dl = DataLoader(dt,batch_size=2,collate_fn=collate_fn)
batch = next(iter(dl))
batch
```

- Model
    - forward 주요 파라미터
        - src : 인코더의 입력 텐서
        - tgt : 디코더의 입력 텐서
        - tgt_mask : 예측시점의 어텐션 방지를 위한 tgt 시퀀스에 대한 마스크

```python
transformer = torch.nn.Transformer(batch_first=True)
transformer
```

```python
d_model = 512
src_emb = torch.nn.Embedding(len(vocab_ko), d_model ) # Encoder input data Embedding
tgt_emb = torch.nn.Embedding(len(vocab_en), d_model ) # Decoder input data Embedding(target)

src = src_emb(batch["src"])
tgt = tgt_emb(batch["tgt"])
src.shape , tgt.shape
```

```python
# Decoder Input data Masking
tgt_mask = torch.nn.Transformer.generate_square_subsequent_mask(tgt.shape[1])
tgt_mask
```

```python
# (Encoder input data, Decoder masking input data)
output = transformer(src,tgt , tgt_mask = tgt_mask)
output.shape
```

- Positional Encoding

```python
class PositionalEncoding(torch.nn.Module):
    def __init__(self,max_len,d_model):
        super().__init__()
        pos_encoding = torch.zeros(max_len,d_model) # seq, feature
        pos = torch.arange(0,max_len,dtype=torch.float32).view(-1,1)
        _2i= torch.arange(0,d_model,step=2,dtype=torch.float32)

        pos_encoding[:,0::2] = torch.sin(pos / 10000 ** (_2i/d_model)) # 짝수
        pos_encoding[:,1::2] = torch.cos(pos / 10000 ** (_2i/d_model)) # 홀수
        # pos_encoding -> (max_len, d_model)
        pos_encoding = pos_encoding.unsqueeze(0) # (batch, max_len, d_model)

        self.register_buffer("pos_encoding",pos_encoding)
    def forward(self,x):
        return x + self.pos_encoding[:,:x.shape[1]]

pe = PositionalEncoding(1000,512)
pe(src).shape
```

- Seq2Seq

```python
class Net(torch.nn.Module):
    def __init__(self,
                 src_vocab_size,
                 tgt_vocab_size,
                 max_len=1000,
                 d_model = 512,
                 nhead=8,
                 num_encoder_layers=6,
                 num_decoder_layers=6,
                 dim_feedforward=2048):

        super().__init__()
        # Encoder Embedding (input data)
        self.src_emb = torch.nn.Embedding(src_vocab_size,d_model)
        # Decoder Embedding (target input date)
        self.tgt_emb = torch.nn.Embedding(tgt_vocab_size,d_model)

        # Positional Encoding
        self.pe = PositionalEncoding(max_len,d_model)

        # Transformer
        self.transformer = torch.nn.Transformer(d_model=d_model,
                                                nhead=nhead,
                                                num_encoder_layers=num_encoder_layers,
                                                num_decoder_layers=num_decoder_layers,
                                                dim_feedforward=dim_feedforward,
                                                batch_first=True)
        # 선형
        self.fc_layer = torch.nn.Linear(d_model,tgt_vocab_size)

    def forward(self,src,tgt,tgt_mask):
        src = self.pe( self.src_emb(src) ) # Encoder
        tgt = self.pe( self.tgt_emb(tgt) ) # Decoder
        x = self.transformer(src,tgt,tgt_mask=tgt_mask) # batch,seq,d_model
        return self.fc_layer(x) # batch,seq,단어별 예측값

    # 실제 예측시 사용할 메서드
    def ecoder(self,src):
        src = self.pe( self.src_emb(src) )
        return self.transformer.encoder(src)

    def decoder(self,tgt,memory):
        tgt = self.pe( self.tgt_emb(tgt) )
        x = self.transformer.decoder(tgt,memory=memory)
        return self.fc_layer(x)
```

```python
tgt_mask = torch.nn.Transformer.generate_square_subsequent_mask(batch["tgt"].shape[1])
tgt_mask
```

```python
model = Net(len(vocab_ko), len(vocab_en))
pred = model(batch["src"],batch["tgt"],tgt_mask)
pred.shape
```

```python
src = batch["src"][:1]
src # bathc, seq

tgt = batch["tgt"][:1,:1]
tgt # batch , seq
```

```python
model.eval()
memory = model.ecoder(src)
pred = model.decoder(tgt,memory)
pred.shape
```

```python
pred[-1,-1].argmax()
vocab_en.lookup_token(1974)
```

- Engine

```python
# EarlyStopper
class EarlyStopper(object):

    def __init__(self, num_trials, save_path):
        self.num_trials = num_trials
        self.trial_counter = 0
        self.best_loss = np.inf
        self.save_path = save_path

    def is_continuable(self, model, loss):
        if loss < self.best_loss:
            self.best_loss = loss
            self.trial_counter = 0 # 초기화
            torch.save(model, self.save_path)
            return True
        elif self.trial_counter + 1 < self.num_trials:
            self.trial_counter += 1 # 기존 시도횟수 + 1
            return True
        else:
            return False
```

```python
# train
def train_loop(dataloader,model,loss_fn,optimizer,device):
    epoch_loss = 0
    model.train()

    for batch in dataloader:
        src = batch["src"].to(device)
        tgt = batch["tgt"].to(device)
        tgt_mask = torch.nn.Transformer.generate_square_subsequent_mask(tgt.shape[1]).to(device)
        pred = model(src,tgt,tgt_mask) # batch, seq, 단어별 예측실수값
        nclass = pred.shape[2]
        pred = pred[:,:-1].reshape(-1,nclass) # 시점별 예측 실수값들이 됨
        tgt = tgt[:,1:].reshape(-1) # 다중분류 문제에서는 정답데이터는 1차원 벡터형태여야함.

        mask = tgt > 2
        tgt = tgt[mask]
        pred = pred[mask]
        loss = loss_fn(pred,tgt)

        optimizer.zero_grad()
        loss.backward()
        optimizer.step()

        epoch_loss += loss.item()

    epoch_loss /= len(dataloader)
    return epoch_loss
```

- Training

```python
src_vocab_size, tgt_vocab_size = len(vocab_ko) , len(vocab_en)
batch_size = 64
loss_fn = torch.nn.CrossEntropyLoss()
epochs = 500
```

```python
reset_seeds(SEED)

model = Net(src_vocab_size, tgt_vocab_size).to(device)
optimizer = torch.optim.Adam(model.parameters())
early_stopper = EarlyStopper(num_trials=10, save_path=f'best_model.pt')

train_dt = TranslateDataset(src_data,tgt_data)
train_dl = torch.utils.data.DataLoader(train_dt,batch_size=batch_size,shuffle=True,collate_fn=collate_fn)

train_loss_list = []
for i in tqdm(range(epochs)):
    train_loss = train_loop(train_dl,model,loss_fn,optimizer,device)
    # print(train_loss)
    train_loss_list.append(train_loss)
    if not early_stopper.is_continuable(model, train_loss):
        print(f'epoch:{i} >> best loss: {early_stopper.best_loss}')
        break
```

```python
import seaborn as sns
import matplotlib.pyplot as plt

sns.lineplot(train_loss_list)
```

<aside>
💡 Reference

</aside>

- Transformer
    - https://gaussian37.github.io/dl-concept-transformer/
- 구조
    - [https://glanceyes.com/entry/Transformer의-Multi-Head-Attention과-Transformer에서-쓰인-다양한-기법#toc-link-1](https://glanceyes.com/entry/Transformer%EC%9D%98-Multi-Head-Attention%EA%B3%BC-Transformer%EC%97%90%EC%84%9C-%EC%93%B0%EC%9D%B8-%EB%8B%A4%EC%96%91%ED%95%9C-%EA%B8%B0%EB%B2%95#toc-link-1)
