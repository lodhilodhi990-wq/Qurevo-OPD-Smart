package com.opd.token.backend.database

import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    fun init() {
        // Fallback in-memory database configuration when PostgreSQL is not running locally
        Database.connect(
            url = "jdbc:h2:mem:opd_db;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )
    }
}
