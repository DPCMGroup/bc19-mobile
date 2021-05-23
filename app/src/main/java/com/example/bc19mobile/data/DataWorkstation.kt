package com.example.bc19mobile.data

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
