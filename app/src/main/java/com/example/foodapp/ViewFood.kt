package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodapp.uitel.getProgressDrawable
import com.example.foodapp.uitel.loadImage
import kotlinx.android.synthetic.main.activity_view_food.*

class ViewFood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_food)
        //get passed data
        val foodIntent=intent
        val foodname= foodIntent.getStringExtra("name")
        val foodimg= foodIntent.getStringExtra("img")
        val foodinfo= foodIntent.getStringExtra("info")
        val foodDetail=foodIntent.getStringExtra("details")
        val foodPrice = foodIntent.getStringExtra("price")
        val foodCount = foodIntent.getStringExtra("count")
        //fill in
        txt_name.text=foodname
        txt_price.text = "Ksh "+foodPrice
        txt_details.text=foodDetail
        img.loadImage(foodimg, getProgressDrawable(this))



    }
}