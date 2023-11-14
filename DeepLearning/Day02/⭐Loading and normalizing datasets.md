### Dataset and DataLoader (데이터 형 변환)

- 머신러닝은 모델을 학습을 할 때 특정 데이터 타입을 파라미터로 해서 fit 했다.
    - Sklearn에서 **Numpy나 Pandas**로 만들어서 데이터 분석을 했다.
- 딥러닝도 모델을 학습을 할 때 딥러닝에서 허락한 특정 데이터 타입을 파라미터로 해서 학습한다.
    - 기본적으로 **pytorch의 tensor** 이다. (딥러닝 모델은 pytorch의 인스턴스니까)
    - 이미지, 영상, 텍스트 등을 tensor로 형 변환을 해야 한다.

```python
import numpy as np

import torch # pytorch
# utils(유용한) / pytorch 개발할때 유용하게 사용한 모듈들
from torch.utils.data import Dataset
# pytorch vision(이미지, 영상 등)에 있는 dataset
from torchvision import datasets
# pytorch에 vision 변환할 때 쓰는 모듈
from torchvision.transforms import ToTensor, Lambda

import matplotlib.pyplot as plt
%matplotlib inline
```

### tensor로 데이터 형 변환

1. torch에서 제공하는 Dataset 사용
- FashionMNIST (https://github.com/mpalaourg/FashionMNIST-PyTorch)
    
    ![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/cc9c9f4b-ef2f-41bd-83fb-ea1e248044bd)
    
    ```python
    training_data = datasets.FashionMNIST( # torchvision에서 제공
        root="data", # 데이터 저장될 위치
        train=True, # train 데이터 인지 test 데이터 인지 정의
        download=True, # 저장될 위치에 파일이 있으면 다운로드 해서 엎어칠지, 생략할지
        # transform=ToTensor() # tensor로 변환
    )
    
    test_data = datasets.FashionMNIST(
        root="data",
        train=False,
        download=True,
        transform=ToTensor()
    )
    ```
    
    - 데이터셋 데이터 크기
        
        ```python
        # pytorch dataset : 기본적으로 인스턴스 => 클래스를 생성해서, 메소드 존재
        type(training_data) # torchvision.datasets.mnist.FashionMNIST
        # Magick Method : __len__
        len(training_data), len(test_data) # (60000, 10000)
        ```
        
    - 데이터 확인
        
        ```python
        # Magic Method : __getitem__
        features, target = training_data[0]
        
        # ((28, 28), 9)
        np.array(features).shape, target
        ```
        
- Iterating and Visualizing the Dataset (Dataset 시각화)
    
    ```python
    # target 숫자를 문자로 표현하는 dicitonary
    # 숫자 to 텍스트
    labels_map = {
        0: "T-Shirt",
        1: "Trouser",
        2: "Pullover",
        3: "Dress",
        4: "Coat",
        5: "Sandal",
        6: "Shirt",
        7: "Sneaker",
        8: "Bag",
        9: "Ankle Boot",
    }
    # 이미지 그려지는 영역 생성(figure) 및 크기(figsize) 지정
    figure = plt.figure(figsize=(8, 8))
    # 컬럼 3, 로우 3 형태로 이미지 그리기
    cols, rows = 3, 3
    
    # reange(start, end) : start 포함, end 미포함
    # rangee(1, 10) : 1 ~ 9
    for i in range(1, cols * rows + 1):
        # torch.randint() : 랜덤 정수 추출
        # torch.randint(범위크기, size=몇 개 뽑을지)
        # torch.randint(100, size=(2,))) => 100 미만 중 랜덤으로 2개 뽑기
        # len(training_data) : training_data의 길이만큼 추출, size=(1,) : 1개 뽑는다.
        # => training_data에서 랜덤으로 이미지 1개에 대한 인덱스 값 추출
        # item() : torch.randint의 결과는 torch이기에 python으로 형 변환
        # sample_idx : 인덱스 숫자
        sample_idx = torch.randint(len(training_data), size=(1,)).item()
        # training_data에서 인덱스에 해당하는 feature, target이 리턴
        # feature : img(28x28) / target : 숫자(label)
        img, label = training_data[sample_idx]
        # fiqure(그림그리는 영역)에 그림(aubplot)을 추가(add)한다.
        # 위치 지정 : rows, cols, i
        figure.add_subplot(rows, cols, i)
        # plt.title() : 정의된 위치에 대한 그림 제목
        # labels_map[label] : dictionary에서 label에 해당하는 텍스트 가지고 온다.
        plt.title(labels_map[label])
        # axis : 축
        # 축에 있는 숫자를 표시하지 않는다.
        plt.axis("off")
        # img를 gray로 해서 이미지를 도화지에 그려줘
        plt.imshow(img, cmap="gray")
    
    # 해당 도화지를 보여줘
    plt.show()
    ```
    
- Preparing your data for training with DataLoaders (train 데이터 준비)
    - DataLoader
        - Dataset을 batch_size 단위로 나누는 클래스
    
    ```python
    # DataLoader : Dataset을 batch_size 단위로 나누는 클래스
    from torch.utils.data import DataLoader
    
    # test_dataloader : Dataset의 인스턴스
    # test_data(Dataset)을 섞어서(shuffle) 64 크기(batch_size)로 나누는 클래스
    test_dataloader = DataLoader(test_data, batch_size=64, shuffle=True)
    ```
    
- Iterate through the DataLoader (반복)
    
    ```python
    # Display image and label.
    test_features, test_labels = next(iter(test_dataloader))
    print(f"Feature batch shape: {test_features.size()}")
    print(f"Labels batch shape: {test_labels.size()}")
    '''
    Feature batch shape: torch.Size([64, 1, 28, 28])
    Labels batch shape: torch.Size([64])
    '''
    
    test_features[0].shape # torch.Size([1, 28, 28])
    
    img = test_features[0].squeeze() # (28, 28)
    label = test_labels[0]
    plt.imshow(img, cmap="gray")
    plt.show()
    print(f"Label: {label}")
    ```
    
2. ImageFolder를 이용한 Datadet 생성
- https://www.learnpytorch.io/04_pytorch_custom_datasets/
- Get Data
    
    ```python
    import requests # 인터넷 통신용 모듈 - 파일 다운로드/업로드, 웹페이지 이동
    import zipfile # 압축파일 모듈 - 압축, 압축해제
    from pathlib import Path # 파일 패스 모듈 - 파일 위치 이동, 폴더/파일 확인
    
    # Setup path to data folder
    data_path = Path("data/")
    # data/pizza_steak_sushi
    image_path = data_path / "pizza_steak_sushi"
    
    # If the image folder doesn't exist, download it and prepare it...
    if image_path.is_dir():
        print(f"{image_path} directory exists.")
    else:
        print(f"Did not find {image_path} directory, creating one...")
        image_path.mkdir(parents=True, exist_ok=True)
    
        # Download pizza, steak, sushi data
        with open(data_path / "pizza_steak_sushi.zip", "wb") as f:
            request = requests.get("https://github.com/mrdbourke/pytorch-deep-learning/raw/main/data/pizza_steak_sushi.zip")
            print("Downloading pizza, steak, sushi data...")
            f.write(request.content)
    
        # Unzip pizza, steak, sushi data
        with zipfile.ZipFile(data_path / "pizza_steak_sushi.zip", "r") as zip_ref:
            print("Unzipping pizza, steak, sushi data...")
            zip_ref.extractall(image_path)
    ```
    
    ```python
    import os # os(윈도우, 리눅스 등)을 사용할 때 사용하는 모듈
    
    def walk_through_dir(dir_path):
      """
      Walks through dir_path returning its contents.
      Args:
        dir_path (str or pathlib.Path): target directory
    
      Returns:
        A print out of:
          number of subdiretories in dir_path
          number of images (files) in each subdirectory
          name of each subdirectory
      """
      for dirpath, dirnames, filenames in os.walk(dir_path):
        print(f"There are {len(dirnames)} directories and {len(filenames)} images in '{dirpath}'.")
    ```
    
    ```python
    # Setup train and testing paths
    train_dir = image_path / "train"
    test_dir = image_path / "test"
    '''
    (PosixPath('data/pizza_steak_sushi/train'),
     PosixPath('data/pizza_steak_sushi/test'))
    '''
    ```
    
- Visualize an image (시각화) - matplotlib
    
    ```python
    import numpy as np
    import matplotlib.pyplot as plt
    
    # Turn the image into an array
    img_as_array = np.asarray(img)
    
    # Plot the image with matplotlib
    plt.figure(figsize=(10, 7))
    plt.imshow(img_as_array)
    plt.title(f"Image class: {image_class} | Image shape: {img_as_array.shape} -> [height, width, color_channels]")
    plt.axis(False);
    ```
    
- ImageFolder
    
    ```python
    # Use ImageFolder to create dataset(s)
    from torchvision import datasets
    
    train_data = datasets.ImageFolder(root=train_dir, # target folder of images
                                      transform=ToTensor(), # Features 정규화 -> tensor 형 변환
                                      target_transform=None) # transforms to perform on labels (if necessary)
    
    test_data = datasets.ImageFolder(root=test_dir,
                                     transform=ToTensor()) # Features 정규화 -> tensor 형 변환
    
    print(f"Train data:\n{train_data}\nTest data:\n{test_data}")
    ```
    
    ```python
    # 알아서 class 화 : Get class names as a list
    class_names = train_data.classes
    
    # Check the lengths
    len(train_data), len(test_data)
    ```
    
    - 이미지 확인
    
    ```python
    # img, label = train_data[0][0], train_data[0][1]
    img, label = train_data[0] # img: 컬러, 높이, 너비
    
    # Rearrange the order of dimensions
    img_permute = img.permute(1, 2, 0) # 높이, 너비, 컬러
    
    # Print out different shapes (before and after permute)
    print(f"Original shape: {img.shape} -> [color_channels, height, width]")
    print(f"Image permute shape: {img_permute.shape} -> [height, width, color_channels]")
    
    # Plot the image
    plt.figure(figsize=(10, 7))
    plt.imshow(img.permute(1, 2, 0))
    plt.axis("off")
    plt.title(class_names[label], fontsize=14);
    ```
    
- DataLoader
    
    ```python
    # Turn train and test Datasets into DataLoaders
    from torch.utils.data import DataLoader
    
    train_dataloader = DataLoader(dataset=train_data,
                                  batch_size=1, # batch_size 정의
                                  shuffle=True) # shuffle 할 것인지
    
    test_dataloader = DataLoader(dataset=test_data,
                                 batch_size=1,
                                 shuffle=False) # 보통 test_data는 shuffle하지 않는다.
    
    len(train_dataloader), len(test_dataloader)
    ```
    
    ```python
    img, label = next(iter(train_dataloader))
    
    # Batch size will now be 1, try changing the batch_size parameter above and see what happens
    print(f"Image shape: {img.shape} -> [batch_size, color_channels, height, width]")
    print(f"Label shape: {label.shape}")
    '''
    Image shape: torch.Size([1, 3, 366, 512]) -> [batch_size, color_channels, height, width]
    Label shape: torch.Size([1])
    '''
    ```
    
3. ⭐ Dataset 클래스 생성
- __init__() : 생성함수
- __len__() : 전체 데이터 수
- __getitem__() : 해당 index의 feature, target 리턴
- Creating a helper function to get class names (class명 가져오는 함수 만들기)
    
    ```python
    # Setup path for target directory
    target_directory = train_dir
    print(f"Target directory: {target_directory}")
    
    # Get the class names from the target directory
    # scandir : 파일명 가지고 오기
    class_names_found = sorted([entry.name for entry in list(os.scandir(image_path / "train"))])
    print(f"Class names found: {class_names_found}")
    ```
    
    ```python
    from typing import Tuple, Dict, List
    
    # Make function to find classes in target directory
    # target 폴더에서 클래스를 찾는 함수
    # Tuple[List[str], Dict[str, int]] : 힌트
    def find_classes(directory: str) -> Tuple[List[str], Dict[str, int]]:
        # 1. Get the class names by scanning the target directory
        # target 폴더에서 폴더명(class name)을 가져온다.
        classes = sorted(entry.name for entry in os.scandir(directory) if entry.is_dir())
    
        # 2. Raise an error if class names not found
        # 폴더명(class name)을 찾지 못한 경우
        if not classes:
            raise FileNotFoundError(f"Couldn't find any classes in {directory}.")
    
        # 3. Crearte a dictionary of index labels (computers prefer numerical rather than string labels)
        # class name을 index로 변환
        class_to_idx = {cls_name: i for i, cls_name in enumerate(classes)}
        return classes, class_to_idx
    ```
    
- Creating a Dataset (Dataset 생성)
    
    ```python
    import pathlib
    
    # Write a custom dataset class (inherits from torch.utils.data.Dataset)
    # Dataset클래스를 상속받는다.
    from torch.utils.data import Dataset
    
    # 1. Subclass torch.utils.data.Dataset
    # torch에 Dataset 클래스를 상속받아서 Dataset 생성
    class ImageFolderCustom(Dataset):
    
        # 2. Initialize with a targ_dir and transform (optional) parameter
        # 생성함수 정의 (데이터 디렉토리:str, 형 변환 필요 유무) -> 반환값 없음 (None)
        def __init__(self, targ_dir: str, transform=None) -> None:
    
            # 3. Create class attributes
            # Get all image paths
            # class 변수를 만들자. -> target 폴더 내 모든 jpg 파일을 리스트에 담는다.
            self.paths = list(pathlib.Path(targ_dir).glob("*/*.jpg")) # note: you'd have to update this if you've got .png's or .jpeg's
            # Setup transforms
            self.transform = transform
            # Create classes and class_to_idx attributes
            # find_classes : 데이터 디렉토리명을 가져온다. (class 변수)
            # (['pizza', 'steak', 'sushi'], {'pizza': 0, 'steak': 1, 'sushi': 2})
            self.classes, self.class_to_idx = find_classes(targ_dir)
    
        # 4. Make function to load images
        # index에 해당하는 이미지를 가져온다.
        def load_image(self, index: int) -> Image.Image:
            "Opens an image via a path and returns it."
            image_path = self.paths[index]
            return Image.open(image_path)
    
        # 5. Overwrite the __len__() method (optional but recommended for subclasses of torch.utils.data.Dataset)
        # 전체 데이터 수 => 전체 이미지의 수
        def __len__(self) -> int:
            "Returns the total number of samples."
            return len(self.paths)
    
        # 6. Overwrite the __getitem__() method (required for subclasses of torch.utils.data.Dataset)
        # 해당 index에 있는 데이터를 가져와 반환 (feature:이미지, target:이미지 index)
        def __getitem__(self, index: int) -> Tuple[torch.Tensor, int]:
            "Returns one sample of data, data and label (X, y)."
            # index에 해당하는 이미지 가져오기
            img = self.load_image(index)
            # class_name : index 전체 경로의 상위폴더의 이름
            class_name  = self.paths[index].parent.name # expects path in data_folder/class_name/image.jpeg
            # class_name에 해당하는 index
            class_idx = self.class_to_idx[class_name]
    
            # Transform if necessary
            if self.transform:
                return self.transform(img), class_idx # return data, label (X, y)
            else:
                return img, class_idx # return data, label (X, y)
    ```
