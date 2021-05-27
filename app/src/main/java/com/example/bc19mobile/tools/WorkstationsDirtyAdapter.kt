package com.example.bc19mobile.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.DataDirtyWorkstations

class WorkstationsDirtyAdapter(var mCtx: Context, var resources: Int, var items: List<DataDirtyWorkstations>) :
    ArrayAdapter<DataDirtyWorkstations>(mCtx, resources, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)
            val testolista: TextView = view.findViewById(R.id.testolista)


            var mItem: DataDirtyWorkstations = items[position]
            testolista.text =
                mItem.workstationName.toString() + " della stanza "+ mItem.idRoom.toString() + " di dimensione X: " + mItem.xWorkstation.toString() + " e Y: " + mItem.yWorkstation.toString()

            return view
        }
}