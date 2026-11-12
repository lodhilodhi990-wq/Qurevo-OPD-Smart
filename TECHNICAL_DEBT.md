# QUREVO — Technical Debt & Optimization Report

---

## 1. Low-RAM Hardware Optimization (8 GB RAM PC)
- **Status**: Excellent.
- Ktor backend runs on JVM with `-Xmx2048m` memory cap.
- Single local PostgreSQL / H2 database instance prevents RAM bloat.
- No heavy Kubernetes or microservices stack required.

---

## 2. Code Maintainability
- Ktor routes are cleanly modularized (`AuthRoutes`, `PatientRoutes`, `TokenRoutes`, `SaaSAdminRoutes`).
- Android Compose screens follow single-responsibility layout principles and integrate with shared design system components.
