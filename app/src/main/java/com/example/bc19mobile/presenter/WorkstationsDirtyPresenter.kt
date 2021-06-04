package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.WorkstationsDirtyContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.RoomsDirtyModel
import com.example.bc19mobile.model.WorkstationsDirtyModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/27
 * @Description input description
 **/
class WorkstationsDirtyPresenter :
    BaseMvpPresenter<WorkstationsDirtyContract.IView, WorkstationsDirtyContract.IModel>(),
    WorkstationsDirtyContract.IPresenter {

    override fun registerModel() = WorkstationsDirtyModel::class.java

    override fun onCreate() {
        super.onCreate()
        getModel().setWorkstationsDirtyListener(object :
            WorkstationsDirtyModel.WorkstationsDirtyListener {
            override fun onWorkstationsSuccess() {
                getMvpView().updateWorkstationsView(getModel().getDirtyWorkstations())
            }

            override fun onWorkstationsFailure() {
                getMvpView().callErrorWorkstationsDirty()
            }

            override fun onSanitizeFailure() {
                getMvpView().callSanitizeError()
            }

            override fun onSanitizeSuccess() {
                getMvpView().callSanitizeOk()
            }
        })

    }

    override fun showWorkstations() {
        getModel().getWorkstations()
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun sanitizeWorkstation(tag: String) {
        getModel().sanitizeWorkstation(tag)
    }

}
