package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.foodAdapter
import com.example.foodapp.databinding.ActivityHomepageBinding
import com.example.foodapp.model.FoodData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
//import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//import com.google.protobuf.Internal
import kotlinx.android.synthetic.main.fragment_home.*

class Homepage : AppCompatActivity() {

    /*

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomepageBinding

    //Firebase Auth
    private lateinit var firebaseAuth:FirebaseAuth

    //Dialogprogress
    private lateinit var progressDialog:ProgressDialog
    //connect Firebase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var foodList:ArrayList<FoodData>
    private lateinit var mAdapter: foodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
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




        //firebase auth instance
        firebaseAuth= FirebaseAuth.getInstance()
        //config progressDialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Logging Off ...")
        progressDialog.setCanceledOnTouchOutside(false)
        setSupportActionBar(binding.appBarHomepage.toolbar)


        binding.appBarHomepage.fab.setOnClickListener { view ->
            Snackbar.make(view, "Orders", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_homepage)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_porders,R.id.nav_orders, R.id.nav_notification, R.id.nav_cpass
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun getFoodData() {
        //database sync <firebase>
        mDatabase=FirebaseDatabase.getInstance().getReference("Food")
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
                Toast.makeText(this@Homepage, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    // create firebase data now

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.homepage, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
               if (item.itemId==R.id.action_settings) {
                progressDialog.show()
                Toast.makeText(this, "Logging out ...", Toast.LENGTH_SHORT).show()
                firebaseAuth.signOut()
                progressDialog.dismiss()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return true

            }else{


        return false
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_homepage)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

     */
}