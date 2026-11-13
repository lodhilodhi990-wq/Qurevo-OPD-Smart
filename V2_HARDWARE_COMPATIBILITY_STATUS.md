# QUREVO v2.0.0 — Hardware Compatibility Status Report

---

## 1. ESC/POS Thermal Printer Compatibility Matrix

| Hardware Category | Printer Model / Specification | Transport Protocol | Paper Width | Verification Status |
|---|---|---|---|---|
| **58mm Bluetooth Printers** | POS-58 Series (Zjiang, Xprinter) | Bluetooth Classic SPP | 58mm (32 Chars) | Software Verified / Physical Pairing Pending On-Site |
| **80mm Bluetooth Printers** | Xprinter XP-N160I, POS-80 | Bluetooth Classic SPP | 80mm (48 Chars) | Software Verified / Physical Pairing Pending On-Site |
| **USB Thermal Printers** | Epson TM-T20II / TM-T82 | Windows Spooler / Raw | 80mm (48 Chars) | Software Verified / Physical Driver Pending On-Site |

---

## 2. On-Site Physical Hardware Setup Checklist
1. Pair Bluetooth thermal printer in Android device settings.
2. Select printer in Reception App settings screen ([PrinterSettingsScreen.kt](file:///E:/QUREVO/apps/reception-app/src/main/java/com/opd/token/reception/ui/PrinterSettingsScreen.kt)).
3. Execute Test Print to verify physical paper feed and cutter.
