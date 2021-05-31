package com.example.bc19mobile.data

import org.junit.Test

import org.junit.Assert.*

class BookingWorkstationTest {
    val bookingWorkstation = BookingWorkstation("2021-12-12","12:20:11","12:50:12","stanza","sig.Rossi")

    @Test
    fun getdataTesto() {
        assertEquals(bookingWorkstation.getdataTesto(),"2021-12-12")
    }

    @Test
    fun getinizioTesto() {
        assertEquals(bookingWorkstation.getinizioTesto(),"12:20:11")
    }

    @Test
    fun getfineTesto() {
        assertEquals(bookingWorkstation.getfineTesto(),"12:50:12")
    }

    @Test
    fun getstanzaTesto() {
        assertEquals(bookingWorkstation.getstanzaTesto(),"stanza")
    }

    @Test
    fun getdipTesto() {
        assertEquals(bookingWorkstation.getdipTesto(),"sig.Rossi")
    }
}