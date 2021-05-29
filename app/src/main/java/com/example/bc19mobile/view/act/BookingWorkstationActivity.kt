package com.example.bc19mobile.view.act

import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.presenter.BookingWorkstationPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationActivity : BaseMvpActivity<BookingWorkstationContract.IPresenter>() , BookingWorkstationContract.IView {

    override fun registerPresenter() = BookingWorkstationPresenter::class.java

    override fun getLayoutId() = R.layout.activity_bookingworkstation

}
