# K-Digital Training X 멀티 캠퍼스

#### AI 기반 지능형 서비스 개발 A반 파이널 프로젝트 기획 (팀)

**2021.04.14**

___

<br/>

#### 📌프로젝트 기간

---

#### 2021 - 04 - 19 - MON ~ 2021 - 05 - 12 - WED 

- **04 - 19 - MON~ 05 - 24 - SAT **:  **쇼핑몰 기능 구현**
- **04 - 26 - MON~ 05 - 11 - SAT **:  **쇼핑몰 기능 구현 + 챗봇 학습 및 API 연동**
- **05 - 03 - MON~ 05 - 11 - SAT **:  **쇼핑몰 기능 구현 + 코드 리팩토링**
- **05 - 10 - MON~ 05 - 11 - TUE**: **결과 보고서 작성 및 시연 영상 제작**
- **05 - 12 - WED** : **경진 대회 및 수료**

<br/>

- **프로젝트 실 진행 기간 약 25일**

<br/>

#### 📌기술 스택

<details>
    <summary>📁 개발 도구</summary>
    📂 STS4<br/>
    📂 Spring Boot 2.4.4<br/>
    📂 JDK 11<br/>
    📂 Maven<br/>
    📂 Spring data JPA<br/>
    📂 Lombok<br/>
    📂 Thymeleaf<br/>
    📂 BootStrap 5<br/>
    📂 <a href="https://github.com/ktae23/TIL/tree/master/python">Python Crawler</a><br/>
</details>


<details>
    <summary>📁 Deployment</summary>
    📂 AWS EC2 Ubuntu <br/>
	📂 Docker
</details>

<details>
    <summary>📁 Collaboration</summary>
📂 <a href="https://github.com/moviementorteam/MM">Git-Hub-Organizations</a><br/>
📂 <a href="https://github.com/ktae23/TIL/blob/master/Git/MM_Git-Flow.png">Git-Flow</a><br/>
📂 Slack<br/>
📂 Notion<br/>
</details>

<br/>

#### 📌구현 기능

___

####  📋 도메인

- 영화 추천, 구매 쇼핑몰
  - 채팅을 통해 선택지를 줄여 간다
  - 채팅을 통해 영화 추천을 받는다

<br/>

#### 🔐 계정

- 서비스 이용을 위해 반드시 회원 가입 후 로그인을 한다
- ID는 유일 값으로 이메일 형식으로 저장한다
- 회원 정보 중 ID 값은 변경 할 수 없다
- 서비스 이용을 중단하고 싶을 경우 회원 정보를 삭제(탈퇴)한다.

<br/>

#### :clapper: 영화​ 

- 로그인 이후 서비스 이용한다
- 영화를 제목, 장르, 주연 배우, 감독의 키워드로 조회 가능한다
- 영화 정보는 제목, 상영시간, 개봉일, 장르, 감독, 주연배우, 줄거리, 평점, 포스터를 제공한다

<br/>

#### 👩🏻‍🏫 Mentor

- 채팅을 통해 영화 정보를 조회 할 수 있다
- 채팅을 통해 영화 선택지를 좁힌다
- 채팅을 통해 영화 추천을 받는다

<br/>

#### :shopping_cart: 장바구니

- 구매를 원할 경우 우선 장바구니에 담는다
- 장바구니에서 구매를 원하지 않는 내역은 삭제한다

<br/>

#### :credit_card: 구매

- Payment Gateway API 연동

- 구매 이후 다운로드한다
- 다운로드 이력 없을 경우 환불이 가능하다

<br/>

#### 💾 DB

- Oracle XE : SQL Developer
- TABLE : `USER`, `MOVIE`, `POSTER`, `CART`, `ORDER`



