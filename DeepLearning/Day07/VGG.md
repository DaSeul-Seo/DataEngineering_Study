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
