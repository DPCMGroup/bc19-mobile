package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.GuideContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.GuideModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/21
 * @Description input description
 **/
class GuidePresenter : BaseMvpPresenter<GuideContract.IView, GuideContract.IModel>(), GuideContract.IPresenter{

    override fun registerModel() = GuideModel::class.java
    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun showGuide() {
        val guideName : String = getModel().getGuide()
        getMvpView().updateGuideView(guideName)
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

}
