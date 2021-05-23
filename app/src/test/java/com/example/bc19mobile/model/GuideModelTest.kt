package com.example.bc19mobile.model

import com.example.bc19mobile.data.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GuideModelTest {


    val guideModel = GuideModel()
    var employee = User(0, "employee", "employee", 1)
    var cleanWorker = User(0, "cleanWorker", "cleanWorker", 2)

    @Test
    fun employeeGuide() {
        guideModel.setUser(employee)
        assertEquals(guideModel.getGuide(),"guida_utente.pdf")
    }

    @Test
    fun cleanWorkerGuide() {
        guideModel.setUser(cleanWorker)
        assertEquals(guideModel.getGuide(),"guida_utente.pdf")
    }

}