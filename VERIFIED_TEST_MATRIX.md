# QUREVO — Verified Subproject Test Matrix

---

## 1. Backend Ktor Unit Tests (`:backend:ktor-server`)

**Command:** `./gradlew :backend:ktor-server:test`  
**Exit Code:** `0`  
**Test Results:** 4 Passed, 0 Failed, 0 Skipped

```text
com.opd.token.backend.TokenRepositoryTest > testGenerateToken_createsUniqueSequentialTokenNumber PASSED
com.opd.token.backend.TokenRepositoryTest > testGetNextWaitingToken_atomicallyCallsNextInQueue PASSED
com.opd.token.backend.TokenRepositoryTest > testUpdateTokenStatus_updatesStateCorrectly PASSED
com.opd.token.backend.TokenRepositoryTest > testPatientSearch_returnsMatchingPatients PASSED
```

---

## 2. Shared Core Unit Tests (`:shared:core`)

**Command:** `./gradlew :shared:core:test`  
**Exit Code:** `0`  
**Test Results:** 2 Passed, 0 Failed, 0 Skipped

```text
com.opd.token.shared.core.OpdApiClientTest > testApiEndpoints_constantsAreValid PASSED
com.opd.token.shared.core.OpdApiClientTest > testClientInitialization_setsHostAndPort PASSED
```

---

## 3. Web & PWA Asset Validation (`web/public`)

**Files Validated:** `index.html`, `styles.css`, `app.js`, `sw.js`  
**Status:** PASS (PWA Service Worker registered, Web Speech API TTS initialized).
