package com.emarketing.medical.ui

interface LoginView {
    fun loginSuccess(token:String,id:Int)
    fun loginFailed(message:String)

    fun resetSuccess(message: String)
}