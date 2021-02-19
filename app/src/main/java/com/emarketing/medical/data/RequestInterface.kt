package com.emarketing.medical.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RequestInterface {

    @GET
    fun downlload(@Url fileUrl: String?): Call<ResponseBody>


    @POST("user-register")
    fun register(
        @Query("name") name:String,
        @Query("email") file_id:String,
        @Query("phone") phone:String,
        @Query("password") password:String,
        @Query("message") message:String
    ): Observable<RegisterResponse>

    @POST("center/initialize_search")
    fun getFilters(): Observable<Filters>

    @POST("center/with_filters")
    fun search(
        @Query("tag") tag:Long?,
        @Query("category") category:Long?,
        @Query("city") city:Long?
    ): Observable<SearchResult>

    @GET("category")
    fun getCategories(): Observable<CategoryResult>
}