package com.emarketing.medical.ui

import android.os.Bundle
import com.emarketing.medical.R
import com.emarketing.medical.data.Doctor
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.ConsultantsViewModel
import com.emarketing.medical.vm.DoctorAdapter
import kotlinx.android.synthetic.main.activity_articals.*

class ConsultantsActivity : BaseActivity(),ConsultantsView {
    lateinit var consultantsViewModel: ConsultantsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articals)
        toolbar.text=intent.getStringExtra("catName")
        getMore.setOnRefreshListener { getMore.setRefreshing(false) }
        consultantsViewModel= ConsultantsViewModel(this,this)
        loading()
        consultantsViewModel.getDoctorsList()
    }

    override fun onFailer(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun onSuccess(doctors: ArrayList<Doctor>) {
        stopLoading()
        content.adapter= DoctorAdapter(this,doctors)
    }

}