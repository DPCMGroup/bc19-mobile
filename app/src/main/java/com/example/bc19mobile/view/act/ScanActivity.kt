package com.example.bc19mobile.view.act


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.bc19mobile.R
import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.presenter.ScanPresenter
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
@Suppress("UNREACHABLE_CODE")
class ScanActivity : BaseMvpActivity<ScanContract.IPresenter>() , ScanContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPresenter().saveUser(intent.extras?.get("user") as User)

        val igienizza = findViewById<Button>(R.id.igienizza)
        val message = findViewById<TextView>(R.id.message_txt)
        val message1 = findViewById<TextView>(R.id.message1_txt)
        val stato = findViewById<TextView>(R.id.stato)
        val tagId = findViewById<TextView>(R.id.tagId_txt)
        runOnUiThread {
            igienizza.isEnabled = false
        }

    }

    override fun getLayoutId() = R.layout.activity_scan

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_prenota -> {
                return true
            }
            R.id.nav_guida -> {
                return true
            }
            R.id.nav_vis -> {
                return true
            }
            R.id.logout -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun registerPresenter() = ScanPresenter::class.java
}
