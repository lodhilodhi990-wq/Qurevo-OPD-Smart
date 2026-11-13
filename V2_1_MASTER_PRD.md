# QUREVO v2.1.0 — Master Product Requirements Document (PRD)

**Product Name:** QUREVO  
**Target Release:** `v2.1.0`  
**Focus Area:** System Reliability, Device Security & Disaster Recovery  
**Baseline Commit:** `f3bc650`  
**Branch:** `feature/v2.1-reliability`  
**Cost Policy:** 100% Free / Open-Source Technology Edition

---

## 1. Executive Vision & Objectives

QUREVO `v2.1.0` strengthens the platform's production reliability, device security, and disaster recovery capabilities for local hospital deployments while strictly preserving offline-first LAN OPD operations.

---

## 2. Module Functional Requirements

### M1 — Automatic Database Backup & Restore
- Scheduled daily/hourly local database snapshots saved to `E:/QUREVO/backups/`.
- AES-256 backup encryption for patient record privacy.
- One-click Database Restore Wizard in OPD Server Manager with pre-restore safety snapshot.

### M2 — Hybrid Cloud Sync Reliability
- Outbox event retry engine with bounded exponential backoff (`2s`, `4s`, `8s`, `16s`, `32s` max).
- Dead-letter event queue for permanently failed events requiring manual admin review.
- Idempotency checks preventing duplicate token records in cloud.

### M3 — Hospital Server Health Monitoring
- Extended health metrics endpoint `GET /api/v1/health/extended` reporting CPU usage, RAM consumption, available disk space, active DB connections, and WebSocket client count.
- Server heartbeat monitor emitting alerts if disk space drops below 1 GB.

### M4 — Device Registration & Revocation
- Cryptographic device pairing (`DevicePairingRequest`, short-lived PIN / QR code).
- Real-time device revocation: revoked devices are immediately disconnected from WebSockets and API access.

### M5 — Low-RAM Optimization (8 GB Target)
- Fixed connection pool limits (`HikariCP` max 10 connections).
- Bounded coroutine channel buffers and Netty non-blocking IO.

### M6 — Audit Logs & Recovery Alerts
- Central system activity audit logging (`AuditEvent`).
- Instant alerts for failed sync batches or system recovery events.

### M7 — LAN Failure Recovery
- Automatic client reconnection over UDP LAN port 27801.
- State reconciliation upon server restart without duplicate token generation.

### M8 — Security & Tenant Isolation
- Strict server-side `X-Tenant-Id` header validation and JWT session token expiration.
- Public displays omit all patient personal details.
