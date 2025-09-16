# TechPost Backend

테크 블로그 포스트 관리 및 배치 처리를 위한 Spring Boot 멀티모듈 프로젝트입니다.

## 📋 프로젝트 개요

TechPost는 테크 블로그 포스트를 수집, 관리하고 사용자에게 제공하는 서비스입니다. 마이크로서비스 아키텍처를 지향하며 각 모듈이 독립적인 책임을 가지도록 설계되었습니다.

## 🏗️ 기술 스택

- **Java**: 21
- **Framework**: Spring Boot 3.3.3
- **Build Tool**: Gradle
- **Database**: MySQL (AWS RDS)
- **ORM**: JPA/Hibernate, QueryDSL
- **Batch**: Spring Batch, Quartz
- **Cache**: EhCache
- **Monitoring**: Spring Actuator
- **Cloud**: AWS (Secrets Manager)

## 📦 모듈 구조

### 1. app-api
- **역할**: 외부 클라이언트와의 인터페이스 담당. 클라이언트 요청을 처리하고 결과를 반환.
- **포트**: 18010
- **주요 기능**: 
  - RESTful API 엔드포인트 제공
  - `domain` 모듈을 이용해 비즈니스 로직 처리
  - Controller 계층 포함
  - 캐싱 기능 (EhCache)
  - API 유효성 검증
  - 헬스체크 엔드포인트

- **주요 의존성**:
  - Spring Web, JPA, Validation, Cache, Actuator
  - Jakarta Persistence API
  - EhCache

- **의존 관계**:
  - `domain` 모듈에 의존
  - `common` 모듈에 의존

---

### 2. app-batch
- **역할**: 배치 작업을 수행하는 모듈. 대량의 데이터를 처리하고 주기적 작업을 자동화.
- **주요 기능**: 
  - Spring Batch를 이용한 테크 블로그 스크랩 작업
  - Quartz 스케줄러를 통한 주기적 배치 실행
  - WebFlux를 통한 비동기 외부 API 호출
  - 배치 잡 실행 및 관리
  - 알림 발송 기능

- **주요 의존성**:
  - Spring Batch, Quartz, WebFlux
  - Jackson (JSON 처리)
  - H2 Database (테스트용)

- **의존 관계**:
  - `domain` 모듈에 의존 (데이터 처리 및 엔티티 관련 작업)
  - `common` 모듈에 의존 (공통 유틸 및 설정 활용)
  - `notification` 모듈에 의존 (알림 기능)

---

### 3. domain
- **역할**: 핵심 비즈니스 로직과 데이터 모델을 담당하는 모듈.
- **주요 기능**:
  - JPA 엔티티 클래스 및 Repository 계층
  - 비즈니스 로직 처리를 위한 Service 계층
  - QueryDSL을 통한 복잡한 쿼리 처리
  - AWS Secrets Manager 연동
  - JWT 토큰 관리
  - 데이터베이스 연결 및 설정

- **주요 의존성**:
  - Spring Data JPA, Actuator
  - JWT (JJWT)
  - AWS Secrets Manager
  - MySQL Connector
  - Spring Security Crypto

- **의존 관계**:
  - `common` 모듈에 의존
  - **독립성**: 다른 도메인 모듈에 의존하지 않음

---

### 4. notification
- **역할**: 알림 기능을 담당하는 모듈.
- **주요 기능**: 
  - 다양한 알림 채널 지원 (이메일, 슬랙, 웹훅 등)
  - WebFlux를 통한 비동기 알림 처리
  - 알림 템플릿 관리

- **주요 의존성**:
  - Spring WebFlux, Data JPA
  - QueryDSL

- **의존 관계**:
  - `common` 모듈에 의존
  - **독립성**: 다른 도메인 모듈에 의존하지 않음

---

