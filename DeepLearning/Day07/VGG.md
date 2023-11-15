### VGG

- CNN의 커다란 모델 (CNN을 쌓은 모델)
    - CNN = Convolution + MaxPooling + Flatten
- VGG16
    - 16개의 Layer(CNN) 쌓은 것
    - Block 5개
    - MaxPooling을 제외한 갯수 세기
    - TinyVGG : 작은 버전의 VGG
    - 최대 19개
    
    ![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/aba2e6a1-4ea1-4b6d-8230-1f54697c6707)

### Loss 그래프를 그리는 이유

- under fitting, over fitting 확인하기 위해

![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/af037eae-f57b-47c0-9dc8-bf8a28d46c83)

- over fitting 해결법
    - 데이터를 더 모으기
    - 모델 단순화
    - argumenation 사용 = 이미지 전처리
    - 전이학습 (Tranfer Learning)
    - 중단(변곡점 - loss가 가장 낮은 곳) : early stopped
        - trials : 시도를 계속해서 가장 낮은 loss를 찾는다.

### VGG Code

- Model

```python
class TinyVGGV1(nn.Module):
    """
    Model architecture copying TinyVGG from:
    https://poloclub.github.io/cnn-explainer/
    """
    def __init__(self, input_shape: int, hidden_units: int, output_shape: int) -> None:
        super().__init__()
        self.conv_block_1 = nn.Sequential(
            nn.Conv2d(in_channels=input_shape,
                      out_channels=hidden_units,
                      kernel_size=3, # how big is the square that's going over the image?
                      stride=1, # default
                      padding=1), # options = "valid" (no padding) or "same" (output has same shape as input) or int for specific number
            nn.ReLU(),
            nn.Conv2d(in_channels=hidden_units,
                      out_channels=hidden_units,
                      kernel_size=3,
                      stride=1,
                      padding=1),
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=2,
                         stride=2) # default stride value is same as kernel_size
        )
        self.conv_block_2 = nn.Sequential(
            nn.Conv2d(hidden_units, hidden_units, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.Conv2d(hidden_units, hidden_units, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.MaxPool2d(2)
        )
        self.classifier = nn.Sequential(
            nn.Flatten(),
            # Where did this in_features shape come from?
            # It's because each layer of our network compresses and changes the shape of our inputs data.
            nn.Linear(in_features=hidden_units*16*16,
                      out_features=output_shape)
        )

    def forward(self, x: torch.Tensor):
        x = self.conv_block_1(x)
        # print(x.shape)
        x = self.conv_block_2(x)
        # print(x.shape)
        x = self.classifier(x)
        # print(x.shape)
        return x
        # return self.classifier(self.conv_block_2(self.conv_block_1(x))) # <- leverage the benefits of operator fusion
```

```python
reset_seeds()
vgg_model1 = TinyVGGV1(input_shape=3, # (3, 64, 64)
                       hidden_units=10,
                       # 결과 예측값이 어떤 것으로 분류가 되었는지 알아야 하니까 train 데이터의 class의 갯수가 된다.
                       output_shape=len(train_dataset.classes)).to(device)
vgg_model1
```

- 학습하기 전 모델 예측

```python
# 1. Get a batch of images and labels from the DataLoader
# img_batch 자체로는 (batch, color, height, width)
img_batch, label_batch = next(iter(train_dataloader))
print(f"img_batch shape: {img_batch[0].shape}\n")
print(f"label_batch shape: {label_batch}\n")

# 2. Get a single image from the batch and unsqueeze the image so its shape fits the model
# img_batch 자체로는 (batch, color, height, width)이다.
# img_batch[0]는 (color, height, width)이기에 테스트를 할 시 차원이 동일하지 않아 배치 채널이 필요해서 차원은 추가하였다.(unsqueeze)
# 0차원에 추가 (dim = 0)
img_single, label_single = img_batch[0].unsqueeze(dim=0), label_batch[0]
print(f"Single image shape: {img_single.shape}\n")
# print(f"label_single shape: {label_single}\n")

# 3. Perform a forward pass on a single image
vgg_model1.eval()
with torch.inference_mode():
    pred = vgg_model1(img_single.to(device))

# 4. Print out what's happening and convert model logits -> pred probs -> pred label
# 이미지에 대한 예측값 = 각 클래스별 예측값
print(f"Output logits:\n{pred}\n")
print(f"Output pred shape:\n{pred.shape}\n")
# dim = 0 : 배치값, dim = 1 : 예측값
# softmax : dim = 1 차원의 값을 확률값으로 변경해준다. => 예측값 자체의 기준값을 모르기 때문에
print(f"Output prediction probabilities:\n{torch.softmax(pred, dim=1)}\n")
# argmx : 확률값(dim = 1) 중에 가장 높은 것. => 각 클래스 중 가장 높이 예측한 값
print(f"Output prediction label:\n{torch.argmax(torch.softmax(pred, dim=1), dim=1)}\n")
print(f"Actual label:\n{label_single}")
```

- Train Loop

