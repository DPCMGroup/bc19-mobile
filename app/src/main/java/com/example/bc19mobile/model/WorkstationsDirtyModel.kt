package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyModel : BaseModel(), WorkstationsDirtyContract.IModel{
    private val service = Service()
    private var user: User? = null

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }
}