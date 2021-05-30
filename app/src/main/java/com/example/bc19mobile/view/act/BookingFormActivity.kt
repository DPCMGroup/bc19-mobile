package com.example.bc19mobile.view.act

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.presenter.BookingFormPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.WorkstationsDirtyAdapter
import java.text.SimpleDateFormat

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
class BookingFormActivity : BaseMvpActivity<BookingFormContract.IPresenter>(),
    BookingFormContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        val SelezionaData = findViewById<ImageButton>(R.id.SelezionaData)
        val dataTesto = findViewById<TextView>(R.id.dataTesto)

        val now = java.util.Calendar.getInstance()
        var day = now.get(java.util.Calendar.DAY_OF_MONTH)
        var month = now.get(Calendar.MONTH)
        var year = now.get(java.util.Calendar.YEAR)

        SelezionaData.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val monthOfYear = monthOfYear + 1
                    // Display Selected date in textbox
                    dataTesto.setText("" + year  + "-" + monthOfYear + "-" + dayOfMonth)

                },
                year,
                month,
                day
            )

            dpd.show()
        }

        val SelezionaInizio = findViewById<ImageButton>(R.id.SelezionaInizio)
        val inizioTesto = findViewById<TextView>(R.id.inizioTesto)

        SelezionaInizio.setOnClickListener {
            val cal = java.util.Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(java.util.Calendar.HOUR_OF_DAY, hour)
                cal.set(java.util.Calendar.MINUTE, minute)
                inizioTesto.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(java.util.Calendar.HOUR_OF_DAY),
                cal.get(java.util.Calendar.MINUTE),
                true
            ).show()
        }

        val SelezionaFine = findViewById<ImageButton>(R.id.SelezionaFine)
        val fineTesto = findViewById<TextView>(R.id.fineTesto)

        SelezionaFine.setOnClickListener {
            val cal = java.util.Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(java.util.Calendar.HOUR_OF_DAY, hour)
                cal.set(java.util.Calendar.MINUTE, minute)
                fineTesto.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(java.util.Calendar.HOUR_OF_DAY),
                cal.get(java.util.Calendar.MINUTE),
                true
            ).show()
        }
        val stanzaTesto = findViewById<EditText>(R.id.stanzaTesto)
        val dipTesto = findViewById<EditText>(R.id.dipTesto)
        val cerca = findViewById<Button>(R.id.cerca)

        cerca.setOnClickListener {
            getPresenter().saveBookingWorkstation(dataTesto.text.toString(),inizioTesto.text.toString(),fineTesto.text.toString(),stanzaTesto.text.toString(),dipTesto.text.toString())
            var bookingWorkstation =getPresenter().getBookingWorkstation()
            var user = getPresenter().getUser()
            goActivity(
                BookingWorkstationActivity::class.java, bundleOf(
                    "user" to user, "bookingWorkstation" to bookingWorkstation
                )
            )

        }
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

    override fun registerPresenter() = BookingFormPresenter::class.java

    override fun getLayoutId() = R.layout.activity_bookingform

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }


}
