package com.example.bc19mobile.data

class User {
    private var id: Int = -1
    private var username: String = ""
    private var password: String = ""
    private var type: Int = -1

    constructor(id: Int, username: String, password: String, type: Int) {
        this.id = id
        this.username = username
        this.password = password
        this.type = type
    }

    fun getId(): Int {
        return id
    }

    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun getType(): Int {
        return type
    }


}