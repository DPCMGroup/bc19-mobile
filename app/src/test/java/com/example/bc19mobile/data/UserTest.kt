package com.example.bc19mobile.data

import org.junit.Test

import org.junit.Assert.*

class UserTest {

    var employee = User(0, "employee", "employee", 1,-1)

    @Test
    fun getIdUser() {
        assertEquals(employee.getId(),0)
    }

    @Test
    fun getUsernameUser() {
        assertEquals(employee.getUsername(),"employee")
    }

    @Test
    fun getPasswordUser() {
        assertEquals(employee.getPassword(),"employee")
    }

    @Test
    fun getTypeUser() {
        assertEquals(employee.getType(),1)
    }
}