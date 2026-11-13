# QUREVO — Version 1.0.0 Release Notes

**Release Date:** March 2026  
**Version:** `v1.0.0`  
**License & Cost Policy:** 100% Free & Open Source Technology Edition  
**Repository:** [https://github.com/lodhilodhi990-wq/Qurevo-OPD-Smart.git](https://github.com/lodhilodhi990-wq/Qurevo-OPD-Smart.git)

---

## 🚀 Key Highlights in Version 1.0.0

### 1. Offline-First Local Hospital Server
- **Reception Server Mode**: Receptionist PC acts as hospital server with zero internet required for patient registration, token generation (`T-001`, `T-002`), doctor queue controls, and waiting room LED displays.
- **Dedicated PC Mode**: Supports dedicated Windows server PC setups.
- **UDP LAN Auto-Discovery**: Client mobile and TV devices auto-discover hospital server IP on port 27801 over local Wi-Fi / Ethernet.

### 2. Hybrid Cloud Synchronization (Outbox Pattern)
- Transaction-safe local outbox records all token events during offline operation.
- `ConnectivityManager` detects internet restoration and `SupabaseSyncEngine` idempotently uploads batch outbox events to Supabase Cloud PostgreSQL.
- Firebase Spark Free Plan handles non-authoritative control-plane device presence and FCM notification metadata.

### 3. Applications Suite
- **Reception App** (`apps/reception-app`): Patient registration, token generation, thermal ticket printing.
- **Doctor App** (`apps/doctor-app`): Consultation Console with **NEXT**, **RECALL**, **SKIP**, **HOLD**, **COMPLETE**, and **EMERGENCY** call controls.
- **TV Display App** (`apps/tv-display-app`): Distance-readable UI with WebSockets listener and Android TextToSpeech engine for Urdu and English voice announcements.
- **Hospital Admin App** (`apps/admin-app`): Department, doctor, room, device, and analytics management.
- **Unified Role App** (`apps/unified-app`): Role-based master portal displaying role-specific workspaces upon login.
- **Web Applications Hub** (`web-apps/` & `web/public/`): Single-page web portals for VS Code web development and browser access.
- **Windows OPD Server Manager** (`tools/server-manager`): Windows launcher utility for server lifecycle, database health, and backup/restore.

### 4. Security & Privacy
- Strict multi-tenant isolation (`TenantContext.kt` checks `X-Tenant-Id` header; negative cross-tenant tests passed).
- Patient personal data strictly omitted from public LED TV display feeds.
- 100% zero-cost policy compliance (no mandatory paid APIs, VPS, or billing activation).
