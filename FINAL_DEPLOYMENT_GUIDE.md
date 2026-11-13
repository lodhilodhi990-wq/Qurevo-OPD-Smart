# QUREVO — Final Deployment Guide

## Deployment Summary

QUREVO supports **Local Hospital LAN Deployment** (Offline-First) and **Hybrid Cloud SaaS Deployment** (Zero-Cost).

---

## 1. Local Hospital Server Deployment
1. Install Java OpenJDK 17 and PostgreSQL 14+ on the hospital server PC (Dedicated PC or Reception PC).
2. Execute OPD Server Manager:
   ```bash
   ./gradlew :tools:server-manager:run --args="MODE_B"
   ```
3. Connect Reception tablets, Doctor phones, and Android TVs to the hospital local Wi-Fi. Devices will auto-discover the server IP on UDP port 27801.

---

## 2. Web Applications Hosting
- Deploy `web/public` or `web-apps/` to Cloudflare Pages Free, Firebase Hosting Spark, or serve directly from the local Reception Server PC at `http://RECEPTION-IP:27800`.
