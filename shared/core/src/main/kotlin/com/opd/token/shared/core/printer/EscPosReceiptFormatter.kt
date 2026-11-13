package com.opd.token.shared.core.printer

import com.opd.token.shared.models.PrinterPaperWidth
import com.opd.token.shared.models.Token

class EscPosReceiptFormatter {

    fun formatTokenReceipt(
        token: Token,
        hospitalName: String,
        patientName: String,
        doctorName: String,
        roomNumber: String,
        departmentName: String,
        paperWidth: PrinterPaperWidth = PrinterPaperWidth.WIDTH_58MM
    ): ByteArray {
        val maxChars = if (paperWidth == PrinterPaperWidth.WIDTH_58MM) 32 else 48
        val divider = "-".repeat(maxChars)

        val builder = EscPosCommandBuilder()
            .initialize()
            .setAlignment(1) // Center align
            .setBold(true)
            .setTextSize(0, 0)
            .printLine(hospitalName.uppercase())
            .setBold(false)
            .printLine("OPD TOKEN TICKET")
            .printLine(divider)

            .setTextSize(1, 1) // Double size for Token Number
            .setBold(true)
            .printLine("TOKEN NUMBER")
            .printLine(token.tokenNumber)
            .setTextSize(0, 0)
            .setBold(false)

            .printLine(divider)
            .setAlignment(0) // Left align
            .printLine("Patient: $patientName")
            .printLine("Doctor:  $doctorName")
            .printLine("Dept:    $departmentName")
            .printLine("Room:    $roomNumber")
            .printLine("Date:    ${token.queueDate}")
            .printLine(divider)

            .setAlignment(1) // Center align
            .printLine("Please wait for your token to be")
            .printLine("called on the waiting screen.")
            .feedLines(3)
            .cutPaper()

        return builder.build()
    }
}
