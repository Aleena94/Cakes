package com.example.cakes.viewmodel

import com.example.cakes.model.CakeListItem
import com.example.cakes.repository.CakeListingRepository
import com.example.cakes.repository.FakeCakeRepository
import junit.framework.Assert.assertNotSame
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit


class CakeListingViewModelTest {

    private lateinit var viewModel: CakeListingViewModel

    @Before
    fun setup() {
        viewModel = CakeListingViewModel(FakeCakeRepository())
    }

    @Test
    fun sortList() {
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

        val mockList1: MutableList<CakeListItem> = ArrayList<CakeListItem>()
        mockList1.add(cakeList1)
        mockList1.add(cakeList2)
        mockList1.add(cakeList3)

        val mockList2: MutableList<CakeListItem> = ArrayList<CakeListItem>()
        mockList2.add(cakeList2)
        mockList2.add(cakeList3)
        mockList2.add(cakeList1)



        assertNotSame(mockList2, viewModel.sortAlphabetically(mockList1))

    }

}