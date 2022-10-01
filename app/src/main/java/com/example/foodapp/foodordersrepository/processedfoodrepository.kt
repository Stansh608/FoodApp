package com.example.foodapp.foodordersrepository

import androidx.lifecycle.MutableLiveData
import com.example.foodapp.foodordersfragmodel.FoodOrdersFragDataClass
import com.example.foodapp.foodordersfragmodel.processedfooddataclass
import com.google.firebase.database.*
import java.lang.Exception

class processedfoodrepository {
    // get logged in user
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("processed")

    // ensure only a single instance of this class exists
    //only when null willanother instance be created
    @Volatile private var INSTANCE2 : processedfoodrepository ?=null

    fun getInstance(): processedfoodrepository{

        return  INSTANCE2 ?: synchronized(this){
            val instance2=processedfoodrepository()
            INSTANCE2=instance2
            instance2
        }
    }
    //get the data , when a change in db,, modify the food lists
    fun loadFood(processedAdapterList: MutableLiveData<List<processedfooddataclass>>){

        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //when data changes
                try {
                    val _processedList : List<processedfooddataclass> = snapshot.children.map{ dataSnapshot ->

                        dataSnapshot.getValue(processedfooddataclass::class.java)!!
                    }

                    processedAdapterList.postValue(_processedList)

                }catch (e: Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}