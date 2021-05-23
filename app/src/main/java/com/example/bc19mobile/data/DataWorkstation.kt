package com.example.bc19mobile.data
/*
data class DataWorkstation(
    val _tag: String,
    val _workId: Int,
    val _workName: String,
    val _workStatus: Int,
    val _bookedToday: Int,
    val _workSanitize: Int
)
*/

class DataWorkstation {
    var _workId: Int? = null
    var _roomName: String? = null
    var _workName: String? = null
    var _workStatus: Int? = null
    var _workSanitize: Int? = null
    var _bookedToday: Int? = null


    constructor(
        workId: Int?,
        roomName: String?,
        workName: String?,
        workStatus: Int?,
        bookedToday: Int?,
        workSanitize: Int?
    ) {
        this._workName = workName
        this._roomName = workName
        this._workId = workId
        this._workStatus = workStatus
        this._workSanitize = workSanitize
        this._bookedToday = bookedToday
    }

    constructor() {}
}
