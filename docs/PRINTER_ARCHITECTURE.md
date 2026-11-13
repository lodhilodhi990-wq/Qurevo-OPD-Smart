# Milestone 1 — ESC/POS Thermal Printing Architecture

**Target Release:** v2.0.0  
**Milestone:** M1 — ESC/POS Direct Thermal Printing  
**Branch:** `feature/v2-bluetooth-printing`

---

## Architecture Overview

The thermal printing subsystem is designed with strict decoupling between token generation and physical receipt printing:

```text
[ Receptionist Request ]
          │
          ▼
[ Ktor Token Engine / Local Database ] (Token Generated & Saved Atomically)
          │
          ▼
[ PrinterManager ] ──> [ EscPosReceiptFormatter ] ──> [ EscPosCommandBuilder ]
          │                                                    │
          ▼                                                    ▼
[ PrintJob Engine ]                                 [ Raw ESC/POS Bytes ]
 (QUEUED -> PRINTING)                                          │
          │                                                    │
          ├──────────────────────────┬─────────────────────────┤
          ▼                          ▼                         ▼
[ Android Bluetooth Transport ] [ Windows Spooler ] [ Web Raw / Dialog ]
 (Bluetooth SPP Socket)
```

---

## Key Design Principles
1. **Decoupled Lifecycle**: Generating an OPD token saves the record to the local database immediately. Printing operates as a separate job. If a printer is offline or out of paper, the token is **NEVER** lost or duplicated.
2. **Platform-Independent Command Builder**: `EscPosCommandBuilder.kt` and `EscPosReceiptFormatter.kt` reside in `shared:core` using pure Kotlin JVM, without Android Bluetooth dependencies.
3. **Isolated Android Transport**: `AndroidBluetoothPrinterTransport.kt` handles Android Bluetooth Classic SPP sockets (`00001101-0000-1000-8000-00805F9B34FB`) securely within `apps/reception-app`.
