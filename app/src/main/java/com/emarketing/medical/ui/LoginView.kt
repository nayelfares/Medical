package com.emarketing.medical.ui

interface LoginView {
    fun loginSuccess(token:String)
    fun loginFailed(message:String)
}