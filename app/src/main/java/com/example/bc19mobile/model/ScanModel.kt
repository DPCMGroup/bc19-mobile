package  com.example.bc19mobile.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.DataBookingToday
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
class ScanModel : BaseModel(), ScanContract.IModel {
    interface ScanListener {
        fun onScanSuccess()
        fun onScanFailure()
        fun onSanitizeSuccess()
        fun onSanitizeFailure()
    }

    private var listener: ScanModel.ScanListener? = null
    private val service = Service()
    private var user: User? = null
    private var workstation = DataWorkstation()
    private var bookingListToday = ArrayList<DataBookingToday>()

    override fun setWorkstationListener(listener: ScanListener?) {
        this.listener = listener
    }

    override fun getWorkstation(): DataWorkstation? {
        return workstation
    }

    override fun setBookingListener(listener: ScanModel.ScanListener?) {
        this.listener = listener
    }

    override fun getBookingListToday(): ArrayList<DataBookingToday>? {
        return bookingListToday
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    override fun getStatus(tag: String) {
        val jsonObject = JSONObject()
        jsonObject.put("tag", tag)
        service.request(jsonObject, "workstation/getInfo", true, ::scanHandle, ::connectionError)
    }

    fun connectionError(ioException: IOException) {
        //gestisco gli errori con connessione
    }

    fun scanHandle(response: String) {
        if (response == "4098") {
            listener?.onScanFailure()
        } else {
            workstation = DataWorkstation()
            val json = JSONObject(response)

            workstation._workId = json.getInt("workId")
            workstation._workName = json.getString("workName")
            workstation._roomName = json.getString("roomName")
            workstation._workStatus = json.getInt("workStatus")
            workstation._workSanitize = json.getInt("workSanitized")
            workstation._bookedToday = json.getInt("bookedToday")


            if(workstation._bookedToday==1) {
                val jsonBookingToday = json.getJSONArray("bookings")
                bookingListToday = ArrayList<DataBookingToday>()
                for (i in 0 until jsonBookingToday.length()) {
                    val book = jsonBookingToday.getJSONObject(i)
                    val model = DataBookingToday()
                    model.bookerId = book.getInt("bookerId")
                    model.bookerName = book.getString("bookerName")
                    model.bookerUsername = book.getString("bookerUsername")
                    model.bookerSurname = book.getString("bookerSurname")
                    model.from = book.getString("from")
                    model.to= book.getString("to")
                    bookingListToday.add(model)
                }
            }
            listener?.onScanSuccess()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSanitize(tag: String) {
        val Settings = JSONObject()
        Settings.put("idUser", user?.getId())
        Settings.put("tag", tag)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val formatted = current.format(formatter)

        Settings.put("data",formatted)

        service.request(Settings, "workstation/sanitize", true, ::sanitizeHandle, ::connectionError)
    }

    fun sanitizeHandle(response: String) {
        if (response == "2052") {
            listener?.onSanitizeSuccess()
        } else {
            listener?.onSanitizeFailure()
        }
    }


    }