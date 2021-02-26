package com.emarketing.medical.vm

import android.content.Context
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.data.LoginResponse
import com.emarketing.medical.data.RegisterResponse
import com.emarketing.medical.data.RequestInterface
import com.emarketing.medical.ui.LoginView
import com.emarketing.medical.ui.RegisterView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(val loginView: LoginView, val context: Context) {

    fun login(email:String,password:String){
        val apiManager= MainAPIManager().provideRetrofitInterface().create(RequestInterface::class.java)
        val registerVar  = apiManager.login(email,password)
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginResponse> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: LoginResponse) {
                    if (t.success)
                        loginView.loginSuccess(t.data.token)
                    else
                        loginView.loginFailed(t.message)
                }
                override fun onError(e: Throwable) {
                    loginView.loginFailed(e.message.toString())
                }
            })
    }
}