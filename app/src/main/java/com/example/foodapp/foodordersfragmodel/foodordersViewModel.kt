package com.example.foodapp.foodordersfragmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.foodordersrepository.FoodOrdersFragRepository

//casted from repository
class foodordersViewModel : ViewModel(){

    private val repository : FoodOrdersFragRepository
    private val _allmeals= MutableLiveData<List<FoodOrdersFragDataClass>>()
    val allmeals : LiveData<List<FoodOrdersFragDataClass>> = _allmeals

    init {
        repository = FoodOrdersFragRepository().getInstance()
        repository.loadFood(_allmeals)
    }
}