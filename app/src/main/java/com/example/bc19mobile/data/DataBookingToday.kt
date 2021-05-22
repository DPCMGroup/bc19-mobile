package com.example.bc19mobile.data

class DataBookingToday {
    var bookerId: Int? = null
    var bookerUsername: String? = null
    var bookerName: String? = null
    var bookerSurname: String? = null
    var from: String? = null
    var to: String? = null


    constructor(bookerId: Int, bookerName: String?, bookerUsername: String?, bookerSurname: String?, from: String?, to: String?) {
        this.bookerId = bookerId
        this.bookerName = bookerName
        this.bookerSurname = bookerSurname
        this.bookerUsername = bookerUsername
        this.from = from
        this.to = to
    }

    constructor() {}
}