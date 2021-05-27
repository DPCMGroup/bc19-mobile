package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyModel : BaseModel(), WorkstationsDirtyContract.IModel{

    interface WorkstationsDirtyListener {
        fun onWorkstationsSuccess()
        fun onWorkstationsFailure()
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
        if (response == "codice di errore") {
            listener?.onWorkstationsFailure()
        } else {
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
        }
    }
}