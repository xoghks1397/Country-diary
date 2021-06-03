# **프로젝트 세팅 및 실행**

**Backend**

빌드 및 실행

```
$ config_server 실행(http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8088)
$ zuul 실행(http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8089)
$ FarmDiary_User_Service,FarmDiary_Board_Service,FarmDiary_Guide_Service 실행 http://k4a305.p.ssafy.io:8091,8092,8093
```



**Frontend**

.env.local 설정

```
VUE_APP_SERVER_URL = http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8089/KNY
VUE_APP_SERVER_URL2 = http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8089/LSH
```

빌드 및 실행

```
$ yarn install
$ yarn serve
```

