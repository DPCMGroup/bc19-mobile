package  com.example.bc19mobile.model

import com.example.bc19mobile.contract.GuideContract
import com.example.bc19mobile.data.User
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/21
 * @Description input description
 **/
class GuideModel : BaseModel(), GuideContract.IModel {

    private var user: User? = null

    override fun setUser(user: User?) {
        this.user = user
    }

    override fun getGuide(): String {
        val userType : Int = user?.getType() ?: -1
        var guideName = String()

        if (userType == 1) {
            guideName = "guida_utente.pdf"
        }
        else {
            guideName = "guida_utente.pdf"
        }
        return guideName
    }

    override fun getUser(): User? {
        return user
    }
}