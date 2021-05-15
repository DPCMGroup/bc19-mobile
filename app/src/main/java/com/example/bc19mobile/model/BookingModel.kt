package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingModel : BaseModel(), BookingContract.IModel {

    interface BookingListener {
        fun onBookingSuccess(): Void
    }

    private var listener: BookingListener? = null
    private val service = Service()
    private var user: User? = null
    private var bookingList: ArrayList<DataBooking>? = null

    override fun setBookingListener(listener: BookingListener?) {
        this.listener = listener
    }

    override fun getBookingList(): ArrayList<DataBooking>? {
        return bookingList
    }

    override fun getUserBooking() {
        val jsonObject = JSONObject()
        val userId: Int = user?.getId() ?: -1
        jsonObject.put("id", userId)
        service.request(
            jsonObject,
            "user/bookings/" + userId,
            false,
            ::BokingHandle,
            ::connectionError
        )
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    private fun connectionError(ioException: IOException) {
        //gestisto gli errori con connessione
    }

    private fun BokingHandle(response: String) {

        //TO DO gestire l'assenza di prenotazioni
        if (response == "\"No Bookings\"") {

        } else {
            val restJson = JSONArray(response)

            for (i in 0 until restJson.length()) {
                val jsonObject = restJson.getJSONObject(i)
                bookingList?.add(
                    DataBooking(
                        jsonObject.getInt("bookId"),
                        jsonObject.getString("workName"),
                        jsonObject.getString("roomName"),
                        jsonObject.getString("start"),
                        jsonObject.getString("end")
                    )
                )
            }

            listener?.onBookingSuccess()
        }
    }


}