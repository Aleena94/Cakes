package com.example.cakes.retrofit

import com.example.cakes.model.CakeListItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("waracle_cake-android-client")
    fun getCakeList(): Call<List<CakeListItem>?>?

}