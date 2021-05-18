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
        fun onBookingSuccess()
    }

    private var listener: BookingListener? = null
    private val service = Service()
    private var user: User? = null
    private var bookingList = ArrayList<DataBooking>()

    override fun setBookingListener(listener: BookingListener?) {
        this.listener = listener
    }

    override fun getBookingList(): ArrayList<DataBooking>? {
        getUserBooking()
        return bookingList
    }

    fun getUserBooking() {
        val jsonObject = JSONObject()
        val userId: Int = user?.getId() ?: -1
        jsonObject.put("id", userId)
        service.request(
            jsonObject,
            "user/bookings/" + userId,
            false,
            ::BookingHandle,
            ::connectionError
        )
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    private fun connectionError(ioException: IOException) {
        //gestisco gli errori con connessione
    }

    private fun BookingHandle(response: String) {
        val deserialize = response.replace("\\\"", "'").replace("\"", "").replace("'", "\"")
        //TO DO gestire l'assenza di prenotazioni
        if (deserialize == "Prenotazioni non presenti") {

        } else {

            val restJson = JSONArray(deserialize)
            for (i in 0 until restJson.length()) {
                val jsonObject = restJson.getJSONObject(i)

                this.bookingList.add(
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