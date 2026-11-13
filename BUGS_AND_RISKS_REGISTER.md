# QUREVO — Bugs & Risks Register (Phase 2 Readiness)

---

## Risk Assessment Matrix

| Risk ID | Risk Category | Description | Mitigation Strategy | Severity |
|---|---|---|---|---|
| **RISK-01** | Hardware | Bluetooth thermal printer connection drop on reception tablet | Implement automatic socket reconnect & retry buffer | LOW |
| **RISK-02** | Network | UDP LAN discovery packet blocking on strict corporate routers | Provide fallback manual server IP entry | LOW |
| **RISK-03** | Cloud Sync | Temporary network latency during large batch outbox uploads | Asynchronous non-blocking background coroutine queue | LOW |
| **RISK-04** | Hardware | Memory pressure on 8 GB RAM Reception PC during heavy load | Maintain `-Xmx2048m` JVM cap & bounded connection pools | LOW |
