# 작업 룰

### 브랜치 관리

```bash
(master) -> (develop) -> ((FE,BE)_이름_기능명)
```

- master : 배포 시 develop에서 merge
- develop : 기능단위 개발완료시 feature에서 merge
- feature :  기능단위로 개발시, develop에서 브랜치 생성

                   ex) BE_KRY_Login(꼭지키기!!)


```bash
* master : 배포 가능한 상태로 유지
* develop : 개발용 최상위 branch
* fetaure : 기능별 branch
```

### Commit(Merge Request) Convention

```bash
type(타입) : title(제목)

body(본문, 생략 가능)

See also : #issue, ...(참고 이슈, 생략 가능)
##################################################
    types = {
      feat : 새로운 기능에 대한 커밋
      fix : 버그 수정에 대한 커밋
      build : 빌드 관련 파일 수정에 대한 커밋
      chore : 그 외 자잘한 수정에 대한 커밋
      ci : CI관련 설정 수정에 대한 커밋
      docs : 문서 수정에 대한 커밋
      style : 코드 스타일 혹은 포맷 등에 관한 커밋
      refactor :  코드 리팩토링에 대한 커밋
      test : 테스트 코드 수정에 대한 커밋
    }

ex)
feat: 로그인 기능 구현 / fix: 회원가입 수정
```

### Git 사용법

```bash
git switch 브런치명
git branch 브런치명
pull 받는다.
push 올린다.

순서
git add.
git commit -m "메세지"
git push/pull origin branch
```