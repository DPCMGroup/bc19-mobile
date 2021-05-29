package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toolbar
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.presenter.BookingWorkstationPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationActivity : BaseMvpActivity<BookingWorkstationContract.IPresenter>() , BookingWorkstationContract.IView {

    override fun registerPresenter() = BookingWorkstationPresenter::class.java

    override fun getLayoutId() = R.layout.activity_bookingworkstation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().saveBookingWorkstation(intent.extras?.get("bookingWorkstation") as BookingWorkstation)
            getPresenter().showAvailability(
               "",//dataTesto.text.toString(),
               "", //inizioTesto.text.toString(),
                "",//fineTesto.text.toString(),
                "", //stanzaTesto.text.toString(),
               ""// dipTesto.text.toString()
            )
        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val itemToHide = menu?.findItem(R.id.nav_bookingForm)
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


            R.id.nav_booking -> {
                val user = getPresenter().getUser()
                goActivity(
                    BookingActivity::class.java, bundleOf(
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
    }

}
