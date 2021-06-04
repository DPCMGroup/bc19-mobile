package com.example.bc19mobile.view.act


import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.example.bc19mobile.NFC.NdefMessageParser
import com.example.bc19mobile.NFC.ParsedNdefRecord
import com.example.bc19mobile.R
import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.DataBookingToday
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.presenter.ScanPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.ArrayList

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/

class ScanActivity : BaseMvpActivity<ScanContract.IPresenter>(), ScanContract.IView {
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var text: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        var username: String? = getPresenter().getUser()?.getUsername()


        val tagId = findViewById<TextView>(R.id.tagId_txt)
        val oraEdit = findViewById<EditText>(R.id.OraEdit)
        val igienizza = findViewById<Button>(R.id.nav_sanitize)
        runOnUiThread {
            igienizza.isEnabled = false
        }

        igienizza.setOnClickListener {
            tagId?.text?.toString()?.let { it1 -> getPresenter().makeSanitize(it1) }
        }

        val startOccupation = findViewById<Button>(R.id.startOccupation)


        runOnUiThread {
            startOccupation.isEnabled = false
        }
        startOccupation.setOnClickListener {
            getPresenter().getTimetoNext()
        }
        val endOccupation = findViewById<Button>(R.id.endOccupation)
        runOnUiThread {
            endOccupation.isEnabled = false
        }
        endOccupation.setOnClickListener {
            tagId?.text?.toString()?.let { it2 -> getPresenter().endOccupation(it2) }
        }



