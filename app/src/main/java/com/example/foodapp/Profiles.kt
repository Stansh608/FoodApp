package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.foodapp.model.userName
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Profiles : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)
        var username= userName(this)

        var regno=username.getUser()
        var name=findViewById<TextView>(R.id.tvName)
        var reg=findViewById<TextView>(R.id.tvregno)
        var id=findViewById<TextView>(R.id.etid)
        databaseReference= FirebaseDatabase.getInstance().getReference("register")
        databaseReference.child(regno).get().addOnSuccessListener {
            if (it.exists()) {
                name.text = it.child("name").value.toString()
                reg.text = it.child("username").value.toString()
                id.text = it.child("idNo").value.toString()
            }
        }
    }
}