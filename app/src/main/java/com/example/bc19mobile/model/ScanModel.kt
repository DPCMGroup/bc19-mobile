package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONObject
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
class ScanModel : BaseModel(), ScanContract.IModel {
    private val service = Service()
    private var user: User? = null

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
            //risposta
        }


    }


}