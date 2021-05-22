package com.example.bc19mobile.view.act


import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bc19mobile.NFC.NdefMessageParser
import com.example.bc19mobile.NFC.ParsedNdefRecord
import com.example.bc19mobile.R
import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.presenter.ScanPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import mvp.ljb.kt.act.BaseMvpAppCompatActivity
import mvp.ljb.kt.act.BaseMvpFragmentActivity
import mvp.ljb.kt.fragment.BaseMvpFragment
import org.json.JSONObject
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
        Toast.makeText(applicationContext, "Benvenuto " + username + "!", Toast.LENGTH_SHORT).show()


        val igienizza = findViewById<Button>(R.id.igienizza)
        runOnUiThread {
            igienizza.isEnabled = false
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
        return true
    }

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookingForm -> {
                return true
            }
            R.id.nav_guida -> {
                return true
            }
            R.id.nav_booking -> {
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

    override fun updateScanView(workstation: DataWorkstation?) {
        val igienizza = findViewById<Button>(R.id.igienizza)
        val message = findViewById<TextView>(R.id.message_txt)
        val message1 = findViewById<TextView>(R.id.message1_txt)
        igienizza.setVisibility(View.INVISIBLE)
        message.setVisibility(View.INVISIBLE)
        message1.setVisibility(View.INVISIBLE)

        val nomepostazione = findViewById<TextView>(R.id.nomepostazione)
        val stato = findViewById<TextView>(R.id.stato)
        val evprenotazioni = findViewById<TextView>(R.id.evprenotazioni)
        nomepostazione.text = workstation?._workName


        if (workstation?._workStatus == 0 && workstation?._workSanitize == 1) {
            runOnUiThread {
                stato.text = "Libera e Igienizzata"
            }
            runOnUiThread {
                message1.setVisibility(View.VISIBLE)
            }

        } else if (workstation?._workStatus == 0 && workstation?._workSanitize== 0) {
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
                stato.text = "Occupata e non Igienizzata"
            }
            runOnUiThread {
                message.setVisibility(View.VISIBLE)
            }
            runOnUiThread {
                igienizza.isEnabled = true
            }

            runOnUiThread {
                igienizza.setVisibility(View.VISIBLE)
            }
        } else if (workstation?._workStatus == 1 && workstation?._workSanitize == 1) {
            runOnUiThread {
                stato.text = "Occupata e Igienizzata"
            }
            runOnUiThread {
                message.setVisibility(View.VISIBLE)
            }
        } else if (workstation?._workStatus == 2 && workstation?._workSanitize == 1) {

            runOnUiThread { stato.text = "Prenotata e Igienizzata" }
            runOnUiThread { message.setVisibility(View.VISIBLE) }
        } else if (workstation?._workStatus == 2 && workstation?._workSanitize == 0) {
            runOnUiThread { stato.text = "Prenotata e non Igienizzata" }
            runOnUiThread { message.setVisibility(View.VISIBLE) }
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



         nomepostazione.setVisibility(View.VISIBLE)
         stato.setVisibility(View.VISIBLE)
         evprenotazioni.setVisibility(View.VISIBLE)

        val intro =  findViewById<TextView>(R.id.intro_txt)
        runOnUiThread {
            intro.setVisibility(View.VISIBLE)
        }

        // Rende visibile recycler view
        val layout =  findViewById<RecyclerView>(R.id.recyclerView)
        runOnUiThread {
            layout.setVisibility(View.VISIBLE)
        }
    }
}


