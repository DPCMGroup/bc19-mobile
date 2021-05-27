package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.WorkstationsDirtyModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyPresenter : BaseMvpPresenter<WorkstationsDirtyContract.IView, WorkstationsDirtyContract.IModel>(), WorkstationsDirtyContract.IPresenter{

    override fun registerModel() = WorkstationsDirtyModel::class.java

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

}
