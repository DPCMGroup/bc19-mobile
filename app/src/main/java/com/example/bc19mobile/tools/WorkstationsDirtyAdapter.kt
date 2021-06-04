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
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.WorkstationsDirtyModel

class WorkstationsDirtyAdapter(var mCtx: Context, var resources: Int, var items: List<DataDirtyWorkstations>) :
    ArrayAdapter<DataDirtyWorkstations>(mCtx, resources, items) {

    private var listener: WorkstationsDirtyModel.WorkstationsDirtyListener? = null
    private lateinit var sanitizeWorkstationHandle: (String) -> Unit

    fun attachSanitizeWorkstation(sanitizeWMethod: (String) -> Unit) {
        this.sanitizeWorkstationHandle = sanitizeWMethod
    }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
            val view: View = layoutInflater.inflate(resources, null)
            val testolista: TextView = view.findViewById(R.id.testolista)


            var mItem: DataDirtyWorkstations = items[position]
            testolista.text =
                "Postazione: " + mItem.workstationName.toString() + "\n" +
                        "Stanza: " + mItem.workstationName.toString()

            var igienizzaPostazione: Button = view.findViewById(R.id.igienizzapostazione)
            igienizzaPostazione.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val mainHandler = Handler(Looper.getMainLooper())
                    mainHandler.post(Runnable { sanitizeWorkstationHandle(mItem.tag!!) })
                }
            })

            return view
        }
}