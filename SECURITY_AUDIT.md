# QUREVO — Security & Privacy Audit Report

---

## 1. Zero-Cost & Billing Safety Audit
- **Verification**: Passed.
- Zero paid VPS, zero paid domain, zero paid APIs.
- Firebase configured on 100% Spark Free Plan (Auth, FCM, Firestore metadata). No automatic credit card or Blaze billing activation.
- Supabase configured on Free tier PostgreSQL.

---

## 2. Multi-Tenant Data Isolation Audit
- **Verification**: Passed.
- All database records (`Tokens`, `Patients`, `Visits`, `Users`, `Devices`) contain `tenant_id` and `hospital_id` fields.
- `TenantContext.kt` middleware verifies `X-Tenant-Id` header on backend requests.

---

## 3. Patient Privacy Audit
- **Verification**: Passed.
- Public LED / TV Web Display and Android TV app show ONLY token number (e.g. `T-024`), Doctor name, and Room number.
- Patient names, MR numbers, phone numbers, and diagnoses are strictly excluded from public displays.
