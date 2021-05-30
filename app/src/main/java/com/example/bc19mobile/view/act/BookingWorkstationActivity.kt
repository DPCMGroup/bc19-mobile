package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.presenter.BookingWorkstationPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataBookableWorkstation
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.BookableWorkstationAdapter
import com.example.bc19mobile.tools.WorkstationsDirtyAdapter

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
        getPresenter().showAvailability()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        menu?.findItem(R.id.nav_bookingForm)?.setVisible(false)
        menu?.findItem(R.id.nav_sanitize)?.setVisible(false)
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

    fun bookWorkstation(idWorkstation: Int){
        getPresenter().bookWorkstation(idWorkstation)
    }

    override fun updateWorkstationsBookableView(workstationsBookable: ArrayList<DataBookableWorkstation>?) {
        var listView = findViewById<ListView>(R.id.bookingWorkstationlist)
        var adapter =
            BookableWorkstationAdapter(this, R.layout.rowbookingworkstation, workstationsBookable!!)
        adapter.attachBooking(::bookWorkstation)
        listView.adapter = adapter
    }



    override fun callErrorBookableWorkstation() {
        var workstationError =findViewById<TextView>(R.id.bookingWorkstationError)
        workstationError?.setVisibility(View.VISIBLE)
    }

    override fun callBookableFailure() {
        Toast.makeText(applicationContext, "Prenotazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callBookableSuccess() {
        val user = getPresenter().getUser()
        goActivity(
            BookingActivity::class.java, bundleOf(
                "user" to user
            )
        )
        Toast.makeText(applicationContext, "Prenotazione eseguita con successo!", Toast.LENGTH_SHORT)
            .show()
    }

}

