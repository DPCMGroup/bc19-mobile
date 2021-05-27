package com.example.bc19mobile.contract

import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.RoomsDirtyModel
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
interface RoomsDirtyContract {

    interface IView : IViewContract  {
        fun updateRoomsView(roomsDirty: ArrayList<DataDirtyRooms>?)
        fun callErrorRoomsDirty()
    }

    interface IPresenter : IPresenterContract{
        fun showRooms()
        fun getUser(): User?
        fun saveUser(user: User?)
    }

    interface IModel : IModelContract{
        fun getRooms()
        fun setRoomsDirtyListener(listener: RoomsDirtyModel.RoomsDirtyListener?)
        fun getDirtyRooms(): ArrayList<DataDirtyRooms>?
        fun setUser(user: User?)
        fun getUser(): User?
    }
}
