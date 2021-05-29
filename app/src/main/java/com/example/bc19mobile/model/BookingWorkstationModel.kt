package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationModel : BaseModel(), BookingWorkstationContract.IModel{
    private val service = Service()
    private var user: User? = null
    private var bookingWorkstation: BookingWorkstation? = null

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

    override fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?) {
        this.bookingWorkstation= bookingWorkstation
    }

    override fun getBookingWorkstation(): BookingWorkstation? {
        return bookingWorkstation
    }
}