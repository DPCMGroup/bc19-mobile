package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import org.json.JSONException
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
        return bookingList
    }

    override fun retriveBookingList() {
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
        if (response == "Prenotazioni non presenti") {

        } else {
            val jsonArray = JSONArray(response)
            bookingList = ArrayList<DataBooking>()
            for (i in 0 until jsonArray.length()) {
                val book = jsonArray.getJSONObject(i)
                val model = DataBooking()
                model.bookId = book.getInt("bookId")
                model.workName = book.getString("workName")
                model.roomName = book.getString("roomName")
                model.start = book.getString("start")
                model.end = book.getString("end")
                bookingList.add(model)
            }
            listener?.onBookingSuccess()
        }

    }


}