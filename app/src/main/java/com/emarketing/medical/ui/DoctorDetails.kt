package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.emarketing.medical.R
import com.emarketing.medical.api.toUrl
import com.emarketing.medical.data.Article
import com.emarketing.medical.data.Doctor
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.PhotoAdapter
import kotlinx.android.synthetic.main.activity_article_details.*
import kotlinx.android.synthetic.main.activity_doctor_details.*

class DoctorDetails : BaseActivity() {
    lateinit var doctor:Doctor
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)
        doctor=getIntent().getParcelableExtra<Doctor>("doctor")!!
        Glide.with(this)
            .load(doctor.photo.toUrl())
            .into(photo)
        username.text=doctor.name
        specialization.text=doctor.specialization
        address.text=doctor.address
        email.text=doctor.email
        phone.text=doctor.mobile
        whatsapp.text=doctor.whatsapp
    }
}