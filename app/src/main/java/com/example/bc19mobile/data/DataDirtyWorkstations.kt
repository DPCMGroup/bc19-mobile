package com.example.bc19mobile.data

class DataDirtyWorkstations {
    var id: Int? = null
    var tag: String? = null
    var workstationName: String? = null
    var xWorkstation: Int? = null
    var yWorkstation: Int? = null
    var idRoom: Int? = null
    var state: Int? = null
    var sanitized: Int? = null
    var archived: Int? = null


    constructor(id: Int, tag: String?, workstationName: String?, xWorkstation: Int?, yWorkstation: Int?, idRoom: Int?, state: Int?, sanitized: Int?, archived: Int?) {
        this.id = id
        this.tag = tag
        this.workstationName = workstationName
        this.yWorkstation = yWorkstation
        this.xWorkstation = xWorkstation
        this.idRoom = idRoom
        this.state = state
        this.sanitized =sanitized
        this.archived = archived
    }

    constructor() {}
}