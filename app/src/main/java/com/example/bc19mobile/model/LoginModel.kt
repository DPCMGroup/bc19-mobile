package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.LoginContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONObject
import java.io.IOException


/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {

    interface LoginListener {
        fun onLoginSuccess()
    }

    private var listener: LoginListener? = null
    private val service = Service()
    private var user: User? = null

    override fun setLoginListener(listener: LoginListener?) {
        this.listener = listener
    }

    override fun sendLogin(username: String, password: String) {

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        service.request(jsonObject, "user/login", true, ::loginHandle, ::connectionError)
    }

    override fun getUser(): User? {
        return user
    }

    fun connectionError(e: IOException) {
        //gestisto gli errori con connessione
    }

    fun loginHandle(response: String) {
        if (response == "\"No user found\"") {

        } else {
            val restJson = JSONObject(response)

            user = User(
                restJson.getInt("id"),
                restJson.getString("username"),
                restJson.getString("password"),
                restJson.getInt("type")
            )

            listener?.onLoginSuccess()
        }
    }
}