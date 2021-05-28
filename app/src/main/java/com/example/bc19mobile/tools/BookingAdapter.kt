package com.example.bc19mobile.tools

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.view.act.BookingActivity


class BookingAdapter(var mCtx: Context, var resources: Int, var items: ArrayList<DataBooking>) :
    ArrayAdapter<DataBooking>(mCtx, resources, items) {


    private var listener: BookingModel.BookingListener? = null
    private lateinit var deleteHandle: (Int) -> Unit

    fun attachDelete(deleteMethod: (Int) -> Unit) {
        this.deleteHandle = deleteMethod
    }

    var bookId: Int? = -1
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataBooking = items[position]
        testolista.text =
            mItem.bookId.toString() + " - " + mItem.workName.toString() + " - " + mItem.roomName.toString() + " - " + mItem.start.toString() + " - " + mItem.end.toString()


        var button2: Button = view.findViewById(R.id.button2)
        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post(Runnable { deleteHandle(mItem.bookId!!) })
            }
        })

        return view
    }


}


