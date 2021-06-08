package com.example.bc19mobile.model

import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataBookableWorkstation
import com.example.bc19mobile.data.User
import org.junit.Test

import org.junit.Assert.*

class BookingWorkstationModelTest {

    val bookingWorkstationModel = BookingWorkstationModel()
    val employee = User(0, "employee", "employee", 1,-1)

    @Test
    fun getBookableWorkstations() {
        val workstationsBookableList = ArrayList<DataBookableWorkstation>()
        assertEquals(bookingWorkstationModel.getBookableWorkstations(), workstationsBookableList)
    }

    @Test
    fun getSetUserTest() {
        bookingWorkstationModel.setUser(employee)
        assertEquals(bookingWorkstationModel.getUser(), employee)
    }

    @Test
    fun setGetBookingWorkstation() {
        val bookingWorkstation = BookingWorkstation()
        bookingWorkstationModel.setBookingWorkstation(bookingWorkstation)
        assertEquals(bookingWorkstationModel.getBookingWorkstation(), bookingWorkstation)

    }
}