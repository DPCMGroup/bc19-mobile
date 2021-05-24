package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.CleanContract
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
class CleanModel : BaseModel(), CleanContract.IModel{

    private var listener: LoginModel.LoginListener? = null
    private val service = Service()

    override fun getRooms() {
        TODO("Not yet implemented")
    }

    override fun getWorkstations() {
        TODO("Not yet implemented")
    }
}