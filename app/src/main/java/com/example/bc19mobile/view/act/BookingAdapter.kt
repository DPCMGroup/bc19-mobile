package com.example.bc19mobile.view.act

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bc19mobile.R
import com.example.bc19mobile.data.DataBooking
import java.util.ArrayList


class BookingAdapter(private val mContext: Context, private val mdata: ArrayList<DataBooking>) :
    RecyclerView.Adapter<BookingAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.booking_items, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bookId.text = mdata[position].bookId.toString()
        holder.workName.text = mdata[position].workName
        holder.roomName.text = mdata[position].roomName
        holder.start.text = mdata[position].start
        holder.end.text = mdata[position].end

    }

    override fun getItemCount(): Int {
        return mdata.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookId: TextView
        var workName: TextView
        var roomName: TextView
        var start: TextView
        var end: TextView


        init {
            bookId = itemView.findViewById(R.id.bookId_txt)
            workName = itemView.findViewById(R.id.workName_txt)
            roomName = itemView.findViewById(R.id.roomName_txt)
            start = itemView.findViewById(R.id.start_txt)
            end = itemView.findViewById(R.id.end_txt)
        }
    }
}