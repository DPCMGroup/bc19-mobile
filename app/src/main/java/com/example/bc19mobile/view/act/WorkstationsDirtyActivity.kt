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
import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.presenter.WorkstationsDirtyPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.WorkstationsDirtyAdapter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyActivity : BaseMvpActivity<WorkstationsDirtyContract.IPresenter>(),
    WorkstationsDirtyContract.IView {

    override fun registerPresenter() = WorkstationsDirtyPresenter::class.java

    override fun getLayoutId() = R.layout.activity_workstationsdirty

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showWorkstations()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        menu?.findItem(R.id.nav_tag)?.setVisible(false)
        menu?.findItem(R.id.nav_booking)?.setVisible(false)
        menu?.findItem(R.id.nav_bookingForm)?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_sanitize -> {
                var user = getPresenter().getUser()
                goActivity(
                    CleanActivity::class.java, bundleOf(
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
            R.id.logout -> {
                var moveIntent = Intent(this, LoginActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun sanitizeWorkstation(tag: String) {
        getPresenter().sanitizeWorkstation(tag)
    }

    override fun updateWorkstationsView(workstationsDirty: ArrayList<DataDirtyWorkstations>?) {
        var listView = findViewById<ListView>(R.id.workstationDirtylist)
        var adapter =
            WorkstationsDirtyAdapter(this, R.layout.rowdirtyworkstation, workstationsDirty!!)
        adapter.attachSanitizeWorkstation(::sanitizeWorkstation)
        listView.adapter = adapter
    }

    override fun callErrorWorkstationsDirty() {
       var workstationError =findViewById<TextView>(R.id.workstationDirtyError)
        workstationError?.setVisibility(View.VISIBLE)
    }

    override fun callSanitizeError() {
        Toast.makeText(applicationContext, "Igienizzazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun callSanitizeOk() {
        var user = getPresenter().getUser()
        goActivity(
            WorkstationsDirtyActivity::class.java, bundleOf(
                "user" to user
            )
        )
        Toast.makeText(applicationContext, "Igienizzazione completata!", Toast.LENGTH_SHORT)
            .show()
    }

}
