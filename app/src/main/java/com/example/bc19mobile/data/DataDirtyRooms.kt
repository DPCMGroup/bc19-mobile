package com.example.bc19mobile.data

class DataDirtyRooms {
    var id: Int? = null
    var roomName: String? = null
    var xRoom: Int? = null
    var yRoom: Int? = null
    var archived: Int? = null
    var unavailable: Int? = null


    constructor(id: Int, roomName: String?, xRoom: Int?, yRoom: Int?, archived: Int?, unavailable: Int?) {
        this.id = id
        this.xRoom = xRoom
        this.roomName = roomName
        this.yRoom = yRoom
        this.archived = archived
        this.unavailable =unavailable
    }

    constructor() {}
}