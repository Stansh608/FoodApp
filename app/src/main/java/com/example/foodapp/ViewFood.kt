package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import com.example.foodapp.model.LeftItem
import com.example.foodapp.model.MealOrder
import com.example.foodapp.model.SharedReference
import com.example.foodapp.model.userName
//import com.example.foodapp.model.userName
import com.example.foodapp.uitel.getProgressDrawable
import com.example.foodapp.uitel.loadImage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_view_food.*

class ViewFood : AppCompatActivity() {
    private lateinit var databaseReference:DatabaseReference
    private lateinit var databaseReference1:DatabaseReference
    private lateinit var databaseReference2:DatabaseReference
    private lateinit var progressdialog:ProgressDialog
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

            //add the pieces
            if(num<foodCount!!.toInt()) {
                num++
                txt_counts.text = num.toString()

            }


        }
        btn_minus.setOnClickListener {

            //reduce the pieces
            if(num>1) {
                num--
                txt_counts.text = num.toString()

            }
        }

//configure progressDialog
        progressdialog= ProgressDialog(this)
        progressdialog.setTitle("Please wait")
        progressdialog.setMessage("Processing...")
        progressdialog.setCanceledOnTouchOutside(false)



// get logged in user

        val refuser= userName(this)

        var username=refuser.getUser()
        btn_order.setOnClickListener {

            progressdialog.show()

            val myPreference = SharedReference(this)
            var ordercount= myPreference.getOrderCount()
            ordercount++
            myPreference.setOrderCount(ordercount)


            var no= txt_counts.text.toString().toInt()
            var unitprice=foodPrice!!.toInt()
            var tprice=no*unitprice
            var totalprice=tprice.toString()

            var items=txt_counts.text.toString()
            var left=foodCount!!.toInt() -no
            var the_key=ordercount.toString()
            if (foodCount!!.toInt()>0) {

                databaseReference = FirebaseDatabase.getInstance().getReference("orders")
                val orders = MealOrder(the_key, username, foodimg, foodname, totalprice, items)
                databaseReference.child(the_key).setValue(orders).addOnSuccessListener {

                    databaseReference1 = FirebaseDatabase.getInstance().getReference("foodItem")
                    val left_Item = mapOf(
                        "count" to left.toString()
                    )
                    databaseReference1.child(pstn.toString()).updateChildren(left_Item)
                    progressdialog.dismiss()


                    val obj = SmsManager.getDefault()
                    obj.sendTextMessage("+254740859674","I", "A new order has been placed, Check the system for further processing", null, null)

                    Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show()



                    startActivity(Intent(this, Orders::class.java))
                    finish()
                }.addOnFailureListener {
                    progressdialog.dismiss()
                    Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }else{
                progressdialog.dismiss()
                Toast.makeText(this, "Failed.  This Item is out of Stock!!", Toast.LENGTH_LONG).show()
                finish()
            }

        }


    }
}