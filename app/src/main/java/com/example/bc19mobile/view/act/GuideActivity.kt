package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.GuideContract
import com.example.bc19mobile.presenter.GuidePresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.User
import com.github.barteksc.pdfviewer.PDFView

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/21
 * @Description input description
 **/
class GuideActivity : BaseMvpActivity<GuideContract.IPresenter>(), GuideContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showGuide()
    }

    override fun registerPresenter() = GuidePresenter::class.java

    override fun getLayoutId() = R.layout.activity_guide

    override fun updateGuideView(guideName: String) {
        val pdfView = findViewById<PDFView>(R.id.pdfView)
        pdfView.fromAsset(guideName).onPageError { page, _ ->
            Toast.makeText(
                this@GuideActivity,
                "Error at page: $page", Toast.LENGTH_LONG
            ).show()
        }
            .load()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val itemToHide = menu?.findItem(R.id.nav_guida)
        itemToHide?.setVisible(false)
        return true
    }

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
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


            R.id.nav_bookingForm -> {
                val user = getPresenter().getUser()
                goActivity(
                    BookingFormActivity::class.java, bundleOf(
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
            R.id.logout -> {
                var moveIntent = Intent(this, LoginActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
