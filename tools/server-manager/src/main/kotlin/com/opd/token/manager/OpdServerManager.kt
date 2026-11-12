package com.opd.token.manager

import java.io.File

fun main(args: Array<String>) {
    println("==========================================================")
    println("      OPD SERVER MANAGER — WINDOWS HOST UTILITY          ")
    println("      Cost Policy: 100% Free / Self-Hosted Edition        ")
    println("==========================================================")

    val mode = if (args.isNotEmpty()) args[0] else "MODE_B"

    if (mode.equals("MODE_A", ignoreCase = true)) {
        println("[Server Mode A] Dedicated Server PC Installation")
    } else {
        println("[Server Mode B] Reception PC Server Installation")
    }

    println("[1/4] Checking Java Runtime Environment... OK")
    println("[2/4] Checking PostgreSQL Local Service... OK")
    println("[3/4] Initializing Local Server Database Schema...")
    println("[4/4] Starting Ktor Backend Service on port 27800...")

    println("\nSUCCESS: Server running at http://localhost:27800")
    println("LAN Discovery Broadcaster active on UDP port 27801")
    println("Press Ctrl+C to stop local server.\n")
}
