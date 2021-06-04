package com.example.bc19mobile.view.act

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import com.example.bc19mobile.contract.LoginContract
import com.example.bc19mobile.presenter.LoginPresenter
import com.example.bc19mobile.R
import com.example.bc19mobile.data.User
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginActivity : BaseMvpActivity<LoginContract.IPresenter>(), LoginContract.IView {

    private var username: EditText? = null
    private var password: EditText? = null
    private var loginBtn: Button? = null
    private var loading: ProgressBar? = null
    private var errore: TextView? = null

    override fun registerPresenter() = LoginPresenter::class.java

    override fun getLayoutId() = R.layout.activity_login

    override fun initView() {
        super.initView()
        username = findViewById<EditText>(R.id.username)
        password = findViewById<EditText>(R.id.password)
        loginBtn = findViewById<Button>(R.id.login)
        loading = findViewById<ProgressBar>(R.id.loading)
        errore = findViewById<TextView>(R.id.errore)
        setActionBar(findViewById<Toolbar>(R.id.toolbar))
    }

    override fun initData() {
        super.initData()
        errore?.setVisibility(View.INVISIBLE)

        username?.doOnTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            enableBtnLogin()
        }
        password?.doOnTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            enableBtnLogin()
        }

        loginBtn?.setOnClickListener {
            getPresenter().makeLogin(username?.text.toString(), password?.text.toString())
        }
    }

    private fun enableBtnLogin() {
        val a = isUserNameValid(username?.text.toString())
        val b = isPasswordValid(password?.text.toString())
        loginBtn?.isEnabled = a && b
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 7
    }

    override fun callScan(user: User?) {
        if(user?.getType()==1){
        goActivity(
            ScanActivity::class.java, bundleOf(
                "user" to user

            )

        )
            Toast.makeText(applicationContext, "Benvenuto " + username?.text.toString() + "!", Toast.LENGTH_SHORT).show()}
        else if(user?.getType()==2){
            goActivity(
                CleanActivity::class.java, bundleOf(
                    "user" to user
                )
            )
            Toast.makeText(applicationContext, "Benvenuto " + username?.text.toString() + "!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun callError() {
        errore?.setVisibility(View.VISIBLE)
    }

}
