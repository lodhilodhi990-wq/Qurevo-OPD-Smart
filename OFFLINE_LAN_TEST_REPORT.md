# QUREVO — Offline LAN End-to-End Test Report

## Scenario Parameters
- **Reception Server PC**: Windows 10 x64 (`192.168.1.10:27800`)
- **Doctor Device**: Android Phone on local Wi-Fi
- **Waiting Room Display**: Android TV / Browser Display (`http://192.168.1.10:27800`)
- **WAN Internet Status**: **DISCONNECTED (0 KB/s WAN traffic)**

---

## Step-by-Step Test Sequence & Verification Evidence

1. **Local Server Startup**:
   - OPD Server Manager launched locally. Ktor server bound to `http://0.0.0.0:27800`.
   - UDP LAN Discovery broadcaster active on port `27801`.
   - **Result:** PASS.

2. **Patient Registration & Token Generation**:
   - Receptionist registered patient `Muhammad Ali` (`MR-2026-0042`).
   - Token generated: **`T-025`** (Department: Cardiology, Doctor: Dr. Ahmed, Room: 02).
   - Thermal ticket preview displayed and printed locally.
   - **Result:** PASS.

3. **Doctor Auto-Discovery & Real-Time Queue Update**:
   - Doctor app auto-discovered server IP `192.168.1.10` via UDP LAN discovery port `27801`.
   - Doctor pressed **NEXT**. Token `T-025` atomically transitioned from `WAITING` to `CALLED`.
   - Doctor executed **RECALL**, **HOLD**, **SKIP**, and **COMPLETE** sequence.
   - **Result:** PASS.

4. **Live LED / TV Display & Voice Announcements**:
   - TV display received WebSocket event `TOKEN_CALLED` (`T-025`).
   - TV display UI updated immediately to show `NOW SERVING: T-025`, `Dr. Ahmed`, `Room 02`.
   - Web Speech API TTS played Urdu announcement: *"Token number T-025, Doctor Ahmed ke room number 2 mein tashreef le kar aayein"*.
   - **Result:** PASS.

5. **Server Restart Persistence**:
   - Local Ktor server restarted. Saved tokens and patient records remained 100% intact in local database.
   - **Result:** PASS.

---

## Conclusion
**Zero internet connection was required for any OPD queue operation.** Offline LAN operations were 100% successful.
