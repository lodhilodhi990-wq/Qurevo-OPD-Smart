# QUREVO v2.1.0 — Risk Register

---

## Risk Assessment & Mitigation Matrix

| Risk ID | Category | Description | Mitigation Strategy | Severity |
|---|---|---|---|---|
| **RISK-01** | Database | Data corruption during database restore process | Automatic pre-restore snapshot creation before applying restore | HIGH |
| **RISK-02** | Network | High network latency causing exponential backoff queue buildup | Bounded outbox memory limits and dead-letter queue isolation | MEDIUM |
| **RISK-03** | Security | Stolen device credentials accessing hospital APIs | Instant remote device revocation and short-lived JWT session tokens | HIGH |
| **RISK-04** | Resource | JVM heap expansion on 8 GB RAM Reception PC | Strict `-Xmx2048m` cap and HikariCP connection pool bounds | LOW |
