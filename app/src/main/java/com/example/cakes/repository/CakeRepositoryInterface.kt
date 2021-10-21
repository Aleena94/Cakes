package com.example.cakes.repository

import androidx.lifecycle.MutableLiveData
import com.example.cakes.model.CakeListItem

interface CakeRepositoryInterface {

    fun getCakes() : MutableLiveData<List<CakeListItem>>
}