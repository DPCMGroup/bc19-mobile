package com.example.bc19mobile.view.act

import android.os.Bundle
import android.os.PersistableBundle
import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.presenter.BookingPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R
import com.example.bc19mobile.data.User

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingActivity : BaseMvpActivity<BookingContract.IPresenter>() , BookingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().saveUser(intent.extras?.get("user") as User)
    }

    override fun registerPresenter() = BookingPresenter::class.java

    override fun getLayoutId() = R.layout.activity_booking

}