### 5. common
- **역할**: 여러 모듈에서 공통적으로 사용하는 유틸리티, 설정 등을 포함한 모듈.
- **주요 기능**: 
  - 공통 유틸리티 클래스 (`DateUtils`, 공통 상수 등)
  - 공통적인 설정이나 프로퍼티 파일 관리
  - 공통 예외 처리
  - 공통 DTO 클래스

- **의존 관계**:
  - **의존하지 않음**: 다른 모듈들이 `common` 모듈을 의존

---

## 🔗 모듈 간 의존 관계

```
app-api → domain, common
app-batch → domain, common, notification
domain → common
notification → common
common → (의존 없음)
```

## 🚀 실행 방법

### 1. 사전 요구사항
- Java 21
- MySQL 8.0 이상
- AWS 자격 증명 설정 (Secrets Manager 사용)

### 2. AWS 설정
```bash
# AWS 자격 증명 설정
aws configure
# 또는 환경 변수 설정
export AWS_ACCESS_KEY_ID=your_access_key
export AWS_SECRET_ACCESS_KEY=your_secret_key
export AWS_DEFAULT_REGION=ap-northeast-2
```

### 3. Secrets Manager 설정
AWS Secrets Manager에 다음과 같은 Secret을 생성하세요:
- **Secret Name**: `dev/techpost/mysql`
- **Secret Value**:
```json
{
  "host": "your-rds-endpoint.ap-northeast-2.rds.amazonaws.com",
  "port": "3306",
  "username": "your_username",
  "password": "your_password"
}
```

### 4. API 서버 실행
```bash
./gradlew :app-api:bootRun
```
- 접속 URL: http://localhost:18010
- 헬스체크: http://localhost:18010/actuator/health

### 5. 배치 서버 실행
```bash
./gradlew :app-batch:bootRun
```

## 🗂️ 프로젝트 구조

```
techpost-be/
├── app-api/          # REST API 서버
├── app-batch/        # 배치 처리 서버
├── domain/           # 도메인 로직 및 데이터 모델
├── notification/     # 알림 기능
├── common/           # 공통 유틸리티
├── docs/            # 프로젝트 문서
│   ├── sql/         # DDL 스크립트
│   └── uml/         # UML 다이어그램
└── gradle/          # Gradle 설정
```

## 🛠️ 개발 환경 설정

### 1. 로컬 개발 환경
- Profile: `local`
- 데이터베이스: 로컬 MySQL 또는 AWS RDS
- 설정 파일: `application-local.yml`

### 2. 운영 환경
- Profile: `prod`
- 데이터베이스: AWS RDS
- 설정 파일: `application-prod.yml`

## 📝 주요 기능

### API 기능
- 테크 블로그 포스트 CRUD
- 사용자 인증 및 권한 관리 (JWT)
- 캐싱을 통한 성능 최적화
- API 유효성 검증

### 배치 기능
- 테크 블로그 사이트 스크랩핑
- 주기적 데이터 수집 및 갱신
- 배치 작업 모니터링
- 실패 시 알림 발송

### 모니터링
- Spring Actuator를 통한 헬스체크
- AWS Secrets Manager 연결 상태 모니터링
- 데이터베이스 연결 상태 확인

## 🔧 빌드 및 배포

### 빌드
```bash
./gradlew clean build
```

### Docker 빌드 (API)
```bash
cd app-api
docker build -t techpost-api .
```

### Docker 빌드 (Batch)
```bash
cd app-batch  
docker build -t techpost-batch .
```

## 📚 참고 문서

- [RDS 연결 가이드](docs/RDS_CONNECTION_GUIDE.md)
- [DDL 스크립트](docs/sql/ddl/)
- [UML 다이어그램](docs/uml/)

## ⚠️ 주의사항

1. **AWS 자격 증명**: 로컬 개발 시 AWS 자격 증명이 올바르게 설정되어야 합니다.
2. **데이터베이스**: MySQL 8.0 이상 버전을 사용해야 합니다.
3. **Java 버전**: Java 21을 사용합니다.
4. **Secret 관리**: 민감한 정보는 AWS Secrets Manager를 통해 관리합니다.
