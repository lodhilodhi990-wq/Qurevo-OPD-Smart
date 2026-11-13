package com.opd.token.shared.models

import kotlinx.serialization.Serializable

@Serializable
enum class PrinterPaperWidth {
    WIDTH_58MM, // 32 characters per line
    WIDTH_80MM  // 48 characters per line
}

@Serializable
enum class PrinterTransportType {
    BLUETOOTH_SPP,
    WINDOWS_SPOOLER,
    USB_RAW,
    NETWORK_LAN
}

@Serializable
enum class PrinterConnectionStatus {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR
}

@Serializable
enum class PrintJobStatus {
    QUEUED,
    CONNECTING,
    PRINTING,
    COMPLETED,
    FAILED,
    CANCELLED,
    RETRY_PENDING
}

@Serializable
data class PrinterDevice(
    val address: String, // MAC Address for Bluetooth or IP/Name for Network
    val name: String,
    val transportType: PrinterTransportType = PrinterTransportType.BLUETOOTH_SPP,
    val paperWidth: PrinterPaperWidth = PrinterPaperWidth.WIDTH_58MM,
    val isAutoPrintEnabled: Boolean = true
)

@Serializable
data class PrinterConfiguration(
    val selectedPrinter: PrinterDevice? = null,
    val paperWidth: PrinterPaperWidth = PrinterPaperWidth.WIDTH_58MM,
    val numberOfCopies: Int = 1,
    val autoPrintOnTokenCreation: Boolean = true,
    val showPatientNameOnReceipt: Boolean = true
)

@Serializable
data class PrintJob(
    val id: String,
    val token: Token,
    val hospitalName: String,
    val patientName: String,
    val doctorName: String,
    val roomNumber: String,
    val departmentName: String,
    val status: PrintJobStatus = PrintJobStatus.QUEUED,
    val retryCount: Int = 0,
    val errorMessage: String? = null
)
