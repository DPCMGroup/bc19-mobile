package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
class ScanModel : BaseModel(), ScanContract.IModel {
    private var listener: BookingModel.BookingListener? = null
    private val service = Service()
    private var user: User? = null
    override fun setUser(user: User?) {
        this.user = user
    }
}