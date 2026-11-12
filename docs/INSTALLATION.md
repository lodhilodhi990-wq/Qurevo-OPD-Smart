# OPD Smart Token Platform — Installation & Setup Guide

## System Requirements

- **OS**: Windows 10/11 or Windows Server
- **RAM**: 4 GB minimum (8 GB recommended)
- **Runtime**: Java OpenJDK 17 or higher
- **Database**: PostgreSQL 14+ (or built-in H2 fallback)
- **Network**: Local Area Network (Wi-Fi or Ethernet router)

---

## Installation Modes

### Mode A — Dedicated Server PC Mode
Use this mode if the hospital has a dedicated computer for server operations.

1. Install OpenJDK 17 and PostgreSQL on the dedicated server PC.
2. Launch `OPD Server Manager`:
   ```bash
   ./gradlew :tools:server-manager:run --args="MODE_A"
   ```
3. Server will initialize database schema and start listening on port 27800.
4. UDP LAN discovery will broadcast `OPD_SERVER_DISCOVERY` packets on port 27801.

---

### Mode B — Reception PC Server Mode
Use this mode if the hospital does not have an extra dedicated server PC.

1. Run `OPD Server Manager` directly on the Reception PC:
   ```bash
   ./gradlew :tools:server-manager:run --args="MODE_B"
   ```
2. Receptionists access Web Reception locally via `http://localhost:27800` or the Android Reception App over local Wi-Fi.

---

## Device Pairing Instructions

1. Connect Reception tablets, Doctor phones, and Android TVs to the hospital Wi-Fi network.
2. Open the app on the client device.
3. Tap **Auto Discover Server**. The app will detect the server IP over UDP LAN port 27801 automatically.
