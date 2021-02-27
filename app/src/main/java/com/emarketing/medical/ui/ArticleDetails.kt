package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.emarketing.medical.R
import com.emarketing.medical.data.Article
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.PhotoAdapter
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetails : BaseActivity() {
    lateinit var currentLocation:Article
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        currentLocation=getIntent().getParcelableExtra<Article>("article")!!
        name.text=currentLocation.title
        description.text=currentLocation.body
        if (currentLocation.photos==null ||currentLocation.photos.isEmpty())
            photosView.visibility= View.GONE
        photosView.adapter=PhotoAdapter(this,currentLocation.photos)
    }
}