# QUREVO v2.0.0 — Port Configuration & Source Audit

---

## Source Code Audit of Network Ports

1. **Ktor Backend HTTP & WebSocket Port (`27800`)**:
   - **Defined in**: `shared/core/src/main/kotlin/com/opd/token/shared/core/NetworkResult.kt` (`DEFAULT_PORT = 27800`)
   - **Backend Binding**: `backend/ktor-server/src/main/kotlin/com/opd/token/backend/Application.kt` (`embeddedServer(Netty, port = ApiEndpoints.DEFAULT_PORT)`)
   - **Client Binding**: `shared/core/src/main/kotlin/com/opd/token/shared/core/network/OpdApiClient.kt`
   - **Web Binding**: `web-apps/unified-portal/app.js` and `web/public/js/app.js`

2. **UDP LAN Server Discovery Port (`27801`)**:
   - **Defined in**: `shared/core/src/main/kotlin/com/opd/token/shared/core/NetworkResult.kt` (`LAN_DISCOVERY_PORT = 27801`)
   - **Broadcaster**: `shared/core/src/main/kotlin/com/opd/token/shared/core/network/LanDiscovery.kt` (`LanServerBroadcaster` sends `OPD_SERVER_DISCOVERY` over UDP port 27801)
   - **Discoverer**: `shared/core/src/main/kotlin/com/opd/token/shared/core/network/LanDiscovery.kt` (`LanServerDiscoverer` listens on UDP port 27801)

---

## Windows Firewall Configuration Requirements
- **Inbound Rule 1**: Allow TCP port `27800` for HTTP REST APIs & WebSockets.
- **Inbound Rule 2**: Allow UDP port `27801` for automatic LAN server discovery.
