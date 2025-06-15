# 효도리 (Hyodori) Backend

---

## 📘 Overview

**효도리**는 부모님과 자녀 간의 원활한 소통과 정서적 유대를 강화하기 위해 개발된 **가족 커뮤니케이션 앱**의 백엔드 서버입니다.  
사용자는 가족 구성원과의 일상적인 대화를 나누고, GPT 기반의 대화형 설문을 통해 정서적 상태를 파악하며, 건강 정보를 함께 관리할 수 있습니다.

**주요 목적**:

- 가족 간의 정서적 유대 강화
- GPT 기반의 친근한 대화형 설문 제공
- 부모님의 일상과 건강 상태 공유
- 안전하고 효율적인 데이터 관리

---

## 🛠 Tech Stack

### 📡 Backend

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: MySQL
- **Security**: Spring Security (JWT 기반 인증)
- **Build Tool**: Gradle
- **Documentation**: Swagger
- **AI Integration**: OpenAI GPT-3.5

---

## ✨ Features

| 기능               | 설명                                              |
| ------------------ | ------------------------------------------------- |
| **GPT 기반 대화**  | GDS 설문을 친근한 대화형으로 변환하여 제공        |
| **감정 분석**      | GPT를 활용한 사용자의 감정 상태 분석 및 추적      |
| **건강 정보 관리** | 사용자의 건강 상태를 기록하고 가족과 공유         |
| **통계 및 분석**   | GDS 점수, 감정 상태 등의 통계 및 트렌드 분석 제공 |
| **보안/인증**      | JWT 기반 인증 및 권한 관리                        |
| **API 문서화**     | Swagger를 통한 API 문서 자동 생성                 |

---

## 🧩 Architecture

```
+-------------+     REST API     +----------------+
|   iOS App   | <--------------> | Spring Server  |
+-------------+                  +----------------+
      |                                 |
      |    Network 통신 (JSON, HTTPS)    |
      v                                 v
+------------+                  +-------------------+
| SwiftUI UI |                  | MySQL Database    |
+------------+                  +-------------------+
```

- RESTful API 설계
- JWT 기반 인증
- 계층형 아키텍처 (Controller-Service-Repository)
- GPT 통합을 위한 서비스 레이어

---

## 📂 Project Structure

```
src/main/java/com/hyodori/backend/
├── config/          # 설정 클래스
├── controller/      # API 엔드포인트
├── domain/          # 엔티티 클래스
├── dto/            # 데이터 전송 객체
├── exception/      # 예외 처리
├── jwt/            # JWT 관련
├── repository/     # 데이터 접근 계층
└── service/        # 비즈니스 로직
```

---

## 🔑 API Endpoints

### 인증

- `POST /api/auth/login` - 로그인
- `POST /api/auth/signup` - 회원가입

### 대화

- `POST /api/conversations/{userId}` - 대화 생성
- `PUT /api/conversations/{id}/response` - 응답 업데이트
- `GET /api/conversations/user/{userId}` - 사용자 대화 목록
- `GET /api/conversations/{id}` - 특정 대화 조회

### 통계

- `GET /api/statistics/user/{userId}` - 사용자 통계
- `GET /api/statistics/user/{userId}/emotion-trends` - 감정 트렌드

### GDS

- `GET /api/gds/questions` - GDS 문항 목록
- `POST /api/gds/results` - GDS 결과 저장
- `GET /api/gds/results/user/{userId}` - 사용자 GDS 결과

---

## 🚀 Getting Started

### Prerequisites

- Java 17
- MySQL
- OpenAI API Key

### Installation

1. 저장소 클론

```bash
git clone https://github.com/hyodori/hyodori-backend.git
```

2. 환경 변수 설정

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hyodori
spring.datasource.username=your_username
spring.datasource.password=your_password

openai.api.key=your_openai_api_key
openai.api.url=https://api.openai.com/v1/chat/completions
```

3. 빌드 및 실행

```bash
./gradlew bootRun
```

---

## 📊 주요 기능 구현

### GPT 기반 대화

- GDS 설문을 친근한 대화형으로 변환
- 사용자 맞춤형 일상 대화 생성
- 감정 분석 및 추적

### 통계 및 분석

- GDS 점수 추적
- 감정 상태 분석
- 대화 패턴 분석

---

## 🧑‍💻 개발 가이드라인

### 코드 컨벤션

- Java 코드 스타일 가이드 준수
- 명확한 패키지 구조 유지
- 적절한 주석 작성

### 테스트

- JUnit을 활용한 단위 테스트
- 통합 테스트 작성
- API 테스트 (Postman/Swagger)

---

## 🔗 확장 가능성

- **다중 언어 지원**: 국제화(i18n) 구현
- **실시간 통신**: WebSocket 통합
- **캐싱**: Redis 도입
- **모니터링**: Prometheus + Grafana
- **CI/CD**: GitHub Actions

---

## 📄 License

MIT License

---

## ❓ FAQ

- **Q. GPT API 키는 어떻게 설정하나요?**  
  A. application.properties 파일에 openai.api.key 값을 설정하세요.

- **Q. 데이터베이스 스키마는 어떻게 생성하나요?**  
  A. JPA 엔티티를 기반으로 자동 생성되며, schema.sql에서 수동 설정도 가능합니다.

- **Q. API 문서는 어디서 확인할 수 있나요?**  
  A. 서버 실행 후 http://localhost:8080/swagger-ui/index.html 에서 확인 가능합니다.
