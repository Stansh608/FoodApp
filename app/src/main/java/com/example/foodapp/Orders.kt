package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodapp.model.passUser
import com.example.foodapp.model.userName
import kotlinx.android.synthetic.main.activity_orders.*

class Orders : AppCompatActivity() {
    private val ordersFragment= OrdersFragment()
            private val processedFragment =ProcessedFragment()
                    private val cancelledFragment = CancelledFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        replaceFragment(ordersFragment)
        //set onclicklistener to the bottom navigation
        bottomnavigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_orders -> replaceFragment(ordersFragment)
                R.id.ic_processed -> replaceFragment(processedFragment)
                R.id.ic_Cancelled -> replaceFragment(cancelledFragment)
            }
            true
        }


    }


    private fun replaceFragment(fragment: Fragment) {
/*
        val fragmentManager= supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentOrder,fragment)
        fragmentTransaction.commit()

 */
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentOrder, fragment)
        transaction.commit()

    }
}