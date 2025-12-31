# 🚀 TechPost Backend

> **헥사고날 아키텍처 기반의 테크 블로그 애그리게이션 플랫폼**
> 
> 국내 주요 테크 기업의 기술 블로그 포스트를 자동으로 수집하고 통합 제공하는 Spring Boot 멀티모듈 프로젝트

<br/>

## 📌 프로젝트 개요

TechPost는 카카오, 네이버 등 국내 주요 IT 기업의 기술 블로그를 자동으로 스크래핑하여 한 곳에서 조회할 수 있는 애그리게이션 서비스입니다. **헥사고날 아키텍처**(Ports & Adapters)를 적용하여 비즈니스 로직과 인프라 계층을 완전히 분리했으며, **도메인 주도 설계(DDD)** 원칙을 따라 각 모듈이 명확한 책임을 가지도록 설계했습니다.

<br/>

### ✨ 주요 특징

- 🏗️ **헥사고날 아키텍처**: 비즈니스 로직과 기술 세부사항의 완전한 분리
- 📦 **멀티모듈 구조**: 행위 중심의 명확한 모듈 분리 (Search, Save 등)
- 🔄 **자동화 배치**: Spring Batch 기반 주기적 데이터 수집
- 🎯 **DDD 적용**: UseCase 중심의 명확한 비즈니스 로직 표현
- 🔌 **의존성 역전**: Port/Adapter 패턴으로 결합도 최소화
- 🧪 **테스트 용이성**: Mock 객체를 통한 독립적인 계층별 테스트 가능

<br/>


---

## 🏗️ 기술 스택

### Backend Core
- **Language**: Java 25
- **Framework**: Spring Boot 4.0.0
- **Build Tool**: Gradle 9.2.1 (Multi-module)
- **ORM**: Spring Data JPA, Hibernate 7.1.8
- **Query DSL**: Querydsl 5.1.0 (Type-safe query)

### Batch & Scheduling
- **Spring Batch**: 6.0.0 (청크 기반 대용량 처리)
- **Spring Quartz**: 스케줄링 관리
- **WebFlux**: 비동기 HTTP 클라이언트 (외부 API 호출)

### Database & Infrastructure
- **RDBMS**: PostgreSQL (AWS RDS)
- **Connection Pool**: HikariCP (최적화된 설정)

### DevOps & Monitoring
- **Container**: Docker
- **Monitoring**: Spring Actuator
- **Notification**: Slack Webhook

<br/>


---


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


--- 


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
- 데이터베이스 연결 상태 확인

---

## 📦 아키텍처 구조

### 1. 헥사고날 아키텍처 (Ports & Adapters)

```
┌─────────────────────────────────────────────────────────────┐
│                    ADAPTERS (외부 세계)                       │
├─────────────────────────────────────────────────────────────┤
│  app-api          │  app-batch      │  infrastructure-jpa   │
│  (REST API)       │  (Batch Job)    │  (JPA Repository)     │
└──────────┬────────┴────────┬────────┴─────────┬─────────────┘
           │                 │                  │
           │  Inbound Port   │                  │ Outbound Port
           ▼                 ▼                  ▼
┌─────────────────────────────────────────────────────────────┐
│                    APPLICATION (비즈니스 로직)                 │
├─────────────────────────────────────────────────────────────┤
│  ┌─── search/              ┌─── save/                       │
│  │    port/in (UseCase)    │    port/in (UseCase)           │
│  │    port/out (Port)      │    port/out (Port)             │
│  │    service/             │    service/                    │
│  └─────────────────────────└───────────────────────────     │
└──────────┬──────────────────────────────────────────────────┘
           │
           ▼
┌─────────────────────────────────────────────────────────────┐
│                    DOMAIN (핵심 도메인)                        │
├─────────────────────────────────────────────────────────────┤
│  Post, Publisher (순수 Java, 프레임워크 독립적)                   │
└─────────────────────────────────────────────────────────────┘
```

<br/>

### 2. 멀티모듈 구조 및 의존성

```
techpost-be/
│
├── 📱 app-api (REST API 어댑터)
│   ├── POST /post (게시물 검색)
│   ├── UseCase 의존 (인터페이스)
│   └── Port: 18080
│
├── ⚙️ app-batch (배치 어댑터)
│   ├── PostScrapJob (테크 블로그 스크래핑)
│   ├── Scheduler (Cron: 매일 07:00)
│   └── Port: 18020
│
├── 🎯 application (비즈니스 로직)
│   ├── search/ (검색 UseCase)
│   │   ├── port/in/  PostSearchUseCase
│   │   ├── port/out/ PostSearchPort
│   │   └── service/  PostSearchService
│   └── save/ (저장 UseCase)
│       ├── port/in/  PostSaveUseCase
│       ├── port/out/ PostSavePort
│       └── service/  PostSaveService
│
├── 🏛️ infrastructure-jpa (영속성 어댑터)
│   ├── adapter/search/ PostSearchPersistenceAdapter
│   ├── adapter/save/   PostSavePersistenceAdapter
│   ├── entity/         PostJpaEntity
│   ├── repository/     PostJpaRepository
│   └── mapper/         PostEntityMapper
│
├── 💎 domain (핵심 도메인)
│   └── post/model/
│       ├── Post (도메인 모델)
│       ├── PostId (Value Object)
│       └── Publisher (Enum)
│
├── 🔔 notification (알림)
│   └── Slack Webhook 클라이언트
│
└── 🛠️ common (공통 유틸리티)
    └── Response, Exception, Utils
  - Spring Batch, Quartz, WebFlux
  - Jackson (JSON 처리)
  - H2 Database (테스트용)
```
--- 

## 🔗 모듈 간 의존 관계

```
app-api → domain, common
app-batch → domain, common, notification
domain → common
notification → common
common → (의존 없음)
```
---
