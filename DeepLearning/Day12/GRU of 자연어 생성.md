- Vanilla RNN 단점 보안

### GRU (Gated Recurrent Unit)

![1](../img/img_gru1.png)

- LSTM과 비슷한데 구조가 단순하다.
- LSTM에서의 cell이 없다.
- input 2개
    - hidden_state : LSTM에서 hidden_state와 cell_state의 역할을 같이 한다. (재사용)
    - feature
- output 2개
    - hidden_result
    - target
- Reset Gate
    - LSTM의 Forget Gate
- Updata Gate
- 💡이론상으로는 LSTM이랑 같지만 대부분 사람들이 실험을 해 보았을 때 LSTM이 성능이 더 좋다고 했다. (확실치 않음)
