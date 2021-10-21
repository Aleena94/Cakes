package com.example.cakes.view

import android.util.Log
import com.example.cakes.model.CakeListItem
import com.example.cakes.retrofit.ApiInterface
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertNotSame
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors


class CakeListingActivityTest {

    @Test
    fun checkDataLoaded() {
        val service = RetrofitClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
        val call = service.getCakeList()
        call!!.enqueue(object : Callback<List<CakeListItem>?> {
            override fun onFailure(call: Call<List<CakeListItem>?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<CakeListItem>?>,
                response: Response<List<CakeListItem>?>
            ) {
                assertThat(response.isSuccessful).isEqualTo(true)

            }
        })
    }

    @Test
    fun checkDuplicatesRemoved() {
        val cakeList1 = CakeListItem(
            "A cheesecake made of lemon",
            "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg",
            "Lemon cheesecake"
        )
        val cakeList2 = CakeListItem(
            "sponge with jam",
            "https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg",
            "victoria sponge"
        )
        val cakeList3 = CakeListItem(

            "Bugs bunnys favourite",
            "https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg",
            "Carrot cake"
        )
        val cakeList4 = CakeListItem(
            "sponge with jam",
            "https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg",
            "victoria sponge"
        )
        val mockList1: MutableList<CakeListItem> = ArrayList<CakeListItem>()
        mockList1.add(cakeList1)
        mockList1.add(cakeList2)
        mockList1.add(cakeList3)
        mockList1.add(cakeList4)

        assertNotSame(
            mockList1.size,
            mockList1.stream().distinct().collect(Collectors.toList()).size
        )
    }

}