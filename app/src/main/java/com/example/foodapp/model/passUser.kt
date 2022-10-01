package com.example.foodapp.model

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*

class passUser{
    lateinit var c:String
    fun getUser(a:String){
       this.c=a

    }
    fun returnUser(): String {return c
    }





}