### GAN (Generative Adversarial Networks)

- Binary Classifier
- 바닐라 GAN
- 오래된 모델
- 생성모델
    - 최종모델이 아니다.
    - Super Resolution
    - 중간모델로 사용
    - 내가 가진 이미지를 개선하는 용도
    - 비지도학습 - feature 차원축소

![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/166bcab2-2407-4940-a281-038868fbe7e4)

- 2개의 모델을 생성해서 각각 학습
- Generator가 아무 의미 없는 것을 가지고 가짜 데이터를 만든다.
- Discriminator가 진짜데이터와 가짜 데이터를 구별하
- Discriminator가 얼마나 속았는지에 대해 loss 계산
- 우리는 진짜 데이터를 가공해서 만들고 Generator가 가짜 데이터를 준다.
- Generator가 좋아지면 모델이 세밀하게 퀄리티가 좋아진다.
- Generator는 최대한 진짜 데이터에 가깝게, Discriminator는 최대한 잘 구별하게 학습

---

![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/0be9deeb-d13b-427d-a94a-e60304689c2c)

- Generator
    - input 으로 진짜 랜덤 binary 데이터
    - 진짜같은 데이터를 output을 내보낸다.
    - min값(G값)이 최소가 되도록 학습
    - 만든 가짜 데이터가 진짜 데이터로 판별이 얼마나 되었는지
    - 작은 데이터가 큰 데이터로 나온다. (Decoder)
    
    ![3](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/ab5a4d9f-db41-421a-857e-30a1b2872382)

- Discriminator
    - 진짜 데이터와 Generator의 output을 학습해서 구별해주는 모델을 만든다.
    - 정답이 중요하지 않다. (label 정보 불필요)
    - 데이터가 진짜인지 아닌지만 구별한다.
    - max값(D값)이 최대가 되도록 학습
    - 큰 데이터가 작은 데이터로 나온다. (Encoder)
    
    ![4](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/e8c54431-805e-4260-81ad-8c38dcd92d01)

- Discriminator의 InputData는 Generator의 OutputData가 들어간다.

### CGAN (Conditional GAN)

- Multi Classifier
- GAN에서 label(target) 까지 학습하여 정답을 찾는다.

![5](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/4ca7070c-954e-4593-901c-242a22b5ee52)

![6](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/24934b5e-717c-4643-9477-fdaa5684f72f)

- Generator : real label / fake data
- Discriminator : real label / real data
