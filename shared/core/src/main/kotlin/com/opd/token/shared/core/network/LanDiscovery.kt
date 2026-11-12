package com.opd.token.shared.core.network

import com.opd.token.shared.core.SystemConstants
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LanServerBroadcaster(private val serverIp: String) {
    suspend fun startBroadcasting() = withContext(Dispatchers.IO) {
        val socket = DatagramSocket()
        socket.broadcast = true
        val message = "OPD_SERVER_DISCOVERY:$serverIp:27800"
        val buffer = message.toByteArray()
        val packet = DatagramPacket(
            buffer,
            buffer.size,
            InetAddress.getByName("255.255.255.255"),
            SystemConstants.LAN_DISCOVERY_PORT
        )
        try {
            socket.send(packet)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            socket.close()
        }
    }
}

class LanServerDiscoverer {
    suspend fun discoverServer(timeoutMs: Int = 3000): String? = withContext(Dispatchers.IO) {
        try {
            val socket = DatagramSocket(SystemConstants.LAN_DISCOVERY_PORT)
            socket.soTimeout = timeoutMs
            val buffer = ByteArray(1024)
            val packet = DatagramPacket(buffer, buffer.size)
            socket.receive(packet)
            val message = String(packet.data, 0, packet.length)
            socket.close()
            if (message.startsWith("OPD_SERVER_DISCOVERY:")) {
                val parts = message.split(":")
                if (parts.size >= 2) return@withContext parts[1]
            }
            null
        } catch (e: Exception) {
            null
        }
    }
}
