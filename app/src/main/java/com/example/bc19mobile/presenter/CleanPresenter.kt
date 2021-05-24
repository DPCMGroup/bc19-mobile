package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.CleanContract
import com.example.bc19mobile.model.CleanModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
class CleanPresenter : BaseMvpPresenter<CleanContract.IView, CleanContract.IModel>(), CleanContract.IPresenter{

    override fun registerModel() = CleanModel::class.java

}
