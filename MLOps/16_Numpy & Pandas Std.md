- numpy와 pandas의 표준편차의 값이 다른 이유
- numpy의 std는 모표준편차(ddof=0)를 default로 한다.
- 반면 pandas의 std는 표본표준편차(ddof=1)를 default로 한다.
- pandas의 자유도(ddof)를 고정해주면 표준편차의 값이 같아진다.

```python
import pandas as pd
import numpy as np

data = list(range(1, 11))
data   # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

np.std(data)   # 2.8722813232690143

pd.DataFrame(data).std()
'''
0    3.02765
dtype: float64
'''

pd.DataFrame(data).std(ddof=0)
'''
0    2.872281
dtype: float64
'''
```