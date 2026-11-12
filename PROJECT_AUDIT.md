# QUREVO — Full Repository Audit Report

**Date:** March 2026  
**Project Path:** `E:/QUREVO/`  
**Architecture:** Offline-First Local Server + Hybrid Cloud Sync (100% Free Technology Edition)

---

## 1. Directory Tree & Module Structure

```text
E:/QUREVO/
├── build.gradle.kts                (Root Gradle Build Script)
├── settings.gradle.kts             (Multi-module Inclusion)
├── gradle.properties               (JVM Memory & AndroidX configuration)
├── gradle/
│   └── libs.versions.toml          (Centralized Version Catalog)
├── shared/
│   ├── models/                     (Shared Domain Data Classes: Token, Patient, Doctor, Department, Visit, User, etc.)
│   ├── core/                       (Ktor API Client, Room Cache, UDP LAN Discovery, Outbox Client & Tests)
│   └── design-system/              (Material 3 Deep Blue Hospital Theme & Reusable Compose UI Components)
├── backend/
│   └── ktor-server/                (Ktor Netty Server, Exposed ORM Schema, Local Outbox Pattern, WebSockets & SaaS APIs)
├── apps/
│   ├── reception-app/              (Receptionist Android Application - 10 Compose Screens)
│   ├── doctor-app/                 (Doctor Queue Control Android Application - 6 Compose Screens)
│   ├── tv-display-app/             (Android TV Display & Urdu/English Voice Announcement App)
│   └── admin-app/                  (Hospital Admin & Analytics Android Application)
├── web/
│   └── public/                     (Single-Page Web Hub: Web Reception, LED/TV Display, Hospital Admin, SaaS Super Admin, PWA sw.js)
├── tools/
│   └── server-manager/             (OPD Server Manager Windows Utility for Mode A Dedicated PC & Mode B Reception PC)
└── docs/
    ├── API-DOCUMENTATION.md        (REST & WebSockets API specification)
    ├── INSTALLATION.md             (Windows installation guide for Mode A & Mode B)
    └── USER-MANUAL.md              (User guide for Receptionists, Doctors, Admins, and SaaS Owners)
```

---

## 2. Module Analysis Summary

| Module | Language/Framework | Purpose | Status |
|---|---|---|---|
| `:shared:models` | Kotlin JVM | Common serializable domain classes | Complete |
| `:shared:core` | Kotlin JVM | Network client, Room models, LAN Discovery | Complete |
| `:shared:design-system` | Android / Compose | Material 3 UI theme and controls | Complete |
| `:backend:ktor-server` | Kotlin / Ktor Netty | Ktor Server, WebSockets, Outbox Sync | Complete |
| `:apps:reception-app` | Android Compose | Reception App with 10 Screens | Complete |
| `:apps:doctor-app` | Android Compose | Doctor App with Consultation Console | Complete |
| `:apps:tv-display-app` | Android TV Compose | TV Display App with TextToSpeech | Complete |
| `:apps:admin-app` | Android Compose | Hospital Admin Panel | Complete |
| `web/public` | HTML5 / JS / CSS | PWA Web Applications Hub | Complete |
| `:tools:server-manager` | Kotlin JVM | Windows Desktop Server Manager | Complete |
