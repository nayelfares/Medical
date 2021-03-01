package com.emarketing.medical.ui

import android.net.Uri
import com.emarketing.medical.data.Profile

interface ProfileView {
    fun getProfileSuccess(profile: Profile)
    fun getProfileFailed(message:String)

    fun updatePhotoOnSuccess(message:String,uri: Uri)
    fun updatePhotoOnSFailer(message:String)
}