        text = findViewById<View>(R.id.text) as TextView

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "No NFC", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, this.javaClass)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item, menu)

        menu?.findItem(R.id.nav_tag)?.setVisible(false)
        menu?.findItem(R.id.nav_sanitize)?.setVisible(false)

        return true
    }

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookingForm -> {
                var user = getPresenter().getUser()
                goActivity(
                    BookingFormActivity::class.java, bundleOf(
                        "user" to user
                    )
                )
                return true
            }
            R.id.nav_guida -> {
                var user = getPresenter().getUser()
                goActivity(
                    GuideActivity::class.java, bundleOf(
                        "user" to user
                    )
                )
                return true
            }
            R.id.nav_booking -> {
                var user = getPresenter().getUser()
                goActivity(
                    BookingActivity::class.java, bundleOf(
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


    @SuppressLint("MissingSuperCall")
    override fun onResume() {
        super.onResume()
        if (nfcAdapter != null) {
            if (!nfcAdapter!!.isEnabled) showWirelessSettings()
            nfcAdapter!!.enableForegroundDispatch(this, pendingIntent, null, null)
        }
    }


    @SuppressLint("MissingSuperCall")
    override fun onPause() {
        super.onPause()
        if (nfcAdapter != null) {
            nfcAdapter!!.disableForegroundDispatch(this)
        }
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        resolveIntent(intent)

    }

    private fun resolveIntent(intent: Intent) {

        val action = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            val msgs: Array<NdefMessage?>
            if (rawMsgs != null) {
                msgs = arrayOfNulls(rawMsgs.size)
                for (i in rawMsgs.indices) {
                    msgs[i] = rawMsgs[i] as NdefMessage
                }
            } else {
                val empty = ByteArray(0)
                val id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)
                val tag = intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG) as Tag
                val payload = dumpTagData(tag).toByteArray()
                val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload)
                val msg = NdefMessage(arrayOf(record))
                msgs = arrayOf(msg)

            }
            displayMsgs(msgs)
        }
    }

    private fun displayMsgs(msgs: Array<NdefMessage?>?) {
        if (msgs == null || msgs.size == 0) return
        val builder = StringBuilder()
        val records: List<ParsedNdefRecord> =
            msgs[0]?.let { NdefMessageParser.parse(it) } as List<ParsedNdefRecord>
        for (i in 0 until records.size) {
            builder.append(records[i].str()).append("\n")
        }
        text!!.text = builder.toString()
    }

    private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }

    private fun dumpTagData(tag: Tag): String {
        val sb = StringBuilder()
        val id = tag.id
        val tag = toHex(id)
        val tagId = findViewById<TextView>(R.id.tagId_txt)
        tagId.text = tag
        getPresenter().scanTagNFC(tag)
        return sb.toString()
    }

    private fun toHex(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (i in bytes.indices.reversed()) {
            val b: Int = bytes[i].toInt() and 0xff
            if (b < 0x10) sb.append('0')
            sb.append(Integer.toHexString(b))
            if (i > 0) {
                sb.append(" ")
            }
        }
        return sb.toString()
    }


    override fun getLayoutId() = R.layout.activity_scan


    override fun registerPresenter() = ScanPresenter::class.java

    override fun updateScanView(
        workstation: DataWorkstation?,
        bookings: ArrayList<DataBookingToday>?
    ) {
        val igienizza = findViewById<Button>(R.id.nav_sanitize)
        val startOccupation = findViewById<Button>(R.id.startOccupation)
        val endOccupation = findViewById<Button>(R.id.endOccupation)
        val oraText = findViewById<TextView>(R.id.OraText)
        val oraEdit = findViewById<EditText>(R.id.OraEdit)

        val message = findViewById<TextView>(R.id.message_txt)
        igienizza.setVisibility(View.INVISIBLE)
        startOccupation.setVisibility(View.INVISIBLE)
        oraText.setVisibility(View.INVISIBLE)
        oraEdit.setVisibility(View.INVISIBLE)
        endOccupation.setVisibility(View.INVISIBLE)
        message.setVisibility(View.INVISIBLE)

        val nomepostazione = findViewById<TextView>(R.id.nomepostazione)
        val stato = findViewById<TextView>(R.id.stato)
        val evprenotazioni = findViewById<TextView>(R.id.evprenotazioni)
        nomepostazione.text = workstation?._workName


        if (workstation?._workStatus == 0 && workstation?._workSanitize == 1) {
            runOnUiThread {
                stato.text = "Libera e Igienizzata"
            }

            runOnUiThread {
                startOccupation.isEnabled = true
            }

            runOnUiThread {
                startOccupation.setVisibility(View.VISIBLE)
            }

            runOnUiThread {
                oraEdit.setVisibility(View.VISIBLE)
            }
            runOnUiThread {
                oraText.setVisibility(View.VISIBLE)
            }
        } else if (workstation?._workStatus == 0 && workstation?._workSanitize == 0) {
            runOnUiThread {
                stato.text = "Libera e non Igienizzata"
            }
            runOnUiThread {
                igienizza.isEnabled = true
            }

            runOnUiThread {
                igienizza.setVisibility(View.VISIBLE)
            }

        } else if (workstation?._workStatus == 1 && workstation?._workSanitize == 0) {
            runOnUiThread {
                stato.text = "Occupata"
            }
            runOnUiThread {
                message.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                startOccupation.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                startOccupation.isEnabled = false
            }
            runOnUiThread {
                oraEdit.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                oraText.setVisibility(View.INVISIBLE)
            }

            runOnUiThread {
                endOccupation.setVisibility(View.VISIBLE)
            }
            runOnUiThread {
                endOccupation.isEnabled = true
            }


        } else if (workstation?._workStatus == 1 && workstation?._workSanitize == 1) {
            var username: String? = getPresenter().getUser()?.getUsername()
            runOnUiThread {
                stato.text = "Occupata"
            }
            runOnUiThread {
                message.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                startOccupation.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                startOccupation.isEnabled = false
            }
            runOnUiThread {
                oraEdit.setVisibility(View.INVISIBLE)
            }
            runOnUiThread {
                oraText.setVisibility(View.INVISIBLE)
            }

            runOnUiThread {
                endOccupation.setVisibility(View.VISIBLE)
            }
            runOnUiThread {
                endOccupation.isEnabled = true
            }

        } else if (workstation?._workStatus == 2 && workstation?._workSanitize == 1) {

            runOnUiThread { stato.text = "Prenotata e Igienizzata" }
            runOnUiThread { message.setVisibility(View.INVISIBLE) }
        } else if (workstation?._workStatus == 2 && workstation?._workSanitize == 0) {
            runOnUiThread { stato.text = "Prenotata e non Igienizzata" }
            runOnUiThread { message.setVisibility(View.INVISIBLE) }
            runOnUiThread {
                igienizza.isEnabled = true
            }

            runOnUiThread {
                igienizza.setVisibility(View.VISIBLE)
            }
        } else if (workstation?._workStatus == 3 && workstation?._workSanitize == 1) {
            runOnUiThread { stato.text = "Guasta e Igienizzata" }
            runOnUiThread { message.setVisibility(View.VISIBLE) }
        } else if (workstation?._workStatus == 3 && workstation?._workSanitize == 0) {
            runOnUiThread { stato.text = "Guasta e non Igienizzata" }
            runOnUiThread { message.setVisibility(View.VISIBLE) }
            runOnUiThread {
                igienizza.isEnabled = true
            }

            runOnUiThread {
                igienizza.setVisibility(View.VISIBLE)
            }
        }

        if (workstation?._bookedToday == 1) {
            var username: String? = getPresenter().getUser()?.getUsername()
            if (bookings?.get(bookings?.size!! - 1)?.bookerUsername == username) {
                runOnUiThread {
                    evprenotazioni.text =
                        "Postazione prenotata da te dalle " + bookings?.get(
                            bookings?.size!! - 1
                        )?.from + " fino alle " + bookings?.get(
                            bookings?.size!! - 1
                        )?.to
                }
                runOnUiThread {
                    message.setVisibility(View.INVISIBLE)
                }
                runOnUiThread {
                    startOccupation.setVisibility(View.VISIBLE)
                }
                runOnUiThread {
                    startOccupation.isEnabled = true
                }

                if (bookings?.size!! > 1 && bookings?.get(bookings?.size!! - 2)?.bookerUsername != username) {
                    runOnUiThread {
                        evprenotazioni.text =
                            "Prenotata da te. Attenzione ci sono altre prenotazioni dalle " + bookings?.get(
                                bookings?.size!! - 2
                            )?.from
                    }
                }
            } else {
                evprenotazioni.text =
                    "Prenotata da " + bookings?.get(bookings?.size!! - 1)?.bookerName + " " + bookings?.get(
                        bookings?.size!! - 1
                    )?.bookerSurname + " dalle " + bookings?.get(bookings?.size!! - 1)?.from + " alle " + bookings?.get(
                        bookings?.size!! - 1
                    )?.to
            }
            evprenotazioni.setVisibility(View.VISIBLE)
        } else {
            evprenotazioni.setVisibility(View.INVISIBLE)
        }



        nomepostazione.setVisibility(View.VISIBLE)
        stato.setVisibility(View.VISIBLE)


        val intro = findViewById<TextView>(R.id.intro_txt)
        intro.setVisibility(View.VISIBLE)

    }

    override fun callScanError() {
        Toast.makeText(applicationContext, "Tag non riconosciuto!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callSanitizeError() {
        Toast.makeText(applicationContext, "Igienizzazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callSanitizeOk() {
        Toast.makeText(applicationContext, "Igienizzazione completata!", Toast.LENGTH_SHORT)
            .show()

        val igienizza = findViewById<Button>(R.id.nav_sanitize)
        val startOccupation = findViewById<Button>(R.id.startOccupation)
        val oraText = findViewById<TextView>(R.id.OraText)
        val oraEdit = findViewById<EditText>(R.id.OraEdit)

        runOnUiThread {
            igienizza.isEnabled = true
        }
        val message = findViewById<TextView>(R.id.message_txt)
        val evprenotazioni = findViewById<TextView>(R.id.evprenotazioni)
        val stato = findViewById<TextView>(R.id.stato)
        if (stato.text == "Libera e non Igienizzata") {
            runOnUiThread {
                stato.text = "Libera e Igienizzata"
            }
            runOnUiThread {
                message.setVisibility(View.INVISIBLE)
            }

        }


        runOnUiThread {
            igienizza.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            igienizza.isEnabled = false
        }
        if (stato.text == "Prenotata e non Igienizzata") {
            runOnUiThread {
                stato.text = "Prenotata e Igienizzata"
            }
            if (evprenotazioni.text == "Prenotata da te. Attenzione ci sono altre prenotazioni!" || evprenotazioni.text == "Postazione prenotata da te") {
                runOnUiThread {
                    message.setVisibility(View.INVISIBLE)
                }

            }

        }
        if (stato.text == "Guasta e non Igienizzata") {
            runOnUiThread {
                stato.text = "Guasta e Igienizzata"
            }
        }

        runOnUiThread {
            igienizza.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            igienizza.isEnabled = false
        }
        runOnUiThread {
            startOccupation.isEnabled = true
        }
        runOnUiThread {
            startOccupation.setVisibility(View.VISIBLE)
        }
        runOnUiThread {
            oraEdit.setVisibility(View.VISIBLE)
        }
        runOnUiThread {
            oraText.setVisibility(View.VISIBLE)
        }

    }


    override fun callStartOccupationError() {
        Toast.makeText(applicationContext, "Inizio occupazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callStartOccupationOk() {
        Toast.makeText(applicationContext, "Inizio occupazione!", Toast.LENGTH_SHORT)
            .show()

        val startOccupation = findViewById<Button>(R.id.startOccupation)
        val endOccupation = findViewById<Button>(R.id.endOccupation)
        val oraText = findViewById<TextView>(R.id.OraText)
        val oraEdit = findViewById<EditText>(R.id.OraEdit)

        runOnUiThread {
            startOccupation.isEnabled = true
        }
        val messageEndOccupation = findViewById<TextView>(R.id.messageEndOccupation)

        val evprenotazioni = findViewById<TextView>(R.id.evprenotazioni)
        val stato = findViewById<TextView>(R.id.stato)
        if (stato.text == "Libera e Igienizzata") {
            runOnUiThread {
                stato.text = "Occupata"
            }
            runOnUiThread {
                messageEndOccupation.setVisibility(View.INVISIBLE)
            }

            runOnUiThread {
                endOccupation.isEnabled = true
            }
            runOnUiThread {
                endOccupation.setVisibility(View.VISIBLE)
            }
        }


        runOnUiThread {
            startOccupation.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            startOccupation.isEnabled = false
        }
        runOnUiThread {
            oraEdit.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            oraText.setVisibility(View.INVISIBLE)
        }
        if (stato.text == "Prenotata e Igienizzata") {
            runOnUiThread {
                stato.text = "Occupata"
            }
            if (evprenotazioni.text == "Prenotata da te. Attenzione ci sono altre prenotazioni!" || evprenotazioni.text == "Postazione prenotata da te") {
                runOnUiThread {
                    messageEndOccupation.setVisibility(View.INVISIBLE)
                }
            }

        }

        runOnUiThread {
            startOccupation.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            startOccupation.isEnabled = false
        }
        runOnUiThread {
            oraEdit.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            oraText.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            endOccupation.isEnabled = true
        }
        runOnUiThread {
            endOccupation.setVisibility(View.VISIBLE)
        }

    }

    override fun callEndOccupationError() {
        Toast.makeText(applicationContext, "Fine occupazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callEndOccupationOk() {
        Toast.makeText(
            applicationContext,
            "Fine occupazione, si prega di allontanarsi!",
            Toast.LENGTH_SHORT
        )
            .show()

        val endOccupation = findViewById<Button>(R.id.endOccupation)
        val igienizza = findViewById<Button>(R.id.nav_sanitize)
        val message = findViewById<TextView>(R.id.message_txt)


        runOnUiThread {
            endOccupation.isEnabled = true
        }

        val evprenotazioni = findViewById<TextView>(R.id.evprenotazioni)
        val stato = findViewById<TextView>(R.id.stato)
        if (stato.text == "Occupata") {
            runOnUiThread {
                stato.text = "Libera e non Igienizzata"
            }

            runOnUiThread {
                igienizza.isEnabled = true
            }
            runOnUiThread {
                igienizza.setVisibility(View.VISIBLE)
            }
        }


        runOnUiThread {
            endOccupation.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            endOccupation.isEnabled = false
        }
        if (stato.text == "Occupata") {
            runOnUiThread {
                stato.text = "Libera e Igienizzata"
            }
            if (evprenotazioni.text == "Prenotata da te. Attenzione ci sono altre prenotazioni!" || evprenotazioni.text == "Postazione prenotata da te") {
                runOnUiThread {
                    message.setVisibility(View.INVISIBLE)
                }

            }

        }

        runOnUiThread {
            endOccupation.setVisibility(View.INVISIBLE)
        }
        runOnUiThread {
            endOccupation.isEnabled = false
        }
        runOnUiThread {
            igienizza.isEnabled = true
        }
        runOnUiThread {
            igienizza.setVisibility(View.VISIBLE)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun CallGetTimeToNextUpdate(s: String) {
        val s2 = s.replace(".0", "")
        val tagId = findViewById<TextView>(R.id.tagId_txt)
        val oraEdit = findViewById<EditText>(R.id.OraEdit)
        var hours: Int = 0
        if (s2.toInt() == -1) {
            val current = LocalDateTime.now()
            hours = 23 - (current.hour + 1)
        } else if (s2.toInt() > 0) {
            hours = s2.toInt()
        }
        else if(s2.toInt()== -3){
            getPresenter().startOccupation(tagId.text.toString(), 0)
        }
        if(oraEdit.text.toString() != "") {
            if (oraEdit.text.toString().toInt() <= hours && s2.toInt() != -3) {
                getPresenter().startOccupation(
                    tagId.text.toString(),
                    oraEdit.text.toString().toInt()
                )
            } else {
                Toast.makeText(
                    applicationContext,
                    "Puoi inserire massimo " + hours + " ore!",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        }
        else {
            Toast.makeText(
                applicationContext,
                "Non hai inserito nessun valore!",
                Toast.LENGTH_SHORT
            )
                .show()

        }
    }
}



