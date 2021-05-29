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
import com.example.bc19mobile.contract.RoomsDirtyContract
import com.example.bc19mobile.presenter.RoomsDirtyPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.tools.RoomsDirtyAdapter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class RoomsDirtyActivity : BaseMvpActivity<RoomsDirtyContract.IPresenter>(),
    RoomsDirtyContract.IView {


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

    fun sanitizeRoom(id: Int) {
        getPresenter().sanitizeRoom(id)
    }

    override fun updateRoomsView(roomsDirty: ArrayList<DataDirtyRooms>?) {
        var listView = findViewById<ListView>(R.id.roomsDirtylist)
        var adapter = RoomsDirtyAdapter(this, R.layout.rowdirtyroom, roomsDirty!!)
        adapter.attachSanitizeRoom(::sanitizeRoom)
        listView.adapter = adapter
    }

    override fun callErrorRoomsDirty() {
        var roomsError = findViewById<TextView>(R.id.roomsError)
        roomsError?.setVisibility(View.VISIBLE)
    }

    override fun callErrorSanitizeRoom() {
        Toast.makeText(applicationContext, "Igienizzazione non riuscita!", Toast.LENGTH_SHORT)
            .show()
    }

    override fun updateRoomsSanitizeView() {
        var user = getPresenter().getUser()
        goActivity(
            RoomsDirtyActivity::class.java, bundleOf(
                "user" to user
            )
        )
        Toast.makeText(
            applicationContext,
            "Igienizzazione eseguita con successo!",
            Toast.LENGTH_SHORT
        )
            .show()
    }

}
