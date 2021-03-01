package com.emarketing.medical.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RequestInterface {
    @POST("user-register")
    fun register(
        @Query("name") name:String,
        @Query("email") file_id:String,
        @Query("phone") phone:String,
        @Query("password") password:String
    ): Observable<Response>

    @POST("user-login")
    fun login(
        @Query("email") file_id:String,
        @Query("password") password:String
    ): Observable<LoginResponse>

    @GET("article/search")
    fun getArticleList(
        @Header("Authorization")  token:String,
        @Query("category_id") categoryId:Int
    ): Observable<ArticleResult>

    @POST("password/create")
    fun resetPassword(
        @Query("email") email:String
    ): Observable<Response>

    @GET("doctor/search")
    fun getDoctorsList(
        @Header("Authorization")  token:String,
        @Query("address") address:String?=null,
        @Query("name") name:String?=null,
        @Query("specialization") specialization:String?=null
    ): Observable<DoctorsListResult>

    @POST("doctor/rating/add")
    fun rate(
        @Header("Authorization")  token:String,
        @Query("doctor_id") doctor_id:Int,
        @Query("value") value:Int,
        @Query("user_id") user_id:Int
    ): Observable<Response>
}