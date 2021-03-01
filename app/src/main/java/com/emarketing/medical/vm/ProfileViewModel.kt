package com.emarketing.medical.vm

import android.content.Context
import com.emarketing.medical.R
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.data.LoginResponse
import com.emarketing.medical.data.ProfileResponse
import com.emarketing.medical.data.RequestInterface
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.ui.ProfileView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(val profileView: ProfileView,val context: Context) {
    fun getProfile(){
        val apiManager= MainAPIManager().provideRetrofitInterface().create(RequestInterface::class.java)
        val registerVar  = apiManager.getProfile(BaseActivity.token,BaseActivity.id)
        registerVar.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProfileResponse> {
                override fun onComplete() { }
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(t: ProfileResponse) {
                    if (t.success!=false)
                        profileView.getProfileSuccess(t.data)
                    else
                        profileView.getProfileFailed(t.message)
                }
                override fun onError(e: Throwable) {
                    profileView.getProfileFailed(context.resources.getString(R.string.check_intenet_connection))
                }
            })
    }
}