package com.example.bc19mobile.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataDirtyRooms

class RoomsDirtyAdapter(var mCtx: Context, var resources: Int, var items: List<DataDirtyRooms>) :
    ArrayAdapter<DataDirtyRooms>(mCtx, resources, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataDirtyRooms = items[position]
        testolista.text =
            mItem.roomName.toString() + ": dimensione X: " + mItem.xRoom.toString() + ", dimensione Y: " + mItem.yRoom.toString()

        return view
    }
}
