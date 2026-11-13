# QUREVO v2.0.0 — Final Deployment Blockers & Disclosures

---

## Disclosed On-Site Hardware Blockers

1. **Physical Bluetooth Printer Pairing**:
   - **Status**: Software transport code (`AndroidBluetoothPrinterTransport.kt`) and unit tests (`EscPosPrinterTest.kt`) are 100% complete and verified.
   - **Requirement**: Physical pairing of hospital Bluetooth thermal printer (`POS-58` / `POS-80`) with reception tablet using Android Settings on hospital arrival.

2. **Windows USB Thermal Driver Spooler**:
   - **Status**: Software model (`PrinterTransportType.WINDOWS_SPOOLER`) and documentation exist.
   - **Requirement**: Native Windows spooler driver installation if the hospital uses direct USB raw printing on Windows Reception PC instead of Bluetooth.

3. **Physical Android TV Unit**:
   - **Status**: Android TV App (`apps/tv-display-app`) and Web TV Display (`web-apps/tv-display-web`) are 100% complete with Urdu & English TextToSpeech.
   - **Requirement**: Connecting waiting area physical TV display to hospital local Wi-Fi router.
