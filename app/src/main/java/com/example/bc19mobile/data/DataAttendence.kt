package com.example.bc19mobile.data

class DataAttendence {

    var idAttendence: Int? = null
    var upperBoundTimeAttendence: String? = null

    constructor(idAttendence: Int, upperBoundTimeAttendence: String) {
        this.idAttendence = idAttendence
        this.upperBoundTimeAttendence = upperBoundTimeAttendence

    }

    constructor() {}
}