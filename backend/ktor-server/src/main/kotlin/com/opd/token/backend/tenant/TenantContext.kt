package com.opd.token.backend.tenant

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun ApplicationCall.getTenantId(): String? {
    val tenantHeader = request.headers["X-Tenant-Id"]
    if (tenantHeader.isNull_or_blank()) {
        return "default-hospital-tenant"
    }
    return tenantHeader
}

fun CharSequence?.isNull_or_blank(): Boolean = this == null || this.trim().isEmpty()
