package com.emarketing.medical.vm

import android.content.Context
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.data.CategoryResult
import com.emarketing.medical.data.RequestInterface
import com.emarketing.medical.ui.GeneralBrowsingView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GeneralBrowsingViewModel(val generalBrowsingView: GeneralBrowsingView,val context: Context) {
    fun getCategories(){
        val apiManager= MainAPIManager().provideRetrofitInterface().create(RequestInterface::class.java)
        val registerVar  = apiManager.getCategories()
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CategoryResult> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: CategoryResult) {
                        generalBrowsingView.onSuccess(t.data)
                }
                override fun onError(e: Throwable) {
                    generalBrowsingView.onFailer(e.message.toString())
                }
            })
    }
}