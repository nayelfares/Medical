package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.emarketing.medical.R
import com.emarketing.medical.data.Article
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.PhotoAdapter
import kotlinx.android.synthetic.main.activity_location_details.*

class ArticleDetails : BaseActivity() {
    lateinit var currentLocation:Article
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)


        currentLocation=getIntent().getParcelableExtra<Article>("article")!!
        name.text=currentLocation.title
        description.text=currentLocation.body

        photosView.adapter=PhotoAdapter(this,currentLocation.photos)
    }
}