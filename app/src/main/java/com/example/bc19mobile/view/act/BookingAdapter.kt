package com.example.bc19mobile.view.act

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking

class BookingAdapter(var mCtx: Context, var resources: Int, var items: List<DataBooking>) :
    ArrayAdapter<DataBooking>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataBooking = items[position]
        testolista.text =
            mItem.bookId.toString() + " - " + mItem.workName.toString()+ " - " + mItem.roomName.toString()+ " - " + mItem.start.toString() + " - " + mItem.end.toString()
        return view
    }
}
