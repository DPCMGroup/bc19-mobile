package com.example.bc19mobile.view.act

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.presenter.BookingPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingActivity : BaseMvpActivity<BookingContract.IPresenter>(), BookingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showBookings()
    }

    override fun registerPresenter() = BookingPresenter::class.java

    override fun getLayoutId() = R.layout.activity_booking
    override fun updateBookingView(bookings: ArrayList<DataBooking>?) {

        val bookId = findViewById<TextView>(R.id.bookId)
        val workName = findViewById<TextView>(R.id.workName)
        val roomName = findViewById<TextView>(R.id.roomName)
        val start = findViewById<TextView>(R.id.start)
        val end = findViewById<TextView>(R.id.end)

        var listView = findViewById<ListView>(R.id.prenotalist)

        for (i in 0 until (bookings?.size?.toInt() ?: 0)){
            bookings?.get(i)?._bookingId

        }

    }

}
