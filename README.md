-- update 중~~ --

# 0. Getting Started (시작하기)
```bash
$ ./gradlew clean build -x test 
$ docker compose up --build -d
```
---->      <----

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
| 기술          | 로고 |
|-----------------|-----------------|
| JAVA          | ![JAVA](https://github.com/user-attachments/assets/5df80afe-5c3d-47c1-9f6d-1549d1b2fc42) | 
| Spring Boot   | ![Spring Boot](https://github.com/user-attachments/assets/4d0c279a-0dfe-4253-b79e-c615935f9aff) |
| Spring Security | ![Spring Security](https://github.com/user-attachments/assets/f4744195-f1d0-44d9-b64c-0a027aaaf05a) |
| JWT   | ![JWT](https://github.com/user-attachments/assets/d43d4e63-3d5c-4ba2-a323-a68c6a8924e1) |
| Spring Batch   | ![Spring Batch](https://github.com/user-attachments/assets/a88d9a9b-d01b-45c1-99ff-a4c431183dc4) |
| Python   | ![Python](https://github.com/user-attachments/assets/4d097a53-ce0b-4d9c-8182-7eb7af9750d0) |
| Fast API  | ![Fast API](https://github.com/user-attachments/assets/bcf0c46b-80b8-4305-b6b3-8c58c20ddb7a) |

## 5.2 Frontend
| 기술          | 로고 | 버전 |
|-----------------|-----------------|-----------------|
| React    | ![React](https://github.com/user-attachments/assets/e3b49dbb-981b-4804-acf9-012c854a2fd2) | latest |
| CSS3     | ![CSS3](https://github.com/user-attachments/assets/c531b03d-55a3-40bf-9195-9ff8c4688f13) | latest |
| JavaScript | ![JavaScript](https://github.com/user-attachments/assets/4a7d7074-8c71-48b4-8652-7431477669d1) | latest |

## 5.3 DBMS
| 기술          | 로고 | 버전 |
|-----------------|-----------------|-----------------|
| Redis    | ![Redis](https://github.com/user-attachments/assets/5de8138b-f2c4-4f72-ac54-3266f6e9b257) | latest |
| MySQL    | ![MySQL](https://github.com/user-attachments/assets/19757339-cd0f-4388-8129-5476409dd88c) | latest |

## 5.4 Infra
| 기술          | 로고 |
|-----------------|-----------------|
| Docker    | ![Docker](https://github.com/user-attachments/assets/14027b7d-6e68-4918-8a01-ac2ba9e9778a) |
| K6    | ![K6](https://github.com/user-attachments/assets/d3e9ec42-1254-4593-a644-fc8fabb016ca) |
| AWS    | ![AWS](https://github.com/user-attachments/assets/0ec812d8-6256-425a-bbf4-797b5a0c5c49) |
| Elasticache   | ![Elasticache](https://github.com/user-attachments/assets/5693f3b6-c0c9-4265-9afd-37b07e73f27e) |
| ECS    | ![ECS](https://github.com/user-attachments/assets/34a918db-c371-4f26-92f6-bd9fa29822bf) |
| ECR    | ![ECR](https://github.com/user-attachments/assets/3fdfef19-4a99-47b2-a07c-39f4aa5427af) |
| SQS    | ![SQS](https://github.com/user-attachments/assets/5ad952cc-c28e-403f-ac76-4d7abeeff85e) |
| ELB    | ![ELB](https://github.com/user-attachments/assets/4aaa5df6-bc26-464f-8352-00a7e1cb235f) |
| RDS    | ![RDS](https://github.com/user-attachments/assets/6576e918-77ad-4357-ba5b-293da222d28f) |
| AutoScaling    | ![AutoScaling](https://github.com/user-attachments/assets/6458fddc-6af5-4746-a7a7-1927301c9737) |

## 5.5 Cooperation
| 기술          | 로고 |
|-----------------|-----------------|
| Git    | ![Git](https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6) |
| Figma    | ![Figma](https://github.com/user-attachments/assets/aa07f6bc-5034-4461-babf-82ada48f36b0) |
| Notion   | ![Notion](https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a) |


# 6. Project Structure (프로젝트 구조)
```plaintext
main
└── java
    └── com
        └── Familiship
            └── checkkuleogi
                ├── batch
                ├── config
                └── domains
                    ├── book
                        ├── dto
                            ├── request
                            └── response
                        ├── exception
                        ├── implementation
                            └── mapper
                        ├── jpa
                            └── repository
                        ├── presentation
                        └── service
                    ├── child
                    ├── recommend
                    └── user
                ├── global
                ├── security
                    └──jwt
                ├── leaderboard
                └── user
└── CheckkuleogiApplication
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
<img src="https://github.com/user-attachments/assets/2a1c29e2-384c-402d-9b65-c6142e5b062d" alt="ERD" width="1020"> <br/><br/>

# 9. 시스템 아키텍처

## 9.1 도서 MBTI 추출
<img src="https://github.com/user-attachments/assets/afc8fcb5-ed52-4918-9d97-c0a60113c653" alt="MBTI추출" width="500"> <br/><br/>

## 9.2 추천 시스템 
<img src="https://github.com/user-attachments/assets/48cbe44d-2ef5-4e95-b403-7eaa39a2106a" alt="추천" width="500"> <br/><br/>

## 9.3 선착순 시스템
<img src="https://github.com/user-attachments/assets/7f39b82f-cf66-4b57-8ef7-2d5fbfb1c42a" alt="선착순" width="500"> <br/><br/>

## 9.4 유저 MBTI 변환
<img src="https://github.com/user-attachments/assets/9eef0afd-6da2-4a73-9353-bf62e0248906" alt="MBTI변환" width="500"> <br/><br/>

# 10. 인터페이스 설계
<img src="" alt="React" width="300">
<img src="" alt="React" width="300">
<img src="" alt="React" width="500"> <br/><br/>



