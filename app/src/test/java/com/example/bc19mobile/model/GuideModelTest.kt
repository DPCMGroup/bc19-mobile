package com.example.bc19mobile.model

import com.example.bc19mobile.data.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GuideModelTest {


    val guideModel = GuideModel()
    var employee = User(0, "employee", "employee", 1,-1)
    var cleanWorker = User(0, "cleanWorker", "cleanWorker", 2,-1)

    @Test
    fun getEmployeeGuide() {
        guideModel.setUser(employee)
        assertEquals(guideModel.getGuide(),"guida_dipendente.pdf")
    }

    @Test
    fun getCleanWorkerGuide() {
        guideModel.setUser(cleanWorker)
        assertEquals(guideModel.getGuide(),"guida_addetto.pdf")
    }

    @Test
    fun getUserWithGuideView() {
        guideModel.setUser(employee)
        assertEquals(guideModel.getUser(), employee)
    }

    @Test
    fun setUserWithGuideView() {
        assertNull(guideModel.getUser())
        guideModel.setUser(employee)
        assertEquals(guideModel.getUser(), employee)
        guideModel.setUser(cleanWorker)
        assertEquals(guideModel.getUser(), cleanWorker)
        guideModel.setUser(null)
        assertNull(guideModel.getUser())
    }
}