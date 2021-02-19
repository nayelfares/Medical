package com.emarketing.medical.ui

import android.os.Bundle
import com.emarketing.medical.R
import com.emarketing.medical.data.Category
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.CategoryAdapter
import com.emarketing.medical.vm.GeneralBrowsingViewModel
import kotlinx.android.synthetic.main.activity_general_browsing.*

class GeneralBrowsing : BaseActivity(),GeneralBrowsingView {
    lateinit var generalBrowsingViewModel: GeneralBrowsingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_browsing)
        getMore.setOnRefreshListener { getMore.setRefreshing(false) }
        generalBrowsingViewModel= GeneralBrowsingViewModel(this,this)
        loading()
        generalBrowsingViewModel.getCategories()
    }

    override fun onFailer(message: String) {
        stopLoading()
        showMessage(message )
    }

    override fun onSuccess(categories: ArrayList<Category>) {
        stopLoading()
        content.adapter=CategoryAdapter(this,categories)
    }

}