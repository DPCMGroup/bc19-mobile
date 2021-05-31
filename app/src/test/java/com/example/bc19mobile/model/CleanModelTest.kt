package com.example.bc19mobile.model

import com.example.bc19mobile.data.User
import org.junit.Test

import org.junit.Assert.*

class CleanModelTest {

    val cleanModel = CleanModel()
    val employee = User(0, "employee", "employee", 1)

    @Test
    fun getSetUserTest() {
        cleanModel.setUser(employee)
        assertEquals(cleanModel.getUser(),employee)
    }
}