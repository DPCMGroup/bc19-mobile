package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
class BookingFormModel : BaseModel(), BookingFormContract.IModel{
    private val service = Service()
    private var user: User? = null

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    override fun getAvailability(
        date: String,
        startTime: String,
        endTime: String,
        room: String,
        colleague: String
    ) {

    }
}