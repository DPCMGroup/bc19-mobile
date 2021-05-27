package com.example.bc19mobile.tools

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.view.act.BookingActivity


class BookingAdapter(var mCtx: Context, var resources: Int, var items: ArrayList<DataBooking>) :
    ArrayAdapter<DataBooking>(mCtx, resources, items) {
    var bookId: Int? = -1
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataBooking = items[position]
        testolista.text =
            mItem.bookId.toString() + " - " + mItem.workName.toString() + " - " + mItem.roomName.toString() + " - " + mItem.start.toString() + " - " + mItem.end.toString()


      //  fun initClickListeners(): Int? {
            var button2: Button = view.findViewById(R.id.button2)
            button2.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {

                    button2.text = mItem.bookId.toString()
                    bookId = mItem.bookId!!

                    //getBookId()
                    //initClickListeners()
                    //BookingActivity().updateBookingView(items)

                    mCtx.startActivity(Intent(mCtx, BookingActivity::class.java))
                }
            })
          //  return bookId
       // }

        return view
    }


    fun getBookId(): Int {
        return bookId!!
    }
/*
    fun initClickListeners(): Int {

        return bookId!!
    }

 */


}


