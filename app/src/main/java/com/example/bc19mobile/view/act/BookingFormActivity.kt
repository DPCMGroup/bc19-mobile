package com.example.bc19mobile.view.act

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.presenter.BookingFormPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import java.sql.Timestamp

import java.text.SimpleDateFormat
import java.time.LocalTime

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
@Suppress("DEPRECATION")
class BookingFormActivity : BaseMvpActivity<BookingFormContract.IPresenter>(),
    BookingFormContract.IView {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)

        getPresenter().getRooms()

        val SelezionaData = findViewById<ImageButton>(R.id.SelezionaData)
        val dataTesto = findViewById<TextView>(R.id.dataTesto)

        val now = java.util.Calendar.getInstance()
        var day = now.get(java.util.Calendar.DAY_OF_MONTH)
        var month = now.get(Calendar.MONTH)
        var year = now.get(java.util.Calendar.YEAR)
        enableBtnSearch()

        SelezionaData.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val monthOfYear = monthOfYear + 1
                    // Display Selected date in textbox
                    dataTesto.setText("" + year + "-" + monthOfYear + "-" + dayOfMonth)

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
        val spinner = findViewById<Spinner>(R.id.spinner)
        val dipTesto = findViewById<EditText>(R.id.dipTesto)
        val cerca = findViewById<Button>(R.id.cerca)

        dataTesto.doOnTextChanged() { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            enableBtnSearch()
        }

        inizioTesto.doOnTextChanged() { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            enableBtnSearch()
            showError()
        }

        fineTesto.doOnTextChanged() { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            enableBtnSearch()
            showError()
        }

        cerca.setOnClickListener {
            val stanzaTesto: String = spinner.getSelectedItem().toString()
            getPresenter().saveBookingWorkstation(
                dataTesto.text.toString(),
                inizioTesto.text.toString(),
                fineTesto.text.toString(),
                stanzaTesto,
                dipTesto.text.toString()
            )
            var bookingWorkstation = getPresenter().getBookingWorkstation()
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
        menu?.findItem(R.id.nav_sanitize)?.setVisible(false)
        menu?.findItem(R.id.nav_bookingForm)?.setVisible(false)
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

    override fun callErrorRooms() {
        Toast.makeText(
            applicationContext,
            "Errore!",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    fun append(arr: Array<String?>, element: String): Array<String?> {
        val list: MutableList<String?> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    override fun updateRoomsView(rooms: ArrayList<DataDirtyRooms>?) {
        val size: Int? = rooms?.size
        var languages = arrayOf(rooms?.get(0)?.roomName)
        for (i in 1..size!! - 1) {
            languages = rooms.get(i).roomName?.let { append(languages, it) }!!
        }


        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun enableBtnSearch() {
        val start = findViewById<EditText>(R.id.inizioTesto).text.isNotBlank()
        val end = findViewById<EditText>(R.id.fineTesto).text.isNotBlank()
        val date = findViewById<EditText>(R.id.dataTesto).text.isNotBlank()

        val searchBtn = findViewById<Button>(R.id.cerca)
        searchBtn?.isEnabled = start && end && date
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkTime(): Boolean {
        val start = findViewById<EditText>(R.id.inizioTesto).text
        val end = findViewById<EditText>(R.id.fineTesto).text
        return Timestamp.parse(start.toString()) < Timestamp.parse(end.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showError() {
        val start = findViewById<EditText>(R.id.inizioTesto).text
        val end = findViewById<EditText>(R.id.fineTesto).text
        /*
        if (start.isNotBlank() && end.isNotBlank() && !checkTime())
            Toast.makeText(
                applicationContext,
                "L'ora di inizio non può essere superiore all'ora di fine",
                Toast.LENGTH_SHORT
            ).show()
         */
        }


}
