package com.emarketing.medical.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.emarketing.medical.MainActivity
import com.emarketing.medical.R
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(),LoginView {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel= LoginViewModel(this,this)
        register.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
        }
        login.setOnClickListener {
            loading()
            loginViewModel.login(email.text.toString(),password.text.toString())
        }

    }

    override fun loginSuccess(token: String) {
        stopLoading()
        BaseActivity.token=token
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun loginFailed(message: String) {
        stopLoading()
        showMessage(message)
    }
}