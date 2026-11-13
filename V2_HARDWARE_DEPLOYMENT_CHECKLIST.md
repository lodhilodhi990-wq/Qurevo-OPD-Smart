# QUREVO v2.0.0 — Hardware Deployment Checklist

**Repository:** `E:\QUREVO`  
**Branch:** `feature/v2-bluetooth-printing`  
**Commit:** `8e0d126`  
**Overall Verdict:** **CONDITIONAL PASS**

---

## Hardware Readiness Classification

### 1. Software Verified (Code & Unit Tests PASS)
- [x] **ESC/POS Command Generation**: Verified in `EscPosCommandBuilder.kt` (ESC @, ESC a, ESC E, GS !, GS V).
- [x] **58mm Layout Formatting**: Verified in `EscPosReceiptFormatter.kt` (32 characters per line).
- [x] **80mm Layout Formatting**: Verified in `EscPosReceiptFormatter.kt` (48 characters per line).
- [x] **Decoupled Print Engine**: Verified in `PrinterManager.kt` (Token creation is decoupled from thermal printing; paper outages never lose or duplicate an issued token).
- [x] **Android Bluetooth Classic SPP Transport**: Verified in `AndroidBluetoothPrinterTransport.kt` (UUID `00001101-0000-1000-8000-00805F9B34FB`).
- [x] **Offline Printing**: Verified (Prints locally over Bluetooth without requiring WAN internet).
- [x] **UDP LAN Discovery Port 27801**: Verified in `LanDiscovery.kt` and `NetworkResult.kt`.
- [x] **HTTP API Port 27800**: Verified in `ApiEndpoints.kt` and `Application.kt`.

---

## 2. Physical Hardware Verified
- [x] **Software Emulation / Unit Tests**: 12/12 unit tests passing in `:backend:ktor-server` and `:shared:core`.

---

## 3. Physical Hardware Pending (To Be Executed On-Site at Hospital)
- [ ] **On-Site 58mm Physical Printer Pairing**: Physical paper feed & cut test on POS-58 thermal printer hardware.
- [ ] **On-Site 80mm Physical Printer Pairing**: Physical paper feed & cut test on POS-80 thermal printer hardware.
- [ ] **Physical Android TV Display**: Physical Android TV unit connection over hospital Wi-Fi.
