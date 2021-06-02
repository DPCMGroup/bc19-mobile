package com.example.bc19mobile.contract

import com.example.bc19mobile.data.DataBookingToday
import com.example.bc19mobile.data.DataWorkstation
import com.example.bc19mobile.data.User
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
        fun callSanitizeError()
        fun callSanitizeOk()
        fun callStartOccupationError()
        fun callStartOccupationOk()
        fun callEndOccupationError()
        fun callEndOccupationOk()
        fun CallGetTimeToNextUpdate(s: String)
    }

    interface IPresenter : IPresenterContract{
        fun saveUser(user: User?)
        fun getUser(): User?
        fun scanTagNFC(tag: String)
        fun makeSanitize(tag: String)
        fun startOccupation(tag: String, ora: Int)
        fun endOccupation(tag: String)
        fun getTimetoNext()
    }

    interface IModel : IModelContract{
        fun getWorkstation(): DataWorkstation?
        fun setWorkstationListener(listener: ScanModel.ScanListener?)
        fun setUser(user: User?)
        fun getUser(): User?
        fun getStatus(tag:String)
        fun getSanitize(tag: String)
        fun getStartOccupation(tag: String, ora:Int)
        fun getEndOccupation(tag: String)
        fun getBookingListToday(): ArrayList<DataBookingToday>?
        fun setBookingListener(listener: ScanModel.ScanListener?)
        fun getTimeToNext()
    }



}
