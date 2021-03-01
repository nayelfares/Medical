package com.emarketing.medical.ui

import android.os.Bundle
import com.emarketing.medical.R
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class Register : BaseActivity(),RegisterView {
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerViewModel= RegisterViewModel(this,this)
        events()
    }

    private fun events() {
        register.setOnClickListener {
            loading()
            registerViewModel.register(
                username.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                password.text.toString()
            )
        }

        cancel.setOnClickListener { finish() }
    }

    override fun onSuccess(message: String) {
        showMessage(message)
        stopLoading()
        finish()
    }

    override fun onFailer(message: String) {
        stopLoading()
        showMessage(message)
    }
}