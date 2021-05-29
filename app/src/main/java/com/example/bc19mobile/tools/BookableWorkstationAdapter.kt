package com.example.bc19mobile.tools

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBookableWorkstation
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.BookingWorkstationModel

class BookableWorkstationAdapter(
    var mCtx: Context, var resources: Int, var items: ArrayList<DataBookableWorkstation>
) :
    ArrayAdapter<DataBookableWorkstation>(mCtx, resources, items) {


    private var listener: BookingWorkstationModel.BookingWorkstationListener? = null
    private lateinit var bookingHandle: (Int) -> Unit

    fun attachBooking(bookingMethod: (Int) -> Unit) {
        this.bookingHandle = bookingMethod
    }

    var workstationId: Int? = -1
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataBookableWorkstation = items[position]
        testolista.text =
            mItem.workstationName.toString() + " della stanza " + mItem.idRoom.toString() + " di dimensione X: " + mItem.xWorkstation.toString() + " e Y: " + mItem.yWorkstation.toString()

        var prenota: Button = view.findViewById(R.id.prenotapostazione)
        prenota.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post(Runnable { bookingHandle(mItem.id!!) })
            }
        })

        return view
    }
}
