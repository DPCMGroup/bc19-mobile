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
        fun onLoginFailure()
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
        service.request(jsonObject, "user/login/mobile", true, ::loginHandle, ::connectionError)
    }

    override fun getUser(): User? {
        return user
    }

    fun connectionError(e: IOException) {
        //manage connection error
    }

    fun loginHandle(response: String) {
        if (response == "16386") {
            listener?.onLoginFailure()
        } else {
            val restJson = JSONObject(response)

            user = User(
                restJson.getInt("id"),
                restJson.getString("username"),
                restJson.getString("password"),
                restJson.getInt("type"),
                -1
            )

            listener?.onLoginSuccess()
        }
    }
}