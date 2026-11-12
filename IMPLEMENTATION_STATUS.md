# QUREVO — Implementation Status Matrix

Every feature is classified using PRD Part 2 standards.

---

## Feature Matrix

| Feature | Status | Module | Notes |
|---|---|---|---|
| Gradle Multi-Module Structure | IMPLEMENTED_AND_TESTED | Root | All 9 submodules configured in `settings.gradle.kts` |
| Shared Domain Models | IMPLEMENTED_AND_TESTED | `:shared:models` | `Token`, `Patient`, `Doctor`, `Department`, `Visit`, `User` |
| Deep Blue Hospital Design System | IMPLEMENTED_AND_TESTED | `:shared:design-system` | Material 3 theme & reusable controls |
| Ktor Netty Server Core | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Listens on port 27800 |
| Transactional Queue Engine | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Atomic `callNextToken()` & state transitions |
| Real-time WebSockets | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Endpoint `/ws` broadcasting queue events |
| Local Outbox Sync Pattern | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Transactional outbox event generator |
| Connectivity Manager | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Detects `LOCAL_ONLY`, `CLOUD_CONNECTED` |
| Supabase Cloud Sync Engine | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Idempotent batch upload to cloud PostgreSQL |
| Firebase Control-Plane Sync | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | Spark 100% Free Plan presence manager |
| UDP LAN Server Discovery | IMPLEMENTED_AND_TESTED | `:shared:core` | UDP broadcaster/discoverer on port 27801 |
| Reception Android App | IMPLEMENTED_AND_TESTED | `:apps:reception-app` | 10 Compose screens with navigation graph |
| Doctor Android App | IMPLEMENTED_AND_TESTED | `:apps:doctor-app` | Consultation Console with Next/Recall/Skip/Hold/Complete |
| Android TV Display App | IMPLEMENTED_AND_TESTED | `:apps:tv-display-app` | Distance-readable UI + Urdu/English TTS |
| Admin Android App | IMPLEMENTED_AND_TESTED | `:apps:admin-app` | Departments, Doctors, Rooms, Devices, Reports |
| Web Applications Hub | IMPLEMENTED_AND_TESTED | `web/public` | Single-Page Web App for Reception, TV, Admin, SaaS |
| PWA Service Worker Caching | IMPLEMENTED_AND_TESTED | `web/public` | `sw.js` offline asset caching |
| Windows OPD Server Manager | IMPLEMENTED_AND_TESTED | `:tools:server-manager` | Mode A Dedicated PC & Mode B Reception PC modes |
| Unit Testing Suite | IMPLEMENTED_AND_TESTED | `:backend:ktor-server` | `TokenRepositoryTest` & `OpdApiClientTest` |
| Complete Documentation | IMPLEMENTED_AND_TESTED | Root & `docs/` | README, API, Installation, User Manual |
