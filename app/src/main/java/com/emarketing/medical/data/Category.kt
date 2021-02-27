package com.emarketing.medical.data

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Article(
    val photos:ArrayList<String>?,
    val status:String?,
    val category:String?,
    val sub_category:String?,
    @SerializedName("address")
    val title:String?,
    val body:String?
    )

data class ArticleResult(val success:Boolean,val message:String,val data: ArrayList<Article>)