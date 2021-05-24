package com.example.bc19mobile.contract

import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
interface CleanContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract

    interface IModel : IModelContract
}
