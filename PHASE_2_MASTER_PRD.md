# QUREVO — Phase 2 Master Product Requirements Document (PRD)

**Target Release:** `v2.0.0`  
**Focus Area:** Hardware Driver Integration, Advanced Queue Priorities, Local SMS Gateway, Extended Analytics

---

## 1. Feature Specifications

### 1.1 Direct ESC/POS Bluetooth Thermal Printing Driver
- Implement raw socket ESC/POS protocol client in `shared:core`.
- Support 58mm and 80mm thermal receipt printers directly from Android tablets over Bluetooth without third-party apps.

### 1.2 Advanced Queue Priority Management
- Allow reception and doctors to elevate tokens to `EMERGENCY_PRIORITY`.
- Support manual drag-and-drop token reordering in doctor console.

### 1.3 Local SMS Gateway Adapter
- Configurable SMS gateway adapter allowing hospitals to send automated token call SMS alerts to patients via connected GSM modem or local API provider.

### 1.4 Wait Time Analytics Engine
- Algorithmic wait-time forecasting based on doctor's rolling average consultation duration.
