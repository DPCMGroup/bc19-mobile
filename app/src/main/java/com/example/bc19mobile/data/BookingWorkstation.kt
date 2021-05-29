package com.example.bc19mobile.data


import android.os.Parcel
import android.os.Parcelable


class BookingWorkstation() : Parcelable {
    private var dataTesto: String = ""
    private var inizioTesto: String = ""
    private var fineTesto: String = ""
    private var stanzaTesto: String = ""
    private var dipTesto: String = ""


    constructor(parcel: Parcel) : this() {
        dataTesto = parcel.readString()!!
        inizioTesto = parcel.readString()!!
        fineTesto = parcel.readString()!!
        stanzaTesto = parcel.readString()!!
        dipTesto = parcel.readString()!!
    }

    constructor(
        dataTesto: String,
        inizioTesto: String,
        fineTesto: String,
        stanzaTesto: String,
        dipTesto: String
    ) : this() {
        this.dataTesto = dataTesto
        this.inizioTesto = inizioTesto
        this.fineTesto = fineTesto
        this.stanzaTesto = stanzaTesto
        this.dipTesto = dipTesto
    }

    fun getdataTesto(): String {
        return dataTesto
    }

    fun getinizioTesto(): String {
        return inizioTesto
    }

    fun getfineTesto(): String {
        return fineTesto
    }

    fun getstanzaTesto(): String {
        return stanzaTesto
    }

    fun getdipTesto(): String {
        return dipTesto
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dataTesto)
        parcel.writeString(inizioTesto)
        parcel.writeString(fineTesto)
        parcel.writeString(stanzaTesto)
        parcel.writeString(dipTesto)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookingWorkstation> {
        override fun createFromParcel(parcel: Parcel): BookingWorkstation {
            return BookingWorkstation(parcel)
        }

        override fun newArray(size: Int): Array<BookingWorkstation?> {
            return arrayOfNulls(size)
        }
    }
}

