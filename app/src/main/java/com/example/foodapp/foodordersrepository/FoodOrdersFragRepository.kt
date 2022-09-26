package com.example.foodapp.foodordersrepository

import androidx.lifecycle.MutableLiveData
import com.example.foodapp.foodordersfragmodel.FoodOrdersFragDataClass
import com.google.firebase.database.*
import java.lang.Exception

class FoodOrdersFragRepository {

    //create database reference
    private val databaseReference: DatabaseReference= FirebaseDatabase.getInstance().getReference("orders").child("stan")

    // ensure only a single instance of this class exists
    //only when null willanother instance be created
    @Volatile private var INSTANCE : FoodOrdersFragRepository ?=null

    fun getInstance(): FoodOrdersFragRepository{
        return  INSTANCE ?: synchronized(this){
        val instance=FoodOrdersFragRepository()
        INSTANCE=instance
        instance
    }
}
    //get the data , when a change in db,, modify the food lists
    fun loadFood(foodAdapterList: MutableLiveData<List<FoodOrdersFragDataClass>>){
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //when data changes
                try {
                    val _foodList : List<FoodOrdersFragDataClass> = snapshot.children.map{ dataSnapshot ->

                        dataSnapshot.getValue(FoodOrdersFragDataClass::class.java)!!
                    }

                    foodAdapterList.postValue(_foodList)

                }catch (e: Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}