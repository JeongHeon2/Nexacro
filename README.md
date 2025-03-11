# Nexacro

📦 Nexacro 기반 ERP 물류 시스템

📌 프로젝트 개요

이 프로젝트는 ERP 물류 시스템을 구현한 프로젝트로, 프론트엔드는 Nexacro, 백엔드는 Spring Boot + JPA + MyBatis, 그리고 데이터베이스는 Oracle을 사용하여 개발되었습니다.

이 시스템은 견적 → 수주 → 생산 → 재고관리 → 납품/출고의 흐름을 기반으로 작동하며, 자재 소요량 계획(MRP) 기능을 포함하여 물류 및 재고 관리 프로세스를 체계적으로 처리합니다.

📌 기술 스택

📌 프론트엔드 (F/E)
Framework: Nexacro

IDE: Nexacro Studio

주요 기능: UI 설계, 데이터 바인딩, 이벤트 핸들링

📌 백엔드 (B/E)
Framework: Spring Boot

ORM: JPA, MyBatis

IDE: IntelliJ

API 설계: RESTful API 기반 개발

Session 관리: 커스텀 백엔드 API 활용

📌 데이터베이스 (DB)
DBMS: Oracle

DB Tool: SQLGate for Oracle


🔥 주요 기능

✅ 로그인 기능
백엔드: Spring Boot + MyBatis를 활용하여 로그인 API 구현

프론트엔드: Nexacro에서 로그인 UI 구성 및 API 연동

세션 관리: 로그인 상태 유지 (true/false 조건 활용)

✅ 회원 관리 (CRUD)
회원가입: JPA를 활용한 사용자 정보 저장

회원 조회: MyBatis 및 JPA를 활용한 사용자 정보 조회 API 구현

회원 수정: PUT 요청을 통한 사용자 정보 업데이트

회원 삭제: DELETE 요청을 통한 사용자 계정 삭제

✅ 물류 시스템 기능

ERP 기반 물류 데이터 관리

주문 및 출고 프로세스 구현

재고 관리 및 데이터 조회

Oracle DB 연동 및 SQL 최적화

✅ 자재 소요량 계획 (MRP)
MPS(주생산계획) 기반 자재 필요량 계산

모의 전개를 통해 자재 투입 시뮬레이션

실시간 원자재 및 부품 발주 관리

✅ 작업 지시 및 생산 관리
제품 생산을 위한 작업 지시 생성

생산 진행 상태 조회 및 공정 성공률 확인

생산 완료 후 제품을 완제품 재고로 이동

✅ 재고 관리 및 출고 시스템
실시간 재고 조회 및 입출고 기록 관리

출고 및 납품 프로세스 자동화

출고 수량과 수주 수량 동기화하여 재고 관리 최적화

✅ DTO 변환 자동화
MapStruct`를 활용하여 Entity ↔ DTO 변환 자동화

`@Mapper`를 이용한 코드 자동 생성 및 유지보수 편의성 향상

API 응답 성능 개선 및 일관된 데이터 변환 처리



🔗 시스템 흐름

1️⃣ 견적 → 수주

고객이 제품을 주문하면 수주를 생성

재고 상태를 확인하여 충분할 경우 즉시 납품, 부족할 경우 생산 계획 수립

2️⃣ 생산 계획 및 MRP(Material Requirements Planning)
MPS(주생산계획) 수립 후 MRP를 통해 원자재 및 부품 필요 수량 산출

소요량 취합 후 발주 생성 → 입고 예정 재고로 이동

입고된 원자재는 투입 예정 재고로 전환

3️⃣ 작업 지시 및 생산
작업지시를 통해 생산 라인 가동

생산된 제품은 완제품 재고로 이동

공정 성공률 확인 후 납품 프로세스 진행

4️⃣ 재고 관리 및 납품/출고
완제품 재고 상태 업데이트

대량 주문은 납품, 소량 주문은 출고로 구분하여 처리

출고 완료 시 수주 데이터와 동기화
