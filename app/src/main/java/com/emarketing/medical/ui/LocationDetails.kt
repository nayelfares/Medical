package com.emarketing.medical.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.text.HtmlCompat
import com.emarketing.medical.R
import com.emarketing.medical.data.Site
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.PhotoAdapter
import kotlinx.android.synthetic.main.activity_location_details.*
import kotlinx.android.synthetic.main.activity_location_details.city
import kotlinx.android.synthetic.main.activity_location_details.name

class LocationDetails : BaseActivity() {
    lateinit var currentLocation:Site
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)


        currentLocation=getIntent().getParcelableExtra<Site>("location")!!
        name.text=currentLocation.name
        city.text=currentLocation.city_name
        description.text=currentLocation.description
        openingHours.text=currentLocation.opening_hours
        if (currentLocation.phone!=null)
            phone.text=currentLocation.phone
        else
            phoneContainer.visibility=View.GONE
        if (currentLocation.email!=null)
            email.text=currentLocation.email
        else
            emailContainer.visibility=View.GONE
        if (currentLocation.facebook!=null)
            facebook.text=currentLocation.facebook
        else
            facebookContainer.visibility=View.GONE
        if (currentLocation.instagram!=null)
            instagram.text=currentLocation.instagram
        else
            instagramContainer.visibility=View.GONE
        if (currentLocation.snapchat!=null)
            snapchat.text=currentLocation.snapchat
        else
            snapchatContainer.visibility=View.GONE
        if (currentLocation.whatsapp!=null)
            whatsapp.text=currentLocation.whatsapp
        else
            whatsappContainer.visibility=View.GONE
        if (currentLocation.youtube!=null)
            youtube.text=currentLocation.youtube
        else
            youtubeContainer.visibility=View.GONE
        address.text=currentLocation.address
        val loc="<a href='https://maps.google.com/?q=${currentLocation.latitude},${currentLocation.longitude}'>https://maps.google.com/?q=${currentLocation.latitude},${currentLocation.longitude}</a>"
        mapsLocation.text= HtmlCompat.fromHtml(loc, HtmlCompat.FROM_HTML_MODE_LEGACY)
        if (currentLocation.photos.isEmpty())
            noResults.visibility=View.VISIBLE
        mapsLocation.movementMethod = LinkMovementMethod.getInstance()
        photosView.adapter=PhotoAdapter(this,currentLocation.photos)
    }
}