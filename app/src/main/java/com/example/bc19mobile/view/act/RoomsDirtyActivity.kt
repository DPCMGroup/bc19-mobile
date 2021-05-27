package com.example.bc19mobile.view.act

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import android.widget.Toolbar
import androidx.core.os.bundleOf
import com.example.bc19mobile.contract.RoomsDirtyContract
import com.example.bc19mobile.presenter.RoomsDirtyPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.BookingAdapter
import com.example.bc19mobile.tools.RoomsDirtyAdapter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class RoomsDirtyActivity : BaseMvpActivity<RoomsDirtyContract.IPresenter>() , RoomsDirtyContract.IView {

    override fun registerPresenter() = RoomsDirtyPresenter::class.java

    override fun getLayoutId() = R.layout.activity_roomsdirty

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showRooms()
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

    override fun updateRoomsView(roomsDirty: ArrayList<DataDirtyRooms>?) {
        var listView = findViewById<ListView>(R.id.roomsDirtylist)
        listView.adapter = RoomsDirtyAdapter(this, R.layout.rowdirty, roomsDirty!!)
    }
}
