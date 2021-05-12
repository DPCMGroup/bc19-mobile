package  com.example.bc19mobile.model

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.bc19mobile.model.service.Service
import com.example.bc19mobile.contract.LoginContract
import com.example.bc19mobile.data.User
import mvp.ljb.kt.model.BaseModel
import org.json.JSONObject

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {

    private val service = Service()
    private var user: User? = null

    override fun sendLogin(username: String, password: String) {

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        service.request(jsonObject, "login", true, ::loginHandle)
    }

    fun loginHandle(response: String) {

//TO DO sistemare
        if (response == "\"No user found\"") {

        } else {
            val restJson = JSONObject(response)

            user = User(
                restJson.getInt("id"),
                restJson.getString("username"),
                restJson.getString("password"),
                restJson.getInt("type")
            )


        }


    }
}