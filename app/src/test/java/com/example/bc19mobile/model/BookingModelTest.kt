package com.example.bc19mobile.model

import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User
import org.junit.Test

import org.junit.Assert.*

class BookingModelTest {

    val bookingModel = BookingModel()
    val employee = User(0, "employee", "employee", 1,-1)

    @Test
    fun getBookingList() {
        var bookingList = ArrayList<DataBooking>()
        assertEquals(bookingModel.getBookingList(), bookingList)
    }

    @Test
    fun getSetUserTest() {
        bookingModel.setUser(employee)
        assertEquals(bookingModel.getUser(), employee)
    }
}