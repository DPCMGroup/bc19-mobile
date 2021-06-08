package com.example.bc19mobile.model

import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User
import org.junit.Test

import org.junit.Assert.*

class BookingFormModelTest {

    val bookingFormModel = BookingFormModel()
    val employee = User(0, "employee", "employee", 1, -1)
    val bookingRequest = BookingWorkstation("2021-12-02", "12:20:00", "13:20:00", "1", "sig. Rossi")

    @Test
    fun getSetUserTest() {
        bookingFormModel.setUser(employee)
        assertEquals(bookingFormModel.getUser(), employee)
    }

    @Test
    fun setGetBookingWorkstationTest() {
        bookingFormModel.setBookingWorkstation(bookingRequest)
        assertEquals(bookingFormModel.getBookingWorkstation(), bookingRequest)
    }
}