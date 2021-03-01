package com.emarketing.medical.ui

import com.emarketing.medical.data.Profile

interface ProfileView {
    fun getProfileSuccess(profile: Profile)
    fun getProfileFailed(message:String)
}