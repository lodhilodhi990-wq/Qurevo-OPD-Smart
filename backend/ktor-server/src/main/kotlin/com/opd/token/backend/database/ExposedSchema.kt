package com.opd.token.backend.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TenantsTable : Table("tenants") {
    val id = varchar("id", 36)
    val name = varchar("name", 100)
    val code = varchar("code", 20).uniqueIndex()
    val isBlocked = boolean("is_blocked").default(false)
    val createdAt = datetime("created_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}

object HospitalsTable : Table("hospitals") {
    val id = varchar("id", 36)
    val tenantId = varchar("tenant_id", 36).references(TenantsTable.id)
    val name = varchar("name", 150)
    val address = text("address").nullable()
    val phone = varchar("phone", 30).nullable()
    val city = varchar("city", 50).nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}

object UsersTable : Table("users") {
    val id = varchar("id", 36)
    val tenantId = varchar("tenant_id", 36).references(TenantsTable.id)
    val hospitalId = varchar("hospital_id", 36).references(HospitalsTable.id)
    val username = varchar("username", 50).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)
    val fullName = varchar("full_name", 100)
    val role = varchar("role", 30)
    val isActive = boolean("is_active").default(true)

    override val primaryKey = PrimaryKey(id)
}

object SubscriptionsTable : Table("subscriptions") {
    val id = varchar("id", 36)
    val hospitalId = varchar("hospital_id", 36).references(HospitalsTable.id)
    val planName = varchar("plan_name", 50).default("FREE_LAN")
    val status = varchar("status", 20).default("ACTIVE")
    val validUntil = datetime("valid_until").nullable()

    override val primaryKey = PrimaryKey(id)
}

object PaymentRecordsTable : Table("payment_records") {
    val id = varchar("id", 36)
    val hospitalId = varchar("hospital_id", 36).references(HospitalsTable.id)
    val amount = double("amount")
    val paymentMethod = varchar("payment_method", 50) // JazzCash, Easypaisa, Bank Transfer
    val transactionRef = varchar("transaction_ref", 100)
    val isVerified = boolean("is_verified").default(false)
    val createdAt = datetime("created_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}
