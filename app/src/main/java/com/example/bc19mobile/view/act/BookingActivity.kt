package com.example.bc19mobile.view.act

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.presenter.BookingPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.BookingAdapter


/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingActivity : BaseMvpActivity<BookingContract.IPresenter>(), BookingContract.IView {

    private var bookingError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showBookings()
    }

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
        bookingError= findViewById(R.id.bookingError)
    }
    override fun initData() {
        super.initData()
        bookingError?.setVisibility(View.INVISIBLE)
    }

    override fun registerPresenter() = BookingPresenter::class.java

    override fun getLayoutId() = R.layout.activity_booking
    override fun updateBookingView(bookings: ArrayList<DataBooking>?) {

        var listView = findViewById<ListView>(R.id.prenotalist)


        listView.adapter = BookingAdapter(this, R.layout.row, bookings!!)
    }


    override fun callError() {
        bookingError?.setVisibility(View.VISIBLE)
    }

}



