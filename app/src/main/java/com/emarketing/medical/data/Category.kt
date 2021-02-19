package com.emarketing.medical.data

import java.util.ArrayList

data class Category (val id:Long,val name:String,val image:String)

data class CategoryResult(val success:Boolean,val message:String,val data: ArrayList<Category>)