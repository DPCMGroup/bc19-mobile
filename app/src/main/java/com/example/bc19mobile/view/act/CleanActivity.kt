package com.example.bc19mobile.view.act

import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import com.example.bc19mobile.contract.CleanContract
import com.example.bc19mobile.presenter.CleanPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
class CleanActivity : BaseMvpActivity<CleanContract.IPresenter>() , CleanContract.IView {

    override fun registerPresenter() = CleanPresenter::class.java

    override fun getLayoutId() = R.layout.activity_clean

    override fun initView() {
        super.initView()
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bStanze= findViewById<Button>(R.id.bStanze)
        val bPostazioni= findViewById<Button>(R.id.bPostazioni)

        bStanze.setOnClickListener {
            getPresenter().showRooms()
        }

        bPostazioni.setOnClickListener{
            getPresenter().showWorkstations()

        }
    }
}
