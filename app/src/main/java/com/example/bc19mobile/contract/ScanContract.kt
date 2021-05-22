package com.example.bc19mobile.contract

import android.app.Activity
import android.view.Menu
import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.DataBookingToday
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.ScanModel
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
interface ScanContract {


    interface IView : IViewContract{
        fun updateScanView(workstation: DataWorkstation?, bookings: ArrayList<DataBookingToday>?)
        fun callScanError()
    }

    interface IPresenter : IPresenterContract{
        fun saveUser(user: User?)
        fun getUser(): User?
        fun scanTagNFC(tag: String)
    }

    interface IModel : IModelContract{
        fun getWorkstation(): DataWorkstation?
        fun setWorkstationListener(listener: ScanModel.ScanListener?)
        fun setUser(user: User?)
        fun getUser(): User?
        fun getStatus(tag:String)
        fun getBookingListToday(): ArrayList<DataBookingToday>?
        fun setBookingListener(listener: ScanModel.ScanListener?)
    }



}
