package com.example.cakes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cakes.model.CakeListItem
import com.example.cakes.repository.CakeListingRepository
import com.example.cakes.repository.CakeRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CakeListingViewModel @Inject constructor(private val repository: CakeListingRepository) : ViewModel() {

    private var cakeList = MutableLiveData<List<CakeListItem>>()

    fun getCakes():MutableLiveData<List<CakeListItem>>{
        cakeList=repository.getCakes()
        return cakeList
    }


    fun sortAlphabetically(arrayList: List<CakeListItem>): List<CakeListItem> {
        var returnList: List<CakeListItem>
        var list = arrayList as MutableList<CakeListItem>
        list.sortWith(Comparator { o1: CakeListItem, o2: CakeListItem ->
            o1.title.compareTo(o2.title)
        })
        returnList = list as ArrayList<CakeListItem>
        return returnList
    }


}