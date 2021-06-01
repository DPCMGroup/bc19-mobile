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
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.RoomsDirtyModel

class RoomsDirtyAdapter(var mCtx: Context, var resources: Int, var items: List<DataDirtyRooms>) :
    ArrayAdapter<DataDirtyRooms>(mCtx, resources, items) {

    private var listener: RoomsDirtyModel.RoomsDirtyListener? = null
    private lateinit var sanitizeRoomHandle: (Int) -> Unit

    fun attachSanitizeRoom(sanitizeRMethod: (Int) -> Unit) {
        this.sanitizeRoomHandle = sanitizeRMethod
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)
        val testolista: TextView = view.findViewById(R.id.testolista)


        var mItem: DataDirtyRooms = items[position]
        testolista.text =
            mItem.roomName.toString()


        var igienizzaStanza: Button = view.findViewById(R.id.igienizzastanza)
        igienizzaStanza.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post(Runnable { sanitizeRoomHandle(mItem.id!!) })
            }
        })

        return view
    }
}
