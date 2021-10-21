package com.example.cakes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cakes.model.CakeListItem
import com.example.cakes.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CakeListingRepository  @Inject constructor(private val retrofitClient: Retrofit) : CakeRepositoryInterface {
    private val cakeList = MutableLiveData<List<CakeListItem>>()

    override fun getCakes(): MutableLiveData<List<CakeListItem>> {
        val service = retrofitClient.create(ApiInterface::class.java)
        val call= service.getCakeList()
        call!!.enqueue(object : Callback<List<CakeListItem>?> {
            override fun onFailure(call: Call<List<CakeListItem>?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<CakeListItem>?>,
                response: Response<List<CakeListItem>?>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                cakeList.postValue(response.body())
            }
        })
       return cakeList
    }
}