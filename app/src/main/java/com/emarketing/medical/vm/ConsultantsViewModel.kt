package com.emarketing.medical.vm

import android.content.Context
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.data.DoctorsListResult
import com.emarketing.medical.data.RequestInterface
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.ui.ConsultantsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ConsultantsViewModel(val consultantsView: ConsultantsView, val context: Context) {
    fun getDoctorsList(){
        val apiManager= MainAPIManager().provideRetrofitInterface().create(RequestInterface::class.java)
        val registerVar  = apiManager.getDoctorsList(BaseActivity.token )
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DoctorsListResult> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: DoctorsListResult) {
                    consultantsView.onSuccess(t.data)
                }
                override fun onError(e: Throwable) {
                    consultantsView.onFailer(e.message.toString())
                }
            })
    }
}