package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.LoginContract
import mvp.ljb.kt.model.BaseModel
import org.json.JSONObject

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginModel : BaseModel(), LoginContract.IModel {
    override fun sendLogin(username: String, password: String) {
        TODO("Not yet implemented")
    }

    //ho utilizzato questo url per semplicità di test
    //val url_json="https://run.mocky.io/v3/f5d1fded-df4c-433f-8be6-b880014c13e3"

/*
    private val client = httpClient(url_json)
    override fun loginModel(username: String, password: String) {
        val json : JSONObject = createJsonObjact()
        client.login(json, ::manageOutput)
    }

 */
}