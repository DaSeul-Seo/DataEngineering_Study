## Numpy (Numerical Python)

- Numpy는 C언어 기반
- 모듈
- 계산을 빨리 하게 해준다
- 엑셀이라고 생각하면 된다.

![6](https://github.com/DaSeul-Seo/Playdata_Study/assets/67898022/d12dbe73-c4c1-4224-93d1-f2f1afeb3821)


- Scalar : 데이터 1개
- Vector
    - 리스트(1차원)
    - Scalar의 모임
- Martix
    - 행렬(2차원)
    - Vector의 모임
- Tensor
    - 3차원 이상

### numpy.ndarray

- ndarray.ndim : 차원
- ndarray.shape : 크기 튜플 (행수, 열수)
- ndarray.size : 전체 데이터 개수
- ndarray.dtype : 데이터 타입

### Numpy를 사용하는 이유

- Python 리스트보다 크키가 작고 빠르다.
- 메모리를 적게 사용하며, 편리하다.

### 배열결합

```python
arr1 = np.array([[1, 1],
               [2, 2]])
arr2 = np.array([[3, 3],
               [4, 4]])

'''
array([[1, 1],
       [2, 2],
       [3, 3],
       [4, 4]])
'''
arrv = np.vstack((arr1, arr2))

'''
array([[1, 1, 3, 3],
       [2, 2, 4, 4]])
'''
arrh = np.hstack((arr1, arr2))
```

### **슬라이싱/인덱싱**

- 1차원은 기본 리스트랑 동일
- 2차원
    
    ```python
    lst = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9]
    ]
    arr = np.array(lst)
    arr.shape   # (3, 3)
    
    arr[0,:]   # array([1, 2, 3])
    arr[0,:] = [11, 12, 13] # 0번쨰 값 변경
    arr[0,:] = [1, 2, 3] # 0번쨰 값 변경
    arr[:,0]   # array([1, 4, 7])
    # 전체로우,
    arr[:,::-1]
    '''
    array([[3, 2, 1],
           [6, 5, 4],
           [9, 8, 7]])
    '''
    arr[::2, :]
    '''
    array([[1, 2, 3],
           [7, 8, 9]])
    '''
    ```
    
- 슬리이싱/인덱싱 문제
    
    ```python
    import numpy as np
    
    arr = np.array([1, 2, 3, 4, 5, 6, 7])
    
    # 1. 7, 6, 5, 4, 3, 2, 1
    arr[::-1]
    ```
    
    ```python
    import numpy as np
    
    lst = [
        [1, 2, 3],
        [4, 5, 6],
        [7, 8, 9],
        [10, 11, 12]
    ]
    
    matrix = np.array(lst)
    matrix
    matrix.shape     # 4, 3
    
    matrix[1, 1]     # 5
    matrix[2,:]      # [7, 8, 9]
    matrix[:,-1]     # [ 3,  6,  9, 12]
    matrix[-1,::-1]  # [12, 11, 10]
    
    # 5, 6
    # 8, 9
    matrix[1:3,1:]
    
    # 4, 5
    # 7, 8
    # 10, 11
    matrix[1:,:2]
    
    # 6, 4
    # 9, 7
    matrix[1:3,::-2]
    
    # 9, 7
    # 3, 1
    matrix[2::-2,::-2]
    ```
