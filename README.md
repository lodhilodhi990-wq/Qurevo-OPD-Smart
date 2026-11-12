# OPD Smart Token & Hospital Management Platform
## Complete A–Z Hospital OPD Queue Platform — 100% Free Technology Edition

OPD Smart Token System is an offline-first, multi-hospital queue management system built with Kotlin, Jetpack Compose, Ktor, PostgreSQL, WebSockets, and Web Speech API TextToSpeech.

---

## 🌟 Key Features

- **Reception Registration & Token Generation**: Fast patient search, MR registration, and thermal receipt printing.
- **Doctor Consultation Console**: Queue control with **NEXT**, **RECALL**, **HOLD**, **SKIP**, **COMPLETE**, and **EMERGENCY** call buttons.
- **LED / TV Display & Voice Announcements**: Real-time waiting room display with Urdu (*"Token number T-025, Doctor Ahmed ke room number 2 mein tashreef le kar aayein"*) and English voice announcements.
- **Offline LAN Operation**: System operates over local hospital network (LAN) without internet access.
- **UDP Server Auto-Discovery**: Automatic discovery of hospital server over local Wi-Fi / Ethernet.
- **Multi-Tenant SaaS Support**: Super Admin portal to manage multiple hospitals, plans, and manual JazzCash/Easypaisa/bank transfer verification.
- **Windows OPD Server Manager**: Utility for Mode A (Dedicated PC) and Mode B (Reception PC) host installations.

---

## 📂 Project Architecture & Modules

- **`shared:models`**: Domain data models (`Token`, `Patient`, `Doctor`, `Department`, `Visit`, `User`, `Room`, `WebSocketMessage`).
- **`shared:core`**: Ktor API Client, Room Cache models, and UDP LAN Discovery.
- **`shared:design-system`**: Material 3 hospital theme & reusable Compose components.
- **`backend:ktor-server`**: Ktor Netty backend server, PostgreSQL Exposed ORM, REST APIs & WebSockets broadcast engine.
- **`apps:reception-app`**: Android Reception application.
- **`apps:doctor-app`**: Android Doctor application.
- **`apps:tv-display-app`**: Android TV Display application with TextToSpeech.
- **`apps:admin-app`**: Android Hospital Admin application.
- **`web/public`**: Single-Page Web Application (Web Reception, LED/TV Web Display, Hospital Admin, SaaS Super Admin).
- **`tools:server-manager`**: Windows OPD Server Manager launcher.

---

## 🚀 Quick Start Guide

### 1. Run Ktor Backend Server
```bash
./gradlew :backend:ktor-server:run
```
Server runs at `http://localhost:27800` with WebSocket endpoint at `ws://localhost:27800/ws`.

### 2. Run OPD Server Manager (Windows)
```bash
./gradlew :tools:server-manager:run
```

### 3. Open Web Frontend
Open `web/public/index.html` in any browser or navigate to `http://localhost:27800`.

---

## 📄 Documentation
- [API Documentation](file:///E:/QUREVO/docs/API-DOCUMENTATION.md)
- [Installation Guide](file:///E:/QUREVO/docs/INSTALLATION.md)
- [User Manual](file:///E:/QUREVO/docs/USER-MANUAL.md)

---

## 🛡️ Licence & Cost Policy
100% Free & Open-Source Technology Edition. Zero software or cloud subscription fees required.
