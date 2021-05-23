package com.example.bc19mobile.data

/*
data class DataBooking(
    val _bookingId: Int,
    val _workName: String,
    val _roomName: String,
    val _startTime: String,
    val _endTime: String
)

 */

class DataBooking {
    var bookId: Int? = null
    var workName: String? = null
    var roomName: String? = null
    var start: String? = null
    var end: String? = null


    constructor(bookId: Int, workName: String?, roomName: String?, start: String?, end: String?) {
        this.bookId = bookId
        this.workName = workName
        this.roomName = roomName
        this.start = start
        this.end = end
    }

    constructor() {}
}