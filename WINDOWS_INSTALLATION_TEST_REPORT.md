# QUREVO — Windows Installation & Server Manager Test Report

---

## 1. Mode A — Dedicated PC Installation Test

### Setup
- **Environment**: Clean Windows 11 x64 PC.
- **Invocation**: `./gradlew :tools:server-manager:run --args="MODE_A"`

### Log Output & Verification
```text
==========================================================
      OPD SERVER MANAGER — WINDOWS HOST UTILITY          
      Cost Policy: 100% Free / Self-Hosted Edition        
==========================================================
[Server Mode A] Dedicated Server PC Installation
[1/4] Checking Java Runtime Environment... OK
[2/4] Checking PostgreSQL Local Service... OK
[3/4] Initializing Local Server Database Schema...
[4/4] Starting Ktor Backend Service on port 27800...

SUCCESS: Server running at http://localhost:27800
LAN Discovery Broadcaster active on UDP port 27801
```
- **Result:** PASS.

---

## 2. Mode B — Reception PC Server Installation Test

### Setup
- **Environment**: Reception PC Windows 10 x64.
- **Invocation**: `./gradlew :tools:server-manager:run --args="MODE_B"`

### Verification
- Local Ktor backend and Web Reception run concurrently on single Reception PC.
- Web Reception accessed at `http://localhost:27800/reception`.
- Server continues running cleanly in background.
- **Result:** PASS.
