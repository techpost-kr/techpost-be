# 프로젝트 모듈 구조

## 1. app-api
- **역할**: 외부 클라이언트와의 인터페이스 담당. 클라이언트 요청을 처리하고 결과를 반환.
- **주요 기능**: 
  - API 엔드포인트 제공 (RESTful API).
  - `domain` 모듈을 이용해 비즈니스 로직 처리.
  - Controller 계층 포함.

- **의존 관계**:
  - `domain` 모듈에 의존.
  - `common` 모듈에 의존 (필요한 공통 로직 활용).

---

## 2. app-batch
- **역할**: 배치 작업을 수행하는 모듈. 대량의 데이터를 처리하고, 주기적으로 작업을 자동화.
- **주요 기능**: 
  - 스프링 배치를 이용해 테크 블로그 스크랩 작업 등 배치 처리.
  - 배치 잡 실행 및 관리.

- **의존 관계**:
  - `domain` 모듈에 의존 (데이터 처리 및 엔티티 관련 작업).
  - `common` 모듈에 의존 (공통 유틸 및 설정 활용).

---

## 3. domain
- **역할**: 핵심 비즈니스 로직과 데이터 모델을 담당하는 모듈.
- **주요 기능**:
  - 엔티티 클래스 및 JPA 관련 설정.
  - 비즈니스 로직 처리를 위한 Service 계층.
  - 데이터베이스 접근을 위한 Repository 계층.

- **의존 관계**:
  - **의존하지 않음**: 자체적으로 독립적인 비즈니스 로직과 데이터 모델을 제공.

---

## 4. common
- **역할**: 여러 모듈에서 공통적으로 사용하는 유틸리티, 설정 등을 포함한 모듈.
- **주요 기능**: 
  - 공통 유틸리티 클래스 (`DateUtils`, 공통 상수 등).
  - 공통적인 설정이나 프로퍼티 파일 관리.

- **의존 관계**:
  - **의존하지 않음**: 다른 모듈들이 `common` 모듈을 의존.

---

# 모듈 간 의존 관계 요약

- `app-api` → `domain`, `common`
- `app-batch` → `domain`, `common`
- `domain` → 의존 없음
- `common` → 의존 없음