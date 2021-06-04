package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.CleanContract
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
class CleanModel : BaseModel(), CleanContract.IModel {

    private var user: User? = null

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

}
