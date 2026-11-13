# QUREVO — Version 2.0.0 Release Notes (Smart OPD)

**Release Target:** `v2.0.0`  
**License & Cost Policy:** 100% Free & Open Source Technology Edition  
**Repository:** [https://github.com/lodhilodhi990-wq/Qurevo-OPD-Smart.git](https://github.com/lodhilodhi990-wq/Qurevo-OPD-Smart.git)

---

## 🚀 Key Highlights in Version 2.0.0

### 1. Milestone M1 — ESC/POS Direct Thermal Printing Subsystem
- [EscPosCommandBuilder.kt](file:///E:/QUREVO/shared/core/src/main/kotlin/com/opd/token/shared/core/printer/EscPosCommandBuilder.kt): Raw byte array generator for standard ESC/POS command sequences.
- [EscPosReceiptFormatter.kt](file:///E:/QUREVO/shared/core/src/main/kotlin/com/opd/token/shared/core/printer/EscPosReceiptFormatter.kt): Layout formatter supporting 58mm (32 chars) and 80mm (48 chars) thermal receipt dimensions.
- [AndroidBluetoothPrinterTransport.kt](file:///E:/QUREVO/apps/reception-app/src/main/java/com/opd/token/reception/printer/AndroidBluetoothPrinterTransport.kt): Bluetooth Classic SPP socket transport (`00001101-0000-1000-8000-00805F9B34FB`) with auto-reconnect.

### 2. Milestone M2 — Advanced Queue Priority Engine
- Deterministic priority queue ordering: `EMERGENCY` > `URGENT` > `PRIORITY` > `NORMAL`.
- Priority elevation endpoint: `POST /api/v1/tokens/{id}/priority` with audit logs.
- Doctor queue UI priority badges.

### 3. Milestone M3 — Local SMS Gateway Adapter
- [SmsGatewayAdapter.kt](file:///E:/QUREVO/shared/core/src/main/kotlin/com/opd/token/shared/core/sms/SmsGatewayAdapter.kt): Adapter interface supporting local GSM modems and HTTP SMS gateways.
- SMS Config REST routes: `GET/POST /api/v1/sms/config`, `POST /api/v1/sms/test`.

### 4. Milestone M4 — Wait-Time Analytics Engine & QA Gate
- [WaitTimeCalculator.kt](file:///E:/QUREVO/shared/core/src/main/kotlin/com/opd/token/shared/core/analytics/WaitTimeCalculator.kt): Rolling consultation-duration average forecasting engine.
- Unit tests: `QueuePriorityTest.kt` and `WaitTimeAnalyticsTest.kt` (100% PASS).
