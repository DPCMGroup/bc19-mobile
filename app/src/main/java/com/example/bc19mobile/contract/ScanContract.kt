package com.example.bc19mobile.contract

import com.example.bc19mobile.data.User
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/18
 * @Description input description
 **/
interface ScanContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract{
        fun saveUser(user: User?)
    }

    interface IModel : IModelContract{
        fun setUser(user: User?)
    }
}
