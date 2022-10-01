package com.example.foodapp.foodordersfragmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.foodordersrepository.FoodOrdersFragRepository
import com.example.foodapp.foodordersrepository.processedfoodrepository

class processedfoodviewmodel: ViewModel(){

    private val processedrepository : processedfoodrepository
    private val _allprocessed= MutableLiveData<List<processedfooddataclass>>()
    val allprocessed : LiveData<List<processedfooddataclass>> = _allprocessed

    init {
        processedrepository = processedfoodrepository().getInstance()
        processedrepository.loadFood(_allprocessed)
    }
}