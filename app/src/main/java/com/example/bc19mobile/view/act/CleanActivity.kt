package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toolbar
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.CleanContract
import com.example.bc19mobile.presenter.CleanPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.User

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
class CleanActivity : BaseMvpActivity<CleanContract.IPresenter>() , CleanContract.IView {

    override fun registerPresenter() = CleanPresenter::class.java

    override fun getLayoutId() = R.layout.activity_clean

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        val bStanze= findViewById<Button>(R.id.bStanze)
        val bPostazioni= findViewById<Button>(R.id.bPostazioni)

        bStanze.setOnClickListener {
            var user = getPresenter().getUser()
            goActivity(
                RoomsDirtyActivity::class.java, bundleOf(
                    "user" to user
                ))
        }

        bPostazioni.setOnClickListener{
            var user = getPresenter().getUser()
            goActivity(
                WorkstationsDirtyActivity::class.java, bundleOf(
                    "user" to user
                ))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        menu?.findItem(R.id.nav_tag)?.setVisible(false)
        menu?.findItem(R.id.nav_sanitize)?.setVisible(false)
        menu?.findItem(R.id.nav_booking)?.setVisible(false)
        menu?.findItem(R.id.nav_bookingForm)?.setVisible(false)
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
