# user-service

아카이브 관련 API를 처리하는 마이크로서비스입니다.

이 서비스는 아카이브 CRUD 기능을 제공하며,  
Spring Cloud 기반 마이크로서비스 아키텍처에서 Eureka 서비스 디스커버리와 통합되어 동작합니다.

---

## 주요 기능

- Create
- Read
- Update
- Delete

---

## 기술 스택

- Java 21+
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client

---

## 연동 서비스

- [api-gateway]를 통해 외부에서 접근
- [discovery-service]에 Eureka Client로 등록되어 서비스 간 호출 가능
- [user-service]와 내부 통신

---

