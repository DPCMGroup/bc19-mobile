package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.ScanContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingModel
import com.example.bc19mobile.model.ScanModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
class ScanPresenter : BaseMvpPresenter<ScanContract.IView, ScanContract.IModel>(),
    ScanContract.IPresenter {

    override fun registerModel() = ScanModel::class.java
    override fun onCreate() {
        super.onCreate()
        getModel().setWorkstationListener(object : ScanModel.ScanListener {
            override fun onScanSuccess() {

                getMvpView().updateScanView(getModel().getWorkstation(), getModel().getBookingListToday())
            }
            override fun onScanFailure() {
                getMvpView().callScanError()
            }

            override fun onSanitizeFailure() {
                getMvpView().callSanitizeError()
            }

            override fun onSanitizeSuccess() {
                getMvpView().callSanitizeOk()
            }
        })
    }

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun scanTagNFC(tag: String) {
        getModel().getStatus(tag)
    }

    override fun makeSanitize(tag: String) {
        getModel().getSanitize(tag)
    }

}
