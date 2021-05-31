package com.example.bc19mobile.data

class DataAttendance {

    var idAttendance: Int? = null
    var upperBoundTimeAttendance: String? = null

    constructor(idAttendance: Int, upperBoundTimeAttendance: String) {
        this.idAttendance = idAttendance
        this.upperBoundTimeAttendance = upperBoundTimeAttendance

    }

    constructor() {}
}