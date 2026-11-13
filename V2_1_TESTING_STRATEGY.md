# QUREVO v2.1.0 — Testing & QA Strategy

---

## 1. Test Categories & Verification Suites

### Unit Tests (`:backend:ktor-server` & `:shared:core`)
- Device pairing PIN validation tests.
- Exponential backoff calculation tests (`2s`, `4s`, `8s`, `16s`, `32s`).
- AES-256 backup encryption and decryption integrity tests.

### Integration Tests
- Device revocation WebSocket disconnect test.
- Extended server health metrics API response test.
- Database snapshot creation and restore wizard verification test.

### Regression Testing
- Verify all v1.0.0 and v2.0.0 features (offline token issuance, ESC/POS thermal printing, queue priorities, SMS routes) continue passing 100%.
