### Install OpenCV

```python
!pip install Pillow
```

### Read Image

```python
im = Image.open(args.default_path+"deer.jpg")
```

### Save Image

```python
im.save(args.default_path+"save_deer.jpg")
```

### **Bands**

- RGB 컬러 정보

```python
im.getbands()
```

### Modes

- L : 흑백
- RGB
- RGBA : RGB + 투명도
- YCbCr : 컬러 비디오 형식

```python
im.mode
```

### **Size**

```python
# OpenCV는 (height, width)
width, hight = im.size # (width, hight)
width, hight
```

### Crop

```python
box = (100, 150, 300, 300)
cropped_image = im.crop(box)
```

### Rotate

```python
rotated = im.rotate(180)
```

### Merge

```python
# 이미지 불러오기
logo = Image.open(args.default_path+"logo_pillow.png")
tmp_im = im.copy()

# 이미지 합치기
position = (40, 350)
tmp_im.paste(logo, position)
```

### **PIL image to Numpy array**

```python
import numpy as np

im_array = np.array(im)
img_np = Image.fromarray(im_array, 'RGB')
```

### Color transformation

```python
# L => gray
im.convert('L')
```

### Image enhancement

- 이미지 향상

```python
from PIL import ImageEnhance

enhancer = ImageEnhance.Sharpness(im)
enhancer.enhance(10.0)
```

### Filters

```python
from PIL import ImageFilter

# Blur
im.filter(ImageFilter.BLUR)

# 색상 제거, 윤곽만
# Contour
im.filter(ImageFilter.CONTOUR)

# Detail
im.filter(ImageFilter.DETAIL)

# Edge_Enhance
# 선을 또렷하게 표시
im.filter(ImageFilter.EDGE_ENHANCE)

# Emboss
im.filter(ImageFilter.EMBOSS)

# Find_Edges
im.filter(ImageFilter.FIND_EDGES)
```
