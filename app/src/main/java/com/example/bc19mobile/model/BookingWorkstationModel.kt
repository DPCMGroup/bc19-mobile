package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataBookableWorkstation
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationModel : BaseModel(), BookingWorkstationContract.IModel{
    interface BookingWorkstationListener {
        fun onBookingWorkstationSuccess()
        fun onBookingWorkstationFailure()
        fun onBookSuccess()
        fun onBookFailure()
    }

    private var listener: BookingWorkstationListener? = null
    private val service = Service()
    private var user: User? = null
    private var bookingWorkstation: BookingWorkstation? = null
    private var workstationsBookableList = ArrayList<DataBookableWorkstation>()

    override fun setBookingWorkstationListener(listener: BookingWorkstationListener?) {
        this.listener = listener
    }

    override fun getBookableWorkstations(): ArrayList<DataBookableWorkstation>? {
        return workstationsBookableList
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    override fun getAvailability() {
        var bookingWorkstationRoom: Int?= bookingWorkstation?.getstanzaTesto()?.toInt()
        var bookingWorkstationStart: String? = bookingWorkstation?.getinizioTesto()
        var bookingWorkstationEnd: String?= bookingWorkstation?.getfineTesto()
        var bookingWorkstationDate: String?= bookingWorkstation?.getdataTesto()

        val jsonObject = JSONObject()
        jsonObject.put("idRoom", bookingWorkstationRoom)
        jsonObject.put("startTime", bookingWorkstationDate +" " +bookingWorkstationStart )
        jsonObject.put("endTime", bookingWorkstationDate +" " +bookingWorkstationEnd )
        service.request(jsonObject, "workstation/bookable/list", true, ::bookableHandle, ::connectionError)
    }

    fun connectionError(e: IOException) {
        //gestisto gli errori con connessione
    }

    fun bookableHandle(response: String) {
        if (response == "[]" || response =="4098" || response =="4097") {
            listener?.onBookingWorkstationFailure()
        } else {
            val jsonArray = JSONArray(response)
            workstationsBookableList = ArrayList<DataBookableWorkstation>()
            for (i in 0 until jsonArray.length()) {
                val workstation = jsonArray.getJSONObject(i)
                val model = DataBookableWorkstation()
                model.id = workstation.getInt("id")
                model.tag = workstation.getString("tag")
                model.workstationName = workstation.getString("workstationname")
                model.xWorkstation = workstation.getInt("xworkstation")
                model.yWorkstation = workstation.getInt("yworkstation")
                model.idRoom = workstation.getInt("idroom")
                model.archived = workstation.getInt("archived")
                model.state = workstation.getInt("state")
                model.sanitized = workstation.getInt("sanitized")

                workstationsBookableList.add(model)
            }
            listener?.onBookingWorkstationSuccess()

        }
    }

    override fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?) {
        this.bookingWorkstation= bookingWorkstation
    }

    override fun getBookingWorkstation(): BookingWorkstation? {
        return bookingWorkstation
    }

    override fun bookWorkstation(idWorkstation: Int) {
        val Settings = JSONObject()

        Settings.put("idworkstation", idWorkstation)
        Settings.put("iduser", user?.getId())
        var bookingWorkstationStart: String? = bookingWorkstation?.getinizioTesto()
        var bookingWorkstationEnd: String?= bookingWorkstation?.getfineTesto()
        var bookingWorkstationDate: String?= bookingWorkstation?.getdataTesto()
        Settings.put("starttime", bookingWorkstationDate +" " +bookingWorkstationStart )
        Settings.put("endtime", bookingWorkstationDate +" " +bookingWorkstationEnd )
        service.request(Settings, "booking/insert", true, ::BookingWorkstationHandle, ::connectionError)
    }

    fun BookingWorkstationHandle(response: String){
        if(response=="32772"){
            listener?.onBookSuccess()
        }
        else{
            listener?.onBookFailure()
        }
    }
}