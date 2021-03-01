package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.emarketing.medical.R
import com.emarketing.medical.api.toUrl
import com.emarketing.medical.data.*
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.ProfileViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
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
        photo.setOnClickListener {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(this)
        }
    }

    override fun getProfileSuccess(profile: Profile) {
        stopLoading()
        this.profile=profile
        Glide.with(this)
            .load(profile.photo.toUrl())
            .into(photo)
        username.setText(profile.name)
        details.setText(profile.details)
        email.setText(profile.email)
        phone.setText(profile.mobile)
    }

    override fun getProfileFailed(message: String) {
        stopLoading()
        showMessage(message)
        finish()
    }

    override fun updatePhotoOnSuccess(message: String, uri: Uri) {
        photo.setImageURI(uri)
        stopLoading()
        showMessage(message)
    }

    override fun updatePhotoOnSFailer(message: String) {
        stopLoading()
        showMessage(message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri: Uri = result.uri
                loading()
                profileViewModel.updatePhoto(resultUri)
            }
        }
    }
}