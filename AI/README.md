# 전원일기

---

[TOC]

---



## Intro

> 2021년 SSAFY 2학기 자율프로젝트로 `전원일기`라는 도서농업 포털 서비스를 개발하였다.
>
> 개발기간: 2021년 4월 12일 - 2021년 5월 21일
> 공공데이터 포털에서 제공하는 데이터를 토대로 라벨링하여 식물이미지가 입력되었을때 해당 식물과 질병가능성 추측하는 Image Classifier구현이 목표

### Service

- 병충해 판단을 AI 알고리즘으로 진단한다.
- 사용자간의 **나눔 게시판** 및 **자유게시판** 서비스를 제공한다.

### Compatibility

- 모델링은 `tensorflow 1.14.0`, `keras 2.3.1`, `python 3.7.0`, `numpy 1.15.4`, `libopencv 3.4.2`, `pillow 8.2.0`, `pip 21.0.1`, `pymongo 3.11.4`, `scikit-learn 0.19.1`, `scipy 1.1.0` 버전에서 테스트되었다.
- 데이터 수치화(그래프)는 `matplotlib 3.3.2` 버전에서 테스트 되었습니다.

## AI(병충해 진단)
- 최종 목표는 아래와같이 인코딩된 이미지 String값이 MongoDB에 저장되면, 이를 불러와 디코딩한 후 \multi_img_data\imgs_others\test 폴더에 Imagexx.png 형태로 저장합니다.

![encod](https://user-images.githubusercontent.com/77223675/119621140-be9bd480-be40-11eb-9238-f75939737706.PNG)

- 이후 설계한 모델에 테스트 이미지를 불러와 추론한 후 질병명과 확률을 PestName과 Percentage의 결과로 저장하고  MongoDB에 해당 부분만 저장합니다.
![KakaoTalk_20210526_155345150](https://user-images.githubusercontent.com/77223675/119620182-ae372a00-be3f-11eb-8b23-5c61622c693d.png)

![result2](https://user-images.githubusercontent.com/77223675/119620903-7a103900-be40-11eb-98ce-a96b1b1da7ff.png)


## 모델 환경 설명(Requirements)

> `전원일기` 플랫폼에서는 크게 **병충해 진단 및 검색, 나눔 게시판 및 자유 게시판**의 2가지 세부서비스를 제공하는데 AI모델은 진단 서비스에 해당됩니다.
>
> - In this platform, users can utilize 2 services of `isease and pest diagnosis and search`,`sharing bulletin board and free bulletin board`

- 환경구축에대해 말씀드리자면 우선 오픈소스라이브러리를 위해 tensorflow를 사용하였고 가장 정확한 Image Classifier를 모델링하기위해 matplotlib, Keras, Scikit learn, numpy, pandas등을 대표적으로 활용했습니다.
- matploblib는 파이썬에서 매트랩과 유사한 그래프를 표시하기위한 라이브러리로, 이따 deeplearning파트에서 보여드리겠지만, 딥러닝의 Loss function을 가시화하기위한 목적으로 사용하였습니다.\
- keras는 파이썬으로 작성된 오픈소스 신경망 라이브러리로, 딥 신경망을 설계하기위해 (CNN사용)사용하였습니다. 
- Scikit learn활용을 위해 pandas, numpy를 설치하였고 keras 버전 호환을 위해 python은 3.7버전으로 유지하였습니다.


![CNN](https://user-images.githubusercontent.com/77223675/119621912-a11b3a80-be41-11eb-8f18-27711d7ebd38.PNG)

- CNN으로 설명드리면 저는 keras로 Multi Layer Perceptron을 이용하였고 이미지 크기는 64x64로 정하여 RGB값으로 바꿔주면서 resize하여 그 값을 numpy 라이브러리를 이용해 저장하였습니다.
- 이렇게 저장된 훈련된 모델을 같고 conv2D를 이용해 만든 신경망 모델로 3x3크기의 컨볼루션 레이어를 32개의 필터수를 처음에 생성하였고 maxpolling2D함수를 통해 주요 값만 뽑아내어 작은 출력값을 만들어내 디테일한 정확도를 높였습니다.
그리고 Flatten()함수는 CNN에서 맥스풀링을 거쳐 나온 특징을 다시 전달하여 학습합니다.

![epochs1](https://user-images.githubusercontent.com/77223675/119622872-8e553580-be42-11eb-9fe9-d8a777aa4889.png)

- 이런식으로 설계된 모델에 저는 질병 라벨별 데이터셋으로 약 200장의 사진을 선정하였습니다. 처음에는 keras imagedatagenerator로 이미지를 각도를 바꿔 움직이는 등으로 데이터를 더 모아 1000장씩 하려했으나 속도문제도 있고 과적합(Overfitting)의 문제로 정확도가 낮아지는 문제가 생겨 100장씩 낮춰가며 결과를 재확인하였고 200장이 95%라는 가장높은 정확도가 도출되었습니다.
- 위 사진은 Loss function의 값을 최소화 하기위해 최대 50번까지 Test를 시도한 모습입니다. 
 
![epochs2](https://user-images.githubusercontent.com/77223675/119623213-ea1fbe80-be42-11eb-8b5e-8d5ae716aa84.png)

- test가 37번째를 넘어가는 순간부터 Loss는 더 이상 작아지지않는 최솟값의 결과를 얻을 수 있었습니다.

![graph](https://user-images.githubusercontent.com/77223675/119623378-12a7b880-be43-11eb-987e-3dcece1211d4.png)

- 그리고 이에 해당되는 95%의 추론 정확도를 확인할 수 있었습니다.

![percentage](https://user-images.githubusercontent.com/77223675/119624287-f3f5f180-be43-11eb-868f-622c106e5eee.png)

- 그리고 이를 시각화 하기위해 matplotlib를 사용하여 Epochs에 따른 Loss Value의 변화를 확인할 수 있었습니다.
- 이렇게 test가 끝난 모델을 ./multi_img_data/imgs_others/test에 .ipynb_checkpoints폴더로 저장하고  아래 사진과 같이 모델에 입력하고자 하는 이미지들을 MongoDB에서 디코딩하여 저장합니다.

![test_example](https://user-images.githubusercontent.com/77223675/119624020-b2fddd00-be43-11eb-95a6-70d1a3ce640e.png)

- test하고자하는 이미지들을 설계한 Image classifier에 입력한 결과 

![result_example](https://user-images.githubusercontent.com/77223675/119624057-bdb87200-be43-11eb-9d4a-db8a2a2bf130.png)

- PestName과 Percentage를 확인할 수 있었습니다.










***Copyright* © 2021 SSAFY_SEOUL3_TEAM4_캠퍼스**

