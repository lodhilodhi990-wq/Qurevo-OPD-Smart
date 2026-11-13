package com.opd.token.shared.core.printer

import com.opd.token.shared.models.*
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class PrinterManager(
    private val formatter: EscPosReceiptFormatter = EscPosReceiptFormatter()
) {
    private var configuration = PrinterConfiguration()
    private val printJobMap = ConcurrentHashMap<String, PrintJob>()

    fun setConfiguration(config: PrinterConfiguration) {
        this.configuration = config
    }

    fun getConfiguration(): PrinterConfiguration = configuration

    fun createPrintJob(
        token: Token,
        hospitalName: String = "CITY GENERAL HOSPITAL",
        patientName: String = "Muhammad Ali",
        doctorName: String = "Dr. Ahmed",
        roomNumber: String = "02",
        departmentName: String = "Cardiology"
    ): PrintJob {
        val job = PrintJob(
            id = UUID.randomUUID().toString(),
            token = token,
            hospitalName = hospitalName,
            patientName = patientName,
            doctorName = doctorName,
            roomNumber = roomNumber,
            departmentName = departmentName,
            status = PrintJobStatus.QUEUED
        )
        printJobMap[job.id] = job
        return job
    }

    fun generateEscPosBytes(job: PrintJob): ByteArray {
        return formatter.formatTokenReceipt(
            token = job.token,
            hospitalName = job.hospitalName,
            patientName = if (configuration.showPatientNameOnReceipt) job.patientName else "N/A",
            doctorName = job.doctorName,
            roomNumber = job.roomNumber,
            departmentName = job.departmentName,
            paperWidth = configuration.paperWidth
        )
    }

    fun updateJobStatus(jobId: String, status: PrintJobStatus, errorMessage: String? = null) {
        val job = printJobMap[jobId]
        if (job != null) {
            printJobMap[jobId] = job.copy(status = status, errorMessage = errorMessage)
        }
    }

    fun getPrintJob(jobId: String): PrintJob? = printJobMap[jobId]
}
