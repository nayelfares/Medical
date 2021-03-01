package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.emarketing.medical.R
import com.emarketing.medical.api.MainAPIManager
import com.emarketing.medical.api.toUrl
import com.emarketing.medical.data.*
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.PhotoAdapter
import com.emarketing.medical.vm.ProfileViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_doctor_details.*
import kotlinx.android.synthetic.main.activity_doctor_details.email
import kotlinx.android.synthetic.main.activity_doctor_details.phone
import kotlinx.android.synthetic.main.activity_doctor_details.photo
import kotlinx.android.synthetic.main.activity_doctor_details.username
import kotlinx.android.synthetic.main.activity_profile_details.*

class ProfileDetails : BaseActivity() ,ProfileView{
    lateinit var profileViewModel: ProfileViewModel
    lateinit var profile: Profile
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)
        profileViewModel= ProfileViewModel(this,this)
        loading()
        profileViewModel.getProfile()
    }

    override fun getProfileSuccess(profile: Profile) {
        stopLoading()
        this.profile=profile
        Glide.with(this)
            .load(profile.photo.toUrl())
            .into(photo)
        username.text=profile.name
        details.setText(profile.details.toString())
        email.text=profile.email
        phone.text=profile.mobile
    }

    override fun getProfileFailed(message: String) {
        stopLoading()
        showMessage(message)
        finish()
    }
}