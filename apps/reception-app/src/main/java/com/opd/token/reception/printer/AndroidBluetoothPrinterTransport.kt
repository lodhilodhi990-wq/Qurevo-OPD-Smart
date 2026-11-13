package com.opd.token.reception.printer

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import com.opd.token.shared.models.PrinterDevice
import com.opd.token.shared.models.PrinterPaperWidth
import com.opd.token.shared.models.PrinterTransportType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.util.UUID

class AndroidBluetoothPrinterTransport {

    // Standard SPP UUID for Bluetooth Classic printers
    private val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    fun getPairedPrinters(): List<PrinterDevice> {
        val adapter = BluetoothAdapter.getDefaultAdapter() ?: return emptyList()
        if (!adapter.isEnabled) return emptyList()

        return try {
            adapter.bondedDevices.map { device ->
                PrinterDevice(
                    address = device.address,
                    name = device.name ?: "Thermal Printer (${device.address})",
                    transportType = PrinterTransportType.BLUETOOTH_SPP,
                    paperWidth = PrinterPaperWidth.WIDTH_58MM
                )
            }
        } catch (e: SecurityException) {
            emptyList()
        }
    }

    suspend fun printBytes(macAddress: String, data: ByteArray): Boolean = withContext(Dispatchers.IO) {
        val adapter = BluetoothAdapter.getDefaultAdapter() ?: return@withContext false
        if (!adapter.isEnabled) return@withContext false

        var socket: BluetoothSocket? = null
        var outputStream: OutputStream? = null

        try {
            val device: BluetoothDevice = adapter.getRemoteDevice(macAddress)
            socket = device.createRfcommSocketToServiceRecord(SPP_UUID)
            adapter.cancelDiscovery()
            socket.connect()

            outputStream = socket.outputStream
            outputStream.write(data)
            outputStream.flush()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            try {
                outputStream?.close()
                socket?.close()
            } catch (e: Exception) {
                // Ignore close exceptions
            }
        }
    }
}
