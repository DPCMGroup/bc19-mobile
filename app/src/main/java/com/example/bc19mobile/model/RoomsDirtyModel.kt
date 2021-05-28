package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.RoomsDirtyContract
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.service.Service
import mvp.ljb.kt.model.BaseModel
import org.json.JSONArray
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class RoomsDirtyModel : BaseModel(), RoomsDirtyContract.IModel {
    interface RoomsDirtyListener {
        fun onRoomsSuccess()
        fun onRoomsFailure()
    }


    private var listener: RoomsDirtyModel.RoomsDirtyListener? = null
    private val service = Service()
    private var user: User? = null
    private var roomsDirtyList = ArrayList<DataDirtyRooms>()

    override fun setRoomsDirtyListener(listener: RoomsDirtyModel.RoomsDirtyListener?) {
        this.listener = listener
    }

    override fun getDirtyRooms(): ArrayList<DataDirtyRooms>? {
        return roomsDirtyList
    }

    override fun getRooms() {
        service.request(
            null,
            "room/dirty/list",
            false,
            ::RoomsHandle,
            ::connectionError
        )
    }

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getUser(): User? {
        return user
    }

    private fun connectionError(ioException: IOException) {
        //gestisco gli errori con connessione
    }

    private fun RoomsHandle(response: String) {
        if (response == "8196") {
            val jsonArray = JSONArray(response)
            roomsDirtyList = ArrayList<DataDirtyRooms>()
            for (i in 0 until jsonArray.length()) {
                val room = jsonArray.getJSONObject(i)
                val model = DataDirtyRooms()
                model.id = room.getInt("id")
                model.roomName = room.getString("roomname")
                model.xRoom = room.getInt("xroom")
                model.yRoom = room.getInt("yroom")
                model.archived = room.getInt("archived")
                model.unavailable = room.getInt("unavailable")
                roomsDirtyList.add(model)
            }
            listener?.onRoomsSuccess()
        }else{
            listener?.onRoomsFailure()
        }
    }

}