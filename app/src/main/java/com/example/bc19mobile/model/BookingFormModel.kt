package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
class BookingFormModel : BaseModel(), BookingFormContract.IModel {
    interface RoomsListener {
        fun onRoomsSuccess()
        fun onRoomsFailure()
    }

    private var user: User? = null
    private var bookingWorkstation: BookingWorkstation? = null
    private val service = Service()
    private var listener: BookingFormModel.RoomsListener? = null
    private var roomsList = ArrayList<DataDirtyRooms>()

    override fun setRoomsListener(listener: BookingFormModel.RoomsListener?) {
        this.listener = listener
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    override fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?) {
        this.bookingWorkstation = bookingWorkstation
    }

    override fun getBookingWorkstation(): BookingWorkstation? {
        return bookingWorkstation
    }

    override fun saveBookingWorkstation(
        dataTesto: String,
        inizioTesto: String,
        fineTesto: String,
        stanzaTesto: String,
        dipTesto: String
    ) {
        setBookingWorkstation(
            BookingWorkstation(
                dataTesto,
                inizioTesto,
                fineTesto,
                stanzaTesto,
                dipTesto
            )
        )
    }

    override fun getRoomsList(): ArrayList<DataDirtyRooms>? {
        return roomsList
    }

    override fun getRooms() {
        service.request(
            null,
            "room/list",
            false,
            ::RoomsHandle,
            ::connectionError
        )
    }

    private fun connectionError(ioException: IOException) {
        //manage connection error
    }

    private fun RoomsHandle(response: String) {
        if (response !="8193" && response !="8194" && response!="[]") {
            val jsonArray = JSONArray(response)
            roomsList = ArrayList<DataDirtyRooms>()
            for (i in 0 until jsonArray.length()) {
                val room = jsonArray.getJSONObject(i)
                val model = DataDirtyRooms()
                model.id = room.getInt("id")
                model.roomName = room.getString("roomname")
                model.xRoom = room.getInt("xroom")
                model.yRoom = room.getInt("yroom")
                model.archived = room.getInt("archived")
                model.unavailable = room.getInt("unavailable")
                roomsList.add(model)
            }
            listener?.onRoomsSuccess()
        }else{
            listener?.onRoomsFailure()
        }
    }

}