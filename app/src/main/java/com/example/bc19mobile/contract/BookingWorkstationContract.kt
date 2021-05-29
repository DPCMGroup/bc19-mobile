package com.example.bc19mobile.contract

import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataBookableWorkstation
import com.example.bc19mobile.data.DataDirtyWorkstations
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingWorkstationModel
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
interface BookingWorkstationContract {

    interface IView : IViewContract{
        fun updateWorkstationsBookableView(workstationsDirty: ArrayList<DataBookableWorkstation>?)
        fun callErrorBookableWorkstation()
    }

    interface IPresenter : IPresenterContract{
        fun showAvailability()
        fun saveUser(user: User?)
        fun saveBookingWorkstation(bookingWorkstation: BookingWorkstation?)
        fun getUser(): User?
        fun getBookingWorkstation(): BookingWorkstation?
    }

    interface IModel : IModelContract{
        fun setUser(user: User?)
        fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?)
        fun getUser(): User?
        fun getBookingWorkstation(): BookingWorkstation?
        fun getAvailability()
        fun setBookingWorkstationListener(listener: BookingWorkstationModel.BookingWorkstationListener?)
        fun getBookableWorkstations(): ArrayList<DataBookableWorkstation>?
    }
}