```python
def train_step(model: torch.nn.Module,
               dataloader: torch.utils.data.DataLoader,
               loss_fn: torch.nn.Module,
               optimizer: torch.optim.Optimizer):
    # Put model in train mode
    # 학습 모드
    model.train()

    # Setup train loss and train accuracy values
    # loss와 acc 저장 변수 선언 및 초기화
    train_loss, train_acc = 0, 0

    # Loop through data loader data batches
    for batch, (X, y) in enumerate(dataloader):
        # Send data to target device
        # 학습 데이터와 모델이 같은 환경에 있어야 한다.
        X, y = X.to(device), y.to(device)

        # 1. Forward pass
        # 모델 학습
        y_pred = model(X)

        # 2. Calculate  and accumulate loss
        loss = loss_fn(y_pred, y)
        train_loss += loss.item()

        # 3. Optimizer zero grad
        optimizer.zero_grad()

        # 4. Loss backward
        loss.backward()

        # 5. Optimizer step
        optimizer.step()

        # Calculate and accumulate accuracy metric across all batches
        y_pred_class = torch.argmax(torch.softmax(y_pred, dim=1), dim=1)
        train_acc += (y_pred_class == y).sum().item()/len(y_pred)

    # Adjust metrics to get average loss and accuracy per batch
    train_loss = train_loss / len(dataloader)
    train_acc = train_acc / len(dataloader)
    return train_loss, train_acc
```

- Test Loop

```python
def test_step(model: torch.nn.Module,
              dataloader: torch.utils.data.DataLoader,
              loss_fn: torch.nn.Module):
    # Put model in eval mode
    model.eval()

    # Setup test loss and test accuracy values
    test_loss, test_acc = 0, 0

    # Turn on inference context manager
    with torch.inference_mode():
        # Loop through DataLoader batches
        for batch, (X, y) in enumerate(dataloader):
            # Send data to target device
            X, y = X.to(device), y.to(device) # X (), y ()

            # 1. Forward pass
            test_pred_logits = model(X) # pred ()

            # 2. Calculate and accumulate loss
            loss = loss_fn(test_pred_logits, y)
            test_loss += loss.item()

            # Calculate and accumulate accuracy
            test_pred_labels = test_pred_logits.argmax(dim=1) # pred_labes ()
            test_acc += ((test_pred_labels == y).sum().item()/len(test_pred_labels))

    # Adjust metrics to get average loss and accuracy per batch
    test_loss = test_loss / len(dataloader)
    test_acc = test_acc / len(dataloader)
    return test_loss, test_acc
```

- Train, Test 합치기

```python
from tqdm.auto import tqdm

# 1. Take in various parameters required for training and test steps
def train(model: torch.nn.Module,
          train_dataloader: torch.utils.data.DataLoader,
          test_dataloader: torch.utils.data.DataLoader,
          optimizer: torch.optim.Optimizer,
          loss_fn: torch.nn.Module = nn.CrossEntropyLoss(),
          epochs: int = 5):

    # 2. Create empty results dictionary
    results = {"train_loss": [],
        "train_acc": [],
        "test_loss": [],
        "test_acc": []
    }

    # 3. Loop through training and testing steps for a number of epochs
    for epoch in tqdm(range(epochs)):
        train_loss, train_acc = train_step(model=model,
                                           dataloader=train_dataloader,
                                           loss_fn=loss_fn,
                                           optimizer=optimizer)
        test_loss, test_acc = test_step(model=model,
                                        dataloader=test_dataloader,
                                        loss_fn=loss_fn)

        # 4. Print out what's happening
        print(
            f"Epoch: {epoch+1} | "
            f"train_loss: {train_loss:.4f} | "
            f"train_acc: {train_acc:.4f} | "
            f"test_loss: {test_loss:.4f} | "
            f"test_acc: {test_acc:.4f}"
        )

        # 5. Update results dictionary
        results["train_loss"].append(train_loss)
        results["train_acc"].append(train_acc)
        results["test_loss"].append(test_loss)
        results["test_acc"].append(test_acc)

    # 6. Return the filled results at the end of the epochs
    return results
```

- Early Stopper

