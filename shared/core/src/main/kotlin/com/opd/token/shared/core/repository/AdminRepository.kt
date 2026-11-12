package com.opd.token.shared.core.repository

import com.opd.token.shared.models.Department
import com.opd.token.shared.models.Doctor
import com.opd.token.shared.models.Room

class AdminRepository {
    private val departments = mutableListOf(
        Department("dep-1", "hosp-1", "Cardiology", "CARD"),
        Department("dep-2", "hosp-1", "General OPD", "GEN"),
        Department("dep-3", "hosp-1", "Paediatrics", "PAED"),
        Department("dep-4", "hosp-1", "Orthopaedics", "ORTHO")
    )

    private val doctors = mutableListOf(
        Doctor("doc-1", "hosp-1", "dep-1", "rm-2", "Dr. Ahmed", "MBBS, FCPS (Cardiology)", true),
        Doctor("doc-2", "hosp-1", "dep-2", "rm-1", "Dr. Fatima", "MBBS, MD", true)
    )

    private val rooms = mutableListOf(
        Room("rm-1", "hosp-1", "dep-2", "01", "General OPD Room"),
        Room("rm-2", "hosp-1", "dep-1", "02", "Cardiology Room")
    )

    fun getDepartments(): List<Department> = departments
    fun getDoctors(): List<Doctor> = doctors
    fun getRooms(): List<Room> = rooms
}
