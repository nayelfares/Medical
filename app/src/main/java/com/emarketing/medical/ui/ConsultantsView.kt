package com.emarketing.medical.ui

import com.emarketing.medical.data.Doctor

interface ConsultantsView {
    fun onFailer(toString: String)
    fun onSuccess(doctors: ArrayList<Doctor>)
}