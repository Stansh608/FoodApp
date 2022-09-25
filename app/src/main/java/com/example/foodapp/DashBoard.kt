package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.view.Menu

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.foodAdapter
import com.example.foodapp.databinding.ActivityDashBoardBinding
import com.example.foodapp.model.FoodData
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.recyclerFood
import java.util.*
import kotlin.collections.ArrayList

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
    //implement search bar
    private lateinit var newfoodList:ArrayList<FoodData>

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
        //
        //search icon
        newfoodList= arrayListOf<FoodData>()

        // get food data from firebase
        getFoodData()

        //onclick myorders
        my_orders.setOnClickListener {
            startActivity(Intent(this, Orders::class.java))
        }
    }
//Implementng the search icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
    val item= menu?.findItem(R.id.search_icon)
    val searchView=item?.actionView as SearchView
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            TODO("Not yet implemented")
        }

        override fun onQueryTextChange(newText: String?): Boolean {
           newfoodList.clear()
            val searchText = newText!!.toLowerCase(Locale.getDefault())
            if (searchText.isNotEmpty()){
                newfoodList.forEach {
                    if (it.name!!.toLowerCase(Locale.getDefault()).contains(searchText)){
                        newfoodList.add(it)

                    }
                }
                //after looping through
                recyclerFood.adapter!!.notifyDataSetChanged()
            }
            else{
                newfoodList.clear()
                newfoodList.addAll(foodList)
                recyclerFood.adapter!!.notifyDataSetChanged()
            }
            return false
        }

    })

        return super.onCreateOptionsMenu(menu)
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
                //search icon item
                //newfoodList.addAll(foodList)
            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DashBoard, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    // create firebase data now
}