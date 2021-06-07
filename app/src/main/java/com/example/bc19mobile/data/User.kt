package com.example.bc19mobile.data

import android.os.Parcel
import android.os.Parcelable


class User() : Parcelable {
    private var id: Int = -1
    private var username: String = ""
    private var password: String = ""
    private var type: Int = -1
    private var occupation: Int = -1

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        username = parcel.readString()!!
        password = parcel.readString()!!
        type = parcel.readInt()
        occupation = parcel.readInt()
    }

    constructor(id: Int, username: String, password: String, type: Int, occupation: Int) : this() {
        this.id = id
        this.username = username
        this.password = password
        this.type = type
        this.occupation = occupation
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

    fun getOccupation(): Int {
        return occupation
    }

    fun setOccupation(id: Int){
        this.occupation = id
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeInt(type)
        parcel.writeInt(occupation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}