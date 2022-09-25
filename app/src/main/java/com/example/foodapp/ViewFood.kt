package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.model.LeftItem
import com.example.foodapp.model.MealOrder
import com.example.foodapp.model.SharedReference
import com.example.foodapp.uitel.getProgressDrawable
import com.example.foodapp.uitel.loadImage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_view_food.*

class ViewFood : AppCompatActivity() {
    private lateinit var databaseReference:DatabaseReference
    private lateinit var databaseReference1:DatabaseReference
    private lateinit var databaseReference2:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_food)
        //get passed data
        val foodIntent=intent
        val foodname= foodIntent.getStringExtra("name")
        val foodimg= foodIntent.getStringExtra("img")
       // val foodinfo= foodIntent.getStringExtra("info")
       val foodDetail=foodIntent.getStringExtra("describe")
        val foodPrice = foodIntent.getStringExtra("price")
        val foodCount = foodIntent.getStringExtra("count")
        val pstn =foodIntent.getStringExtra("pstn")
        //fill in
        txt_count.text=foodCount+ " piece(s) Left"
        txt_name.text=foodname
        txt_price.text = "Ksh "+foodPrice
        txt_details.text=foodDetail
        img.loadImage(foodimg, getProgressDrawable(this))
        var num=1
        txt_counts.text=num.toString()
        btn_add.setOnClickListener {

            if(num<foodCount!!.toInt()) {
                num++
                txt_counts.text = num.toString()

            }


        }
        btn_minus.setOnClickListener {

            if(num>1) {
                num--
                txt_counts.text = num.toString()

            }
        }
        //create preference value






        btn_order.setOnClickListener {
            val myPreference = SharedReference(this)
            var ordercount= myPreference.getOrderCount()
            ordercount++
            myPreference.setOrderCount(ordercount)

            var no= txt_counts.text.toString().toInt()
            var unitprice=foodPrice!!.toInt()
            var tprice=no*unitprice
            var totalprice=tprice.toString()
            var username="stan"
            var items=txt_counts.text.toString()
            var left=foodCount!!.toInt() -no
            var the_key=ordercount.toString()



            databaseReference=FirebaseDatabase.getInstance().getReference("orders")
            val orders= MealOrder(username,foodimg, foodname,totalprice,items)
            databaseReference.child(username).child(the_key).setValue(orders).addOnSuccessListener {

                databaseReference1=FirebaseDatabase.getInstance().getReference("foodItem")
                val left_Item= mapOf(
                    "count" to left.toString()
                )
                databaseReference1.child(pstn.toString()).updateChildren(left_Item)

                Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Orders::class.java))
            }.addOnFailureListener {
                Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show()
            }

        }


    }
}