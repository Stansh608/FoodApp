package com.example.foodapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.foodAdapter
import com.example.foodapp.databinding.ActivityDashBoardBinding
import com.example.foodapp.model.FoodData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

class DashBoard : AppCompatActivity() {
    //Firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding:ActivityDashBoardBinding
    //Dialogprogress
    private lateinit var progressDialog: ProgressDialog
    //connect Firebase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var foodList:ArrayList<FoodData>
    private lateinit var mAdapter: foodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implementing recyclerview
        //initializing
        foodList = ArrayList()
        mAdapter= foodAdapter(this, foodList)
        recyclerFood.layoutManager = LinearLayoutManager(this)
        recyclerFood.setHasFixedSize(true)
        recyclerFood.adapter=mAdapter
        // get food data from firebase
        getFoodData()

    }

    private fun getFoodData() {
        //database sync <firebase>
        mDatabase= FirebaseDatabase.getInstance().getReference("foodItem")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //
                if (snapshot.exists()){
                    for (foodSnapshot in snapshot.children){
                        val food = foodSnapshot.getValue(FoodData :: class.java)
                        foodList.add(food!!)
                    }
                    recyclerFood.adapter=mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DashBoard, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    // create firebase data now
}