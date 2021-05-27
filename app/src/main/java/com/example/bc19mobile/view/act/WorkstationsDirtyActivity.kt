package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.presenter.WorkstationsDirtyPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.User

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyActivity : BaseMvpActivity<WorkstationsDirtyContract.IPresenter>() , WorkstationsDirtyContract.IView {

    override fun registerPresenter() = WorkstationsDirtyPresenter::class.java

    override fun getLayoutId() = R.layout.activity_workstationsdirty

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val itemToHide = menu?.findItem(R.id.nav_tag)
        itemToHide?.setVisible(false)
        val itemToHide2 = menu?.findItem(R.id.nav_booking)
        itemToHide2?.setVisible(false)
        val itemToHide3 = menu?.findItem(R.id.nav_bookingForm)
        itemToHide3?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

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

}
