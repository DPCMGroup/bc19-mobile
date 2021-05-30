package  com.example.bc19mobile.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.data.DataDirtyRooms
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
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyModel : BaseModel(), WorkstationsDirtyContract.IModel {

    interface WorkstationsDirtyListener {
        fun onWorkstationsSuccess()
        fun onWorkstationsFailure()
        fun onSanitizeSuccess()
        fun onSanitizeFailure()
    }


    private var listener: WorkstationsDirtyModel.WorkstationsDirtyListener? = null
    private val service = Service()
    private var user: User? = null
    private var workstationsDirtyList = ArrayList<DataDirtyWorkstations>()

    override fun getWorkstations() {
        service.request(
            null,
            "workstation/dirty/list",
            false,
            ::WorkstationsHandle,
            ::connectionError
        )
    }

    override fun setWorkstationsDirtyListener(listener: WorkstationsDirtyModel.WorkstationsDirtyListener?) {
        this.listener = listener
    }

    override fun getDirtyWorkstations(): ArrayList<DataDirtyWorkstations>? {
        return workstationsDirtyList
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    private fun connectionError(ioException: IOException) {
        //gestisco gli errori con connessione
    }

    private fun WorkstationsHandle(response: String) {
        if (response != "4098" && response !="4099" && response!="[]") {
            val jsonArray = JSONArray(response)
            workstationsDirtyList = ArrayList<DataDirtyWorkstations>()
            for (i in 0 until jsonArray.length()) {
                val workstation = jsonArray.getJSONObject(i)
                val model = DataDirtyWorkstations()
                model.id = workstation.getInt("id")
                model.tag = workstation.getString("tag")
                model.workstationName = workstation.getString("workstationname")
                model.xWorkstation = workstation.getInt("xworkstation")
                model.yWorkstation = workstation.getInt("yworkstation")
                model.idRoom = workstation.getInt("idroom")
                model.archived = workstation.getInt("archived")
                model.state = workstation.getInt("state")
                model.sanitized = workstation.getInt("sanitized")

                workstationsDirtyList.add(model)
            }
            listener?.onWorkstationsSuccess()
        } else {
            listener?.onWorkstationsFailure()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun sanitizeWorkstation(tag: String) {
        val Settings = JSONObject()
        Settings.put("idUser", user?.getId())
        Settings.put("tag", tag)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val formatted = current.format(formatter)

        Settings.put("data", formatted)

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