```python
# over fitting을 줄이기 위해 Early stop
class EarlyStopper(object):
    # 생성함수 - 다른 함수의 필요한 변수를 생성
    def __init__(self, num_trials, save_path):
        # 최대 몇 번을 참을 건지
        self.num_trials = num_trials
        # 지금까지 몇 번을 참은 건지
        self.trial_counter = 0
        # 가장 최적화된 loss값 (가장 작은 값)
        # 가장 작은 값을 찾아야 하기 때문에 가장 큰 수로 초기화
        self.best_loss = np.inf
        # 최적의 loss 값을 가진 모델(weight) 저장 경로
        self.save_path = save_path

    # 각 epoch별 학습을 지속할지 멈출지 판단하는 함수
    # epoch 학습이 끝날 때마다 함수 실행
    def is_continuable(self, model, loss): # epoch 시점의 loss
        # 기존 best loss와 현재 loss 비교
        if loss < self.best_loss: # 현재 loss가 최고 loss보다 더 낮은 경우
            self.best_loss = loss # 최고 loss를 현재 loss로 업데이트
            self.trial_counter = 0 # 초기화
            torch.save(model, self.save_path) # 최고 loss를 갖은 모델 저장
            return True # 더 학습
        # 현재 loss가 최적의 loss보다 작은 경우 & 최대 시도횟수보다 현재 시도횟수가 작은 경우
        # self.trial_counter : 현재 시도 횟수
        # self.num_trials : 최대 시도 횟수
        elif self.trial_counter + 1 < self.num_trials:
            self.trial_counter += 1 # 기존 시도횟수 + 1
            return True
        # 현재 loss가 최적의 loss보다 큰 경우
        # & 현재 시도횟수가 최대 시도횟수보다 큰 경우
        # over fitting 발생
        else:
            return False

    def get_best_model(self, device):
        return torch.load(self.save_path).to(device)
```

- Train 평가

```python
# Set random seeds
reset_seeds()

# Recreate an instance of TinyVGG
vgg_model1 = TinyVGGV1(input_shape=3, # number of color channels (3 for RGB)
                       hidden_units=10,
                       output_shape=len(train_dataset.classes)).to(device)

# Setup loss function and optimizer
loss_fn = nn.CrossEntropyLoss()
optimizer = torch.optim.Adam(params=vgg_model1.parameters(), lr=0.001)

# early_stopper
early_stopper = EarlyStopper(num_trials=args.NUM_TRIALS, save_path=args.vgg_best_model)

# Start the timer
from timeit import default_timer as timer
start_time = timer()

# Train
vgg_result1 = train(model=vgg_model1,
                        train_dataloader=train_dataloader,
                        test_dataloader=test_dataloader,
                        optimizer=optimizer,
                        loss_fn=loss_fn,
                        epochs=args.NUM_EPOCHS)

# End the timer and print out how long it took
end_time = timer()
print(f"Total training time: {end_time-start_time:.3f} seconds")
```

- Model loss 그래프 그리기

```python
def plot_loss_curves(results):
    """Plots training curves of a results dictionary.

    Args:
        results (dict): dictionary containing list of values, e.g.
            {"train_loss": [...],
             "train_acc": [...],
             "test_loss": [...],
             "test_acc": [...]}
    """

    # Get the loss values of the results dictionary (training and test)
    loss = results['train_loss']
    test_loss = results['test_loss']

    # Get the accuracy values of the results dictionary (training and test)
    accuracy = results['train_acc']
    test_accuracy = results['test_acc']

    # Figure out how many epochs there were
    epochs = range(len(results['train_loss']))

    # Setup a plot
    plt.figure(figsize=(15, 7))

    # Plot loss
    plt.subplot(1, 2, 1)
    plt.plot(epochs, loss, label='train_loss')
    plt.plot(epochs, test_loss, label='test_loss')
    plt.title('Loss')
    plt.xlabel('Epochs')
    plt.legend()

    # Plot accuracy
    plt.subplot(1, 2, 2)
    plt.plot(epochs, accuracy, label='train_accuracy')
    plt.plot(epochs, test_accuracy, label='test_accuracy')
    plt.title('Accuracy')
    plt.xlabel('Epochs')
    plt.legend();

plot_loss_curves(vgg_result1)
```

- Confusion Matrix

```python
from tqdm.auto import tqdm

y_preds = []
vgg_model1.eval()

with torch.inference_mode():
  # test 데이터 하나씩 가져오기 (feature, target)
  for X,y in tqdm(test_dataloader, desc = "Making predictions"):
    # 모델과 동일한 환경 세팅
    X, y = X.to(device), y.to(device)
    # 예측
    y_logit = vgg_model1(X)
    # 예측값을 확률값으로 나타낸 후 최대값의 라벨 반환
    y_pred = torch.softmax(y_logit, dim = 1).argmax(dim = 1)
    print(y_pred)
    # 라벨 리스트에 값 저장
    y_preds.append(y_pred.cpu())
  y_pred_tensor = torch.cat(y_preds)
```

```python
# mlxtend = 전처리, 모델 선택, 평가 및 시각화에 사용
try:
  import torchmetrics, mlxtend
  print(f"mlxtend version: {mlxtend.__version__}")
  assert int(mlxtend.__version__.split(".")[1]) >= 19
except:
  !pip install -q torchmetrics -U mlxtend
  import torchmetrics, mlxtend
  print(f"mlxtend version: {mlxtend.__version__}")
```

```python
from torchmetrics import ConfusionMatrix
from mlxtend.plotting import plot_confusion_matrix

confmat = ConfusionMatrix(num_classes = len(train_dataset.classes), task = "multiclass")
confmat_tensor = confmat(preds = y_pred_tensor,
                         target = torch.tensor(test_dataset.targets))

fig, ax = plot_confusion_matrix(conf_mat = confmat_tensor.numpy(),
                                class_names = train_dataset.classes,
                                figsize = (10, 7))
```
