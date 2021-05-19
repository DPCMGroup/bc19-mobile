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
        val deserialize = response.replace("\\\"", "'").replace("\"", "").replace("'", "\"")
        val deserialize2 = "{ \"visualizzaPrenotazioni\" : " + deserialize + "}"
        //TO DO gestire l'assenza di prenotazioni
        if (deserialize2 == "Prenotazioni non presenti") {

        } else {
            val jsonObject = JSONObject(deserialize2)
            val restJson = jsonObject.getJSONArray("visualizzaPrenotazioni")
            try {
                val jsonObject = JSONObject(deserialize2)
                val jsonArray = jsonObject.getJSONArray("visualizzaPrenotazioni")
                for (i in 0 until bookingList!!.size)
                    bookingList!!.removeAt(i)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val model = DataBooking()
                    model.bookId = jsonObject1.getInt("bookId")
                    model.workName = jsonObject1.getString("workName")
                    model.roomName = jsonObject1.getString("roomName")
                    model.start = jsonObject1.getString("start")
                    model.end = jsonObject1.getString("end")

                    bookingList!!.add(model)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            listener?.onBookingSuccess()
        }

    }


}