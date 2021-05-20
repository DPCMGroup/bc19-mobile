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
    var _tag: String? = null
    var _workId: Int? = null
    var _workName: String? = null
    var _workStatus: Int? = null
    var _bookedToday: Int? = null
    var _workSanitize: Int? = null


    constructor(
        tag: String?,
        workId: Int?,
        workName: String?,
        workStatus: Int?,
        bookedToday: Int?,
        workSanitize: Int?
    ) {
        this._tag = tag
        this._workName = workName
        this._workId = workId
        this._workStatus = workStatus
        this._bookedToday = bookedToday
        this._workSanitize = workSanitize
    }

    constructor() {}
}
