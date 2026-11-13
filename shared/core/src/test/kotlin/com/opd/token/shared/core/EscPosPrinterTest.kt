package com.opd.token.shared.core

import com.opd.token.shared.core.printer.EscPosCommandBuilder
import com.opd.token.shared.core.printer.EscPosReceiptFormatter
import com.opd.token.shared.core.printer.PrinterManager
import com.opd.token.shared.models.PrinterPaperWidth
import com.opd.token.shared.models.Token
import com.opd.token.shared.models.TokenPriority
import com.opd.token.shared.models.TokenStatus
import org.junit.Assert.*
import org.junit.Test

class EscPosPrinterTest {

    @Test
    fun testEscPosCommandBuilder_generatesCorrectBytes() {
        val bytes = EscPosCommandBuilder()
            .initialize()
            .setAlignment(1)
            .setBold(true)
            .printLine("TEST HOSPITAL")
            .cutPaper()
            .build()

        assertTrue(bytes.isNotEmpty())
        assertEquals(0x1B.toByte(), bytes[0]) // ESC byte
        assertEquals(0x40.toByte(), bytes[1]) // @ byte (Initialize)
    }

    @Test
    fun testReceiptFormatter_generates58mmAnd80mmLayouts() {
        val formatter = EscPosReceiptFormatter()
        val dummyToken = Token(
            id = "t-1",
            hospitalId = "h-1",
            visitId = "v-1",
            departmentId = "dep-1",
            doctorId = "doc-1",
            tokenNumber = "T-025",
            tokenPrefix = "T",
            queueDate = "2026-03-30",
            priority = TokenPriority.NORMAL,
            status = TokenStatus.WAITING,
            createdAt = "2026-03-30T10:00:00",
            createdBy = "reception1"
        )

        val bytes58mm = formatter.formatTokenReceipt(
            token = dummyToken,
            hospitalName = "CITY GENERAL HOSPITAL",
            patientName = "Muhammad Ali",
            doctorName = "Dr. Ahmed",
            roomNumber = "02",
            departmentName = "Cardiology",
            paperWidth = PrinterPaperWidth.WIDTH_58MM
        )

        val bytes80mm = formatter.formatTokenReceipt(
            token = dummyToken,
            hospitalName = "CITY GENERAL HOSPITAL",
            patientName = "Muhammad Ali",
            doctorName = "Dr. Ahmed",
            roomNumber = "02",
            departmentName = "Cardiology",
            paperWidth = PrinterPaperWidth.WIDTH_80MM
        )

        assertTrue(bytes58mm.isNotEmpty())
        assertTrue(bytes80mm.isNotEmpty())
    }

    @Test
    fun testPrinterManager_decouplesTokenCreationFromPrinting() {
        val manager = PrinterManager()
        val dummyToken = Token(
            id = "t-1",
            hospitalId = "h-1",
            visitId = "v-1",
            departmentId = "dep-1",
            doctorId = "doc-1",
            tokenNumber = "T-025",
            tokenPrefix = "T",
            queueDate = "2026-03-30",
            priority = TokenPriority.NORMAL,
            status = TokenStatus.WAITING,
            createdAt = "2026-03-30T10:00:00",
            createdBy = "reception1"
        )

        val printJob = manager.createPrintJob(dummyToken)
        assertNotNull(printJob)
        assertEquals("T-025", printJob.token.tokenNumber)

        val bytes = manager.generateEscPosBytes(printJob)
        assertTrue(bytes.isNotEmpty())
    }
}
