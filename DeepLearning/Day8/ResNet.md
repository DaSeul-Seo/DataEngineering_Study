- VGG
    - Layer가 많을수록 Error가 감소할 것이라고 생각했다.
    - 하지만 모델이 클수록 성능이 떻어지는 것을 발견
    - 모델이 더 어려워져서 score가 낮아지는 것이다.

### ResNet

- VGG 단점 보안
    - 모델 학습 난이도를 줄이고 Layer를 늘려보자.
    - 하나의 Layer가 끝날 때마다 input data(x)를 더해준다.
    
- BasicBlock

![1](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/449c152f-5f47-4d68-bab8-23b46270d7e2)

- ResNet은 BasicBlock이 여러개 반복

![2](https://github.com/DaSeul-Seo/DataEngineering_Study/assets/67898022/d61178fb-c217-493b-8fcd-6e83d676f18c)
