# QUREVO v2.1.0 — Milestone M1 Disaster Recovery Guide

---

## Database Restoration Procedure

In the event of database corruption or server failure:

### 1. Identify Latest Healthy Snapshot
Query active backup metadata via REST API or OPD Server Manager:
```http
GET /api/v1/backups
```

### 2. Verify Snapshot Integrity
Run pre-restore integrity verification:
```http
POST /api/v1/backups/verify
Content-Type: application/json

{
  "backupId": "backup-uuid-here",
  "keyHex": "your-256-bit-encryption-key-hex"
}
```

### 3. Execute Safe Atomic Restore
Trigger database restoration:
```http
POST /api/v1/backups/restore
Content-Type: application/json

{
  "backupId": "backup-uuid-here",
  "encryptionKeyHex": "your-256-bit-encryption-key-hex"
}
```

### Disaster Recovery Guarantee
The `DatabaseBackupEngine` decrypts the file and verifies its SHA-256 checksum in isolated memory. If either the decryption key is invalid or the file is corrupted, the live database is **NEVER** modified.
