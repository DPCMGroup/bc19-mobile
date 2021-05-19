package com.example.bc19mobile.view.act

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.presenter.BookingPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User



/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingActivity : BaseMvpActivity<BookingContract.IPresenter>(), BookingContract.IView {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
        getPresenter().showBookings()

    }

    override fun registerPresenter() = BookingPresenter::class.java

    override fun getLayoutId() = R.layout.view_booking
    override fun updateBookingView(bookings: ArrayList<DataBooking>?) {
/*
        val bookId = findViewById<TextView>(R.id.bookId)
        val workName = findViewById<TextView>(R.id.workName)
        val roomName = findViewById<TextView>(R.id.roomName)
        val start = findViewById<TextView>(R.id.start)
        val end = findViewById<TextView>(R.id.end)

        var listView = findViewById<ListView>(R.id.prenotalist)
*/
        
        /*
        try{
        val s : String= bookings.toString()
        val jsonObject = JSONObject(s)
        val jsonArray = jsonObject.getJSONArray("visualizzaPrenotazioni")

            //for (i in 0 until bookings!!.size)
              //  bookings!!.removeAt(i)
        for (i in 0 until jsonArray.length()) {
            val jsonObject1 = jsonArray.getJSONObject(i)
            val model = DataBooking()
            model.bookId = jsonObject1.getInt("bookId")
            model.workName = jsonObject1.getString("workName")
            model.roomName = jsonObject1.getString("roomName")
            model.start = jsonObject1.getString("start")
            model.end = jsonObject1.getString("end")

            bookings!!.add(model)
        }
            }
            catch (e: JSONException) {
                e.printStackTrace()
            }
*/
        PutDataIntoRecyclerView(bookings)

    }


    private fun PutDataIntoRecyclerView(bookings: ArrayList<DataBooking>?) {
        var recyclerView: RecyclerView? = null
        recyclerView = findViewById(R.id.recyclerView)
        val adaptery = BookingAdapter(this, bookings!!)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = adaptery
    }
/*
        for (i in 0 until (bookings?.size?.toInt() ?: 0)){
            bookings?.get(i)?._bookingId

        }


*/
}

