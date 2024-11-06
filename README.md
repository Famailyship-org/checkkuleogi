-- update 중~~ --

# 0. Getting Started (시작하기)
```bash
$ ./gradlew clean build -x test 
$ docker compose up --build -d
```
---->   [서비스 링크](https://coupong.netlify.app/)   <----

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)
- 프로젝트 이름: 책꾸러기
- 프로젝트 설명: 자녀 맞춤 도서 추천 시스쳄

<br/>
<br/>

# 2. Team Members (팀원 및 팀 소개)
| 진명인 | 권예진 | 김태연 | 박정웅 | 양석진 |
|:------:|:------:|:------:|:------:|:------:|
| <img src="https://avatars.githubusercontent.com/myeonginjin" alt="진명인" width="150"> | <img src="https://avatars.githubusercontent.com/YaeJinKwon" alt="권예진" width="150"> | <img src="https://avatars.githubusercontent.com/taeyeon0319" alt="김태연" width="150"> | <img src="https://avatars.githubusercontent.com/daase"  alt="박정웅" width="150"> | <img src="https://avatars.githubusercontent.com/harvartz"  alt="양석진" width="150"> 
| BE | BE | BE | BE | BE |
| [GitHub](https://github.com/myeonginjin) | [GitHub](https://github.com/YaeJinKwon) | [GitHub](https://github.com/taeyeon0319) | [GitHub](https://github.com/daase) | [GitHub](https://github.com/harvartz) |

<br/>
<br/>

# 3. Key Features (주요 기능)

- **개인 맞춤형 추천 시스템**:
  - 매일 밤 자정, 이벤트 목록이 초기화됩니다.  `스케줄러 동적 할당`
  - 이벤트는 카테고리별로 동일한 날짜, 동일한 시간대에 여러 이벤트가 진행될 수 있습니다.  `멀티 스케줄러`
 
- **선착순 응모 시스템**:
  - 
 
- **도서 성향 추출 및 등록**:
  - 
- 
<br/>
<br/>

# 4. Tasks & Responsibilities (작업 및 역할 분담)
|  |  |  |
|-----------------|-----------------|-----------------|
| 진명인    |  <img src="https://avatars.githubusercontent.com/myeonginjin" alt="진명인" width="100"> | <ul><li>팀 리딩</li><li>프로젝트 기획 및 설계</li></ul>     |
| 권예진  |  <img src="https://avatars.githubusercontent.com/YaeJinKwon" alt="권예진" width="100">| <ul><li>개인 맞춤형 추천 시스템</li><li>스프링 배치를 통한 진단 데이터 물리적 삭제</li></ul> |
| 김태연  |  <img src="https://avatars.githubusercontent.com/taeyeon0319" alt="김태연" width="100">| <ul><li>도서 성향 추출 시스템</li><li>피그마 및 프론트엔드</li></ul> |
| 박정웅    |  <img src="https://avatars.githubusercontent.com/daase" alt="박정웅" width="100">    | <ul><li>인증/인가 시스템</li><li>대규모 아키텍쳐 설계 및 세팅</li></ul>    |
| 양석진  |  <img src="https://avatars.githubusercontent.com/harvartz" alt="양석진" width="100">    |<ul><li>선착순 응모 시스템 </li><li>대규모 아키텍쳐 설계 및 세팅</li></ul>  |


<br/>


# 5. Technology Stack (기술 스택)
## 5.1 Backend
|  |  |
|-----------------|-----------------|
| JAVA          |<img src="https://github.com/user-attachments/assets/5df80afe-5c3d-47c1-9f6d-1549d1b2fc42" alt="JAVA" width="200">| 
| Spring Boot   |   <img src="https://github.com/user-attachments/assets/4d0c279a-0dfe-4253-b79e-c615935f9aff" alt="Spring Boot" width="200">|
| Spring Security   |   <img src="https://github.com/user-attachments/assets/f4744195-f1d0-44d9-b64c-0a027aaaf05a" alt="Spring Security" width="200">|
| JWT   |   <img src="https://github.com/user-attachments/assets/d43d4e63-3d5c-4ba2-a323-a68c6a8924e1" alt="JWT" width="200">|
| Spring Batch   |   <img src="https://github.com/user-attachments/assets/a88d9a9b-d01b-45c1-99ff-a4c431183dc4" alt="Spring Batch" width="200">|
| Python   |   <img src="https://github.com/user-attachments/assets/4d097a53-ce0b-4d9c-8182-7eb7af9750d0" alt="Python" width="200">|
| Fast API  |   <img src="https://github.com/user-attachments/assets/bcf0c46b-80b8-4305-b6b3-8c58c20ddb7a" alt="Fast API" width="200">|
<br/>

## 5.2 Frotend
|  |  |  |
|-----------------|-----------------|-----------------|
| React    |  <img src="https://github.com/user-attachments/assets/e3b49dbb-981b-4804-acf9-012c854a2fd2" alt="React" width="100"> | latest    |
| CSS3    |   <img src="https://github.com/user-attachments/assets/c531b03d-55a3-40bf-9195-9ff8c4688f13" alt="CSS3" width="100">| latest    |
| Javascript    |  <img src="https://github.com/user-attachments/assets/4a7d7074-8c71-48b4-8652-7431477669d1" alt="Javascript" width="100"> | latest    |
<br/>

## 5.3 DBMS
|  |  |  |
|-----------------|-----------------|-----------------|
| Redis    |  <img src="https://github.com/user-attachments/assets/5de8138b-f2c4-4f72-ac54-3266f6e9b257" alt="Redis" width="100">    | latest   |
| MySQL    |  <img src="https://github.com/user-attachments/assets/19757339-cd0f-4388-8129-5476409dd88c" alt="Redis" width="100">    | latest   |
<br/>

## 5.3 Infra
|  |  |  
|-----------------|-----------------|
| AWS    |  <img src="https://github.com/user-attachments/assets/0ec812d8-6256-425a-bbf4-797b5a0c5c49" alt="AWS" width="100">    
| Docker    |  <img src="https://github.com/user-attachments/assets/14027b7d-6e68-4918-8a01-ac2ba9e9778a" alt="Docker" width="100">
| Elasticahce   |  <img src="https://github.com/user-attachments/assets/5693f3b6-c0c9-4265-9afd-37b07e73f27e" alt="Elasticahce" width="150">
| K6    |  <img src="https://github.com/user-attachments/assets/d3e9ec42-1254-4593-a644-fc8fabb016ca" alt="K6" width="150">
<br/>

## 5.4 Cooperation
|  |  |
|-----------------|-----------------|
| Git    |  <img src="https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6" alt="git" width="100">    |
| Figma    |  <img src="https://github.com/user-attachments/assets/aa07f6bc-5034-4461-babf-82ada48f36b0" alt="Figma" width="100">    |
| Notion    |  <img src="https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a" alt="Notion" width="100">    |

<br/>

# 6. Project Structure (프로젝트 구조)
```plaintext
main
└── java
    └── com
        └── onepage
            └── coupong
                ├── chat
                └── coupon
                    ├── api
                    ├── config
                    ├── domain
                    ├── dto
                    ├── exception
                    ├── repository
                    └── service
                ├── global
                ├── infrastructure
                ├── leaderboard
                └── user
└── CoupongApplication
resources
test
└── java
    └── com
        └── onepage
            └── coupong
                └── couponEventTest
                    ├── CouponEventSchedulerTest.java
                    ├── CouponEventServiceIntegrationTest
                    └── CoupongApplicationTests
```

<br/>
<br/>

# 7. Development Workflow (개발 워크플로우)
## 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- main
  - 배포 가능한 상태의 코드를 유지합니다.
  - 모든 배포는 이 브랜치에서 이루어집니다.
  
- develop/{feature/release/refactoring...}
  - 팀원 각자의 개발 브랜치입니다.
  - 모든 기능 개발은 이 브랜치에서 이루어집니다.

<br/>
<br/>

# 8. ERD
<img src="https://github.com/user-attachments/assets/25b6826f-99bc-4573-855c-c795f7f8dc47" alt="React" width="500"> <br/><br/>

# 9. 시스템 아키텍처
<img src="https://github.com/user-attachments/assets/c4d06585-0d93-481e-b14c-81b70ae594c3" alt="React" width="500"> <br/><br/>

# 10. 인터페이스 설계
<img src="https://github.com/user-attachments/assets/6e6238ea-3994-4eec-a1ad-d533b26a8971" alt="React" width="300">
<img src="https://github.com/user-attachments/assets/0516008c-7991-4cd5-b089-daac5b4611aa" alt="React" width="300">
<img src="https://github.com/user-attachments/assets/6185929c-0a1a-4250-94b8-d776ab0eca7c" alt="React" width="500"> <br/><br/>

# 11. 인증•인가 플로우
<img src="https://github.com/user-attachments/assets/8e286e17-370a-4f67-b3cc-0e4ae29ed673" alt="React" width="300"> <br/><br/>

# 12. 쿠폰 이벤트 시스템 프로세스
<img src="https://github.com/user-attachments/assets/4a5bf147-cd63-4eb0-97c6-f8fcffd91fa2" alt="React" width="500"> <br/><br/>

# 13. 금칙어 필터링 시스템 프로세스
<img src="https://github.com/user-attachments/assets/8df53e98-8da1-46fe-8830-e8914b587fb6" alt="React" width="500"> 
<img src="https://github.com/user-attachments/assets/187da3cf-ffdc-47ed-beb5-1211146bfa4f" alt="React" width="500"> 



