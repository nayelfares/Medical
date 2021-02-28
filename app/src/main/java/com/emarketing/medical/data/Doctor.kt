package com.emarketing.medical.data

import java.util.ArrayList

data class Doctor(
    val id:Int,
    val name:String,
    val photo:String,
    val address:String,
    val mobile:String,
    val email:String,
    val whatsapp:String,
    val specialization:String,
    val descritption:String,
    val rating: String
    )

data class DoctorsListResult(val success:Boolean,val message:String,val data: ArrayList<Doctor>)