package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.os.bundleOf
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val itemToHide = menu?.findItem(R.id.nav_booking)
        itemToHide?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_tag -> {
                val user = getPresenter().getUser()
                goActivity(
                    ScanActivity::class.java, bundleOf(
                        "user" to user
                    )
                )
                return true
            }


            R.id.nav_bookingForm -> {
                val user = getPresenter().getUser()
                goActivity(
                    BookingFormActivity::class.java, bundleOf(
                        "user" to user
                    )
                )
                return true
            }

            R.id.nav_guida -> {
                val user = getPresenter().getUser()
                goActivity(
                    GuideActivity::class.java, bundleOf(
                        "user" to user
                    )
                )
                return true
            }
            R.id.logout -> {
                var moveIntent = Intent(this, LoginActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
        bookingError = findViewById(R.id.bookingError)
    }

    override fun initData() {
        super.initData()
    }

    override fun registerPresenter() = BookingPresenter::class.java

    fun deleteBooking(id: Int) {
        getPresenter().deleteBooking(id)
    }

    override fun getLayoutId() = R.layout.activity_booking
    override fun updateBookingView(bookings: ArrayList<DataBooking>?) {

        var listView = findViewById<ListView>(R.id.prenotalist)

        var adapter = BookingAdapter(this, R.layout.row, bookings!!)
        adapter.attachDelete(::deleteBooking)
        listView.adapter = adapter
    }

    override fun callError() {
        val bookErr = findViewById<TextView>(R.id.bookingError)
        runOnUiThread {
            bookErr.setVisibility(View.VISIBLE)
        }
    }

    override fun callDeleteError() {
        Toast.makeText(applicationContext, "Eliminazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun updateBookingDeleteView(bookings: ArrayList<DataBooking>?) {
        var user= getPresenter().getUser()
        goActivity(
            BookingActivity::class.java, bundleOf(
                "user" to user
            )
        )
        Toast.makeText(applicationContext, "Eliminazione eseguita con successo!", Toast.LENGTH_SHORT)
            .show()
    }

}



