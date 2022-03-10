package com.ezetap.network

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("mobileapps/android_assignment")
    fun fetchCustomUI(): Call<String>

    @GET("mobileapps/android_assignment")
    fun fetchImage(): Call<String>
}