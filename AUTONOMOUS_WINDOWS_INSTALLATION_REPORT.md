# QUREVO — Autonomous Windows Server Manager Installation Report

---

## 1. Mode A — Dedicated Server PC Mode Verification
- **Command:** `./gradlew :tools:server-manager:run --args="MODE_A"`
- **Log Output:**
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
- **Result:** **PASS**.

---

## 2. Mode B — Reception PC Server Mode Verification
- **Command:** `./gradlew :tools:server-manager:run --args="MODE_B"`
- **Log Output:**
  - Local Ktor server and Reception Web App initialized on single Reception PC.
  - Access verified at `http://localhost:27800/reception`.
- **Result:** **PASS**.
