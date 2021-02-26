package com.emarketing.medical.data


data class RegisterResponse (val success:Boolean,val message:String)
data class LoginResponse (val success:Boolean,val message:String,val data:Data)
data class Data(val token:String,val name:String,val id:Int)