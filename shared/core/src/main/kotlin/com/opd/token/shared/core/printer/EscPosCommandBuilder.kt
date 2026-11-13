package com.opd.token.shared.core.printer

import java.io.ByteArrayOutputStream

class EscPosCommandBuilder {
    private val buffer = ByteArrayOutputStream()

    fun initialize(): EscPosCommandBuilder {
        buffer.write(byteArrayOf(0x1B, 0x40)) // ESC @
        return this
    }

    fun setAlignment(align: Int): EscPosCommandBuilder {
        // 0 = Left, 1 = Center, 2 = Right
        buffer.write(byteArrayOf(0x1B, 0x61, align.toByte())) // ESC a n
        return this
    }

    fun setBold(enable: Boolean): EscPosCommandBuilder {
        val mode = if (enable) 1 else 0
        buffer.write(byteArrayOf(0x1B, 0x45, mode.toByte())) // ESC E n
        return this
    }

    fun setTextSize(widthMultiplier: Int, heightMultiplier: Int): EscPosCommandBuilder {
        // 0 = Normal, 1 = Double
        val n = ((widthMultiplier and 0x07) shl 4) or (heightMultiplier and 0x07)
        buffer.write(byteArrayOf(0x1D, 0x21, n.toByte())) // GS ! n
        return this
    }

    fun printText(text: String): EscPosCommandBuilder {
        buffer.write(text.toByteArray(Charsets.US_ASCII))
        return this
    }

    fun printLine(text: String = ""): EscPosCommandBuilder {
        if (text.isNotEmpty()) {
            buffer.write(text.toByteArray(Charsets.US_ASCII))
        }
        buffer.write(0x0A) // Line Feed (LF)
        return this
    }

    fun feedLines(lines: Int): EscPosCommandBuilder {
        buffer.write(byteArrayOf(0x1B, 0x64, lines.toByte())) // ESC d n
        return this
    }

    fun cutPaper(): EscPosCommandBuilder {
        buffer.write(byteArrayOf(0x1D, 0x56, 0x42, 0x00)) // GS V 66 0
        return this
    }

    fun build(): ByteArray {
        return buffer.toByteArray()
    }
}
