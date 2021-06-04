package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.RoomsDirtyContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.RoomsDirtyModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class RoomsDirtyPresenter : BaseMvpPresenter<RoomsDirtyContract.IView, RoomsDirtyContract.IModel>(), RoomsDirtyContract.IPresenter{

    override fun registerModel() = RoomsDirtyModel::class.java

    override fun onCreate() {
        super.onCreate()
        getModel().setRoomsDirtyListener(object : RoomsDirtyModel.RoomsDirtyListener {
            override fun onRoomsSuccess() {
                  getMvpView().updateRoomsView(getModel().getDirtyRooms())
            }
            override fun onRoomsFailure() {
                getMvpView().callErrorRoomsDirty()
            }

            override fun onSanitizeRoomSuccess() {
                getMvpView().updateRoomsSanitizeView()
            }
            override fun onSanitizeRoomFailure() {
                getMvpView().callErrorSanitizeRoom()
            }
        })

    }

    override fun showRooms() {
        getModel().getRooms()
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun sanitizeRoom(roomId: Int) {
        getModel().sanitizeRoom(roomId)
    }


}
