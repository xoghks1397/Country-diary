
# In[60]:


from PIL import Image
import os, glob, numpy as np
from keras.models import load_model

caltech_dir = "./multi_img_data/imgs_others/test"
image_w = 64
image_h = 64

pixels = image_h * image_w * 3

X = []
filenames = []
files = glob.glob(caltech_dir+"/*.*")
for i, f in enumerate(files):
    img = Image.open(f)
    img = img.convert("RGB")
    img = img.resize((image_w, image_h))
    data = np.asarray(img)
    filenames.append(f)
    X.append(data)

X = np.array(X)
model = load_model('./model/multi_img_classification.model')

prediction = model.predict(X)
np.set_printoptions(formatter={'float': lambda x: "{0:0.3f}".format(x)})
cnt = 0

for i in prediction:
    pre_ans = i.argmax()  
    print(i)
    print(pre_ans)
#     "blue", "jumbak", "white","gue","ipgom","america"
    pre_ans_str = ''
    if pre_ans == 0: pre_ans_str = "장미로, 청벌레병"
    elif pre_ans == 1: pre_ans_str = "장미로, 점박이응애병"
    elif pre_ans == 2: pre_ans_str = "장미로, 흰가루병"
    elif pre_ans == 3: pre_ans_str = "토마토로, 궤양병"
    elif pre_ans == 4: pre_ans_str = "토마토로, 잎곰팡이병"
    elif pre_ans == 5: pre_ans_str = "토마토로, 아메리카잎굴파리병"
    if i[0] >= 0.8 : print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[0]*100))
    if i[1] >= 0.8: print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[1]*100))
    if i[2] >= 0.8: print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[2]*100))
    if i[3] >= 0.8: print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[3]*100))
    if i[4] >= 0.8: print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[4]*100))
    if i[5] >= 0.8: print("해당 "+filenames[cnt].split("\\")[1]+"이미지는 "+pre_ans_str+"일 확률이 %.2f%%로 진단됩니다." % (i[5]*100))
        
    cnt += 1
    


# In[ ]:




