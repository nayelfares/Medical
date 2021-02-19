package com.emarketing.medical.vm

import android.content.Context
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.data.RegisterResponse
import com.emarketing.medical.data.RequestInterface
import com.emarketing.medical.ui.RegisterView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(val registerView: RegisterView, val context: Context) {

    fun register(name:String,email:String,phone:String,password:String,message:String){
        val apiManager= MainAPIManager().provideRetrofitInterface().create(RequestInterface::class.java)
        val registerVar  = apiManager.register(name,email,phone,password,message)
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RegisterResponse> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: RegisterResponse) {
                    if (t.success)
                        registerView.onSuccess(t.message)
                    else
                        registerView.onFailer(t.message)
                }
                override fun onError(e: Throwable) {
                    registerView.onFailer(e.message.toString())
                }
            })
    }
}