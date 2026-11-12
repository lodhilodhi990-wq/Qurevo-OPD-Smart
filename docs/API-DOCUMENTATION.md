# OPD Smart Token Platform — API Documentation

Base URL: `http://SERVER-IP:27800/api/v1`  
WebSocket URL: `ws://SERVER-IP:27800/ws`

---

## 1. Authentication APIs

### Login
- **Endpoint**: `POST /api/v1/auth/login`
- **Request**:
```json
{
  "username": "reception1",
  "password": "password123"
}
```
- **Response**:
```json
{
  "token": "jwt-mock-token-xyz",
  "user": {
    "id": "usr-1",
    "username": "reception1",
    "fullName": "Receptionist Staff",
    "role": "RECEPTIONIST",
    "hospitalId": "hosp-1"
  }
}
```

---

## 2. Patient APIs

### Search Patients
- **Endpoint**: `GET /api/v1/patients/search?q=Muhammad`
- **Response**: Array of matching `Patient` objects.

### Register Patient
- **Endpoint**: `POST /api/v1/patients`
- **Request**:
```json
{
  "id": "p-101",
  "mrNumber": "MR-2026-0042",
  "name": "Muhammad Ali",
  "age": 35,
  "gender": "Male",
  "phone": "03001234567"
}
```

---

## 3. Token & Queue APIs

### Generate Token
- **Endpoint**: `POST /api/v1/tokens`
- **Request**:
```json
{
  "patientId": "p-1",
  "doctorId": "doc-1",
  "departmentId": "dep-1"
}
```

### Call Next Patient
- **Endpoint**: `POST /api/v1/tokens/next?doctorId=doc-1`
- **Response**: Called `Token` object.

### Queue Status Action
- **Endpoints**:
  - `POST /api/v1/tokens/{id}/recall`
  - `POST /api/v1/tokens/{id}/hold`
  - `POST /api/v1/tokens/{id}/skip`
  - `POST /api/v1/tokens/{id}/complete`

---

## 4. WebSocket Real-Time Events (`ws://SERVER-IP:27800/ws`)

### Event Format:
```json
{
  "type": "TOKEN_CALLED",
  "payload": "T-025"
}
```

Events:
- `TOKEN_CREATED`
- `TOKEN_CALLED`
- `TOKEN_RECALLED`
- `TOKEN_COMPLETED`
