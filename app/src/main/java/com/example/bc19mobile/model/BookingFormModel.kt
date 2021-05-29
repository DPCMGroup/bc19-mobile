package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
class BookingFormModel : BaseModel(), BookingFormContract.IModel{
    private var user: User? = null
    private var bookingWorkstation: BookingWorkstation? = null

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    override fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?) {
        this.bookingWorkstation = bookingWorkstation
    }

    override fun getBookingWorkstation(): BookingWorkstation? {
        return bookingWorkstation
    }

    override fun saveBookingWorkstation(
        dataTesto: String,
        inizioTesto: String,
        fineTesto: String,
        stanzaTesto: String,
        dipTesto: String
    ) {
        bookingWorkstation = BookingWorkstation(
            dataTesto,
            inizioTesto,
            fineTesto,
            stanzaTesto,
            dipTesto
        )
    }

}