package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
class ScanModel : BaseModel(), ScanContract.IModel {
    interface ScanListener {
        fun onScanSuccess()
    }

    private var listener: ScanModel.ScanListener? = null
    private val service = Service()
    private var user: User? = null
    private var workstation = DataWorkstation()

    override fun setWorkstationListener(listener: ScanListener?) {
        this.listener = listener
    }

    override fun getWorkstation(): DataWorkstation? {
        return workstation
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
            //fare errore
        } else {
            workstation = DataWorkstation()
            val json = JSONObject(response)

            workstation._workId = json.getInt("workId")
            workstation._workName = json.getString("workName")
            workstation._roomName = json.getString("roomName")
            workstation._workStatus = json.getInt("workStatus")
            workstation._workSanitize = json.getInt("workSanitized")
            workstation._bookedToday = json.getInt("bookedToday")


            listener?.onScanSuccess()
        }


    }


}