package com.example.bc19mobile.contract

import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.RoomsDirtyModel
import com.example.bc19mobile.model.WorkstationsDirtyModel
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
interface WorkstationsDirtyContract {

    interface IView : IViewContract{
        fun updateWorkstationsView(workstationsDirty: ArrayList<DataDirtyWorkstations>?)
        fun callErrorWorkstationsDirty()
    }

    interface IPresenter : IPresenterContract{
        fun showWorkstations()
        fun getUser(): User?
        fun saveUser(user: User?)
    }

    interface IModel : IModelContract{
        fun getWorkstations()
        fun setWorkstationsDirtyListener(listener: WorkstationsDirtyModel.WorkstationsDirtyListener?)
        fun getDirtyWorkstations(): ArrayList<DataDirtyWorkstations>?
        fun setUser(user: User?)
        fun getUser(): User?
    }
}
