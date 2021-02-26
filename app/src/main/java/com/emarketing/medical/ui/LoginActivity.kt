package com.emarketing.medical.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.emarketing.medical.MainActivity
import com.emarketing.medical.R
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.reset_password.*

class LoginActivity : BaseActivity(),LoginView {
    lateinit var loginViewModel: LoginViewModel
    lateinit var resetDialog:Dialog
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

        forgetPassword.setOnClickListener {
            resetDialog= Dialog(this)
            resetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            resetDialog.setCancelable(true)
            resetDialog.setContentView(R.layout.reset_password)
            resetDialog.reset.setOnClickListener {
                loading()
                loginViewModel.resetPassword(resetDialog.email.text.toString())
            }
            resetDialog.show()

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

    override fun resetSuccess(message: String) {
        showMessage(message)
        stopLoading()
        resetDialog.dismiss()
    }
}