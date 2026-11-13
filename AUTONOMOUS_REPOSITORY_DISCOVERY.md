# QUREVO — Autonomous Repository Discovery Report

**Timestamp:** March 2026  
**Execution Mode:** Autonomous Overnight Execution  
**Project Path:** `E:/QUREVO/`

---

## 1. Verified Module Inventory

1. **`shared/models`**: Kotlin JVM module containing serializable data classes (`Token`, `Patient`, `Doctor`, `Department`, `Visit`, `User`, `Room`, `WebSocketMessage`).
2. **`shared/core`**: Kotlin JVM module containing `OpdApiClient`, `OpdDatabase` Room cache models, `LanServerBroadcaster`, `LanServerDiscoverer`, and `AdminRepository`.
3. **`shared/design-system`**: Android library containing Material 3 Deep Blue hospital theme (`Theme.kt`, `Color.kt`, `Type.kt`) and reusable UI controls (`PrimaryButton`, `TokenCard`, `DashboardCard`, `StatusBadge`).
4. **`backend/ktor-server`**: Kotlin JVM Ktor server containing Netty engine, Exposed ORM schema (`ExposedSchema.kt`), Local Outbox pattern (`SyncOutboxTable.kt`, `OutboxRepository.kt`), `ConnectivityManager`, `SupabaseSyncEngine`, `FirebaseControlPlane`, WebSockets broadcast engine (`QueueWebSocket.kt`), and REST routes.
5. **`apps/reception-app`**: Android app (`com.opd.token.reception`) with 10 Compose screens, thermal ticket printing UI, navigation graph, and ViewModel.
6. **`apps/doctor-app`**: Android app (`com.opd.token.doctor`) with Doctor Consultation Console (**NEXT**, **RECALL**, **SKIP**, **HOLD**, **COMPLETE**, **EMERGENCY**), navigation, and ViewModel.
7. **`apps/tv-display-app`**: Android TV app (`com.opd.token.tv`) with distance-readable UI, WebSockets listener, and Android TextToSpeech voice announcements in Urdu and English.
8. **`apps/admin-app`**: Android app (`com.opd.token.admin`) with hospital administration screens (Departments, Doctors, Rooms, Devices, Reports).
9. **`apps/unified-app`**: Android app (`com.opd.token.unified`) with role-based navigation portal.
10. **`web-apps/` & `web/public/`**: Web applications hub hosting Web Reception, LED/TV Display, Hospital Admin, SaaS Super Admin, and PWA Service Worker (`sw.js`).
11. **`tools/server-manager`**: Windows OPD Server Manager launcher (`OpdServerManager.kt`) for Mode A (Dedicated PC) and Mode B (Reception PC) installations.

---

## 2. Environment & Configuration Verification
- **Gradle Version Catalog**: `gradle/libs.versions.toml` maps AGP 8.4.2, Kotlin 1.9.24, Compose BOM 2024.06.00, Hilt 2.51.1, Room 2.6.1, Ktor 2.3.11, PostgreSQL, Exposed 0.50.0.
- **Git Status**: Repository initialized on branch `main`, remote origin set to `https://github.com/lodhilodhi990-wq/Qurevo-OPD-Smart.git`, and pushed successfully.
