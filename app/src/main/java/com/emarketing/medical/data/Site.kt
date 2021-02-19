package com.emarketing.medical.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.ArrayList

@Parcelize
data class Site(
    val name: String,
    val description: String?,
    val opening_hours: String?,
    val email: String?,
    val phone: String?,
    val latitude: String?,
    val longitude: String?,
    val profile_photo: String?,
    val facebook:String?,
    val instagram:String?,
    val snapchat:String?,
    val whatsapp:String?,
    val youtube:String?,
    val city_name: String,
    val address:String?,
    val photos:@RawValue ArrayList<String>
): Parcelable

data class SearchResult(val success:Boolean,val message:String,val data:ArrayList<Site>)