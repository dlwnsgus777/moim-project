# 모임 프로젝트

"오늘의 세미나, 웰컴 제공 음식 : 피자, 보쌈 도시락, 닭강정"
내가 특정 음식에 알러지가 있거나 비건이라면??

## 개요

세미나, 워크숍 참석시 참여 인원의 취식을 제한하는 재료을 토대로 식사를 정한다.

## 기능 요구사항 정리

- [x] 회원가입
    - [x] 서비스를 이용하기 위해 회원가입을 할 수 있다.
        - [x] 회원가입시 모임 주최자로 가입한다.
        - [x] 회원가입시 모임 참여자로 가입한다.
        - [x] 회원가입시 전달된 비밀번호를 암호화하여 저장한다.
- [x] 로그인
    - [x] 회원가입시 제출한 아이디와 비밀번호를 이용해 인증토큰을 발급 받는다.
        - [x] 인증토큰은 JWT를 사용한다.
- [x] 내 정보 업데이트
    - [x] 회원가입시 제출한 정보를 수정할 수 있다.
        - [x] 인증토큰을 이용한 사용자 인증 과정이 있다.
- [x] 모임 주최자로도 활동 / 모임 참여자로도 활동
    - [x] 추가적인 정보를 제출 받아서 참여자 혹은 주최자로 서비스를 이용할 수 있다.
        - [x] 인증토큰을 이용한 사용자 인증 과정이 있다.
- [x] 내 정보 보기
    - [x] 사용자는 작성한 내 정보를 조회할 수 있다.
        - [x] 비밀번호는 노출하지 않는다.
        - [x] 주최자, 참여자에 따라 다른 정보가 노출된다.
        - [x] 인증토큰을 이용한 사용자 인증 과정이 있다.
- [x] 유효성 검사
    - [x] 회원가입 / 정보 수정시 제출한 비밀번호가 비밀번호 정책에 부합하는지 확인한다.
        - [x] 회원가입
        - [x] 정보 수정
    - [x] 비밀번호 정책은 임의로 정한다.
        - 정책은 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호이다.

### 회원가입시 유저가 갖는 정보

- 주최자
    - 회원 번호
    - 이름
    - 생년월일
        - YYYYMMDD 포맷
    - 성별
    - 아이디
    - 비밀번호
    - 이메일
    - 소속
- 참여자
    - 회원번호
    - 이름
    - 생년월일
        - YYYYMMDD 포맷
    - 성별
    - 아이디
    - 비밀번호
    - 이메일
    - 취식을 제한하는 재료
    - 자기 소개