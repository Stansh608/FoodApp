package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.foodapp.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class ForgotPassword : AppCompatActivity() {
    //initialize binding sth
    private lateinit var binding: ActivityForgotPasswordBinding
    //initialize progress dialog
    private lateinit var progressdialog: ProgressDialog

    //initialize firebase
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progressDialog
        progressdialog= ProgressDialog(this)
        progressdialog.setTitle("Please wait")
        progressdialog.setMessage("Validating...")
        progressdialog.setCanceledOnTouchOutside(false)

        //btnreset
        binding.btnReset.setOnClickListener {
            val username : String = binding.etUsername.text.toString()
            if(username.isEmpty()){
                binding.etUsername.error = "This field cannot be empty!"
            }

            if (username.isNotEmpty()){
                getData(username)

            }




        }
    }

    private fun getData(username: String) {
        progressdialog.show()
        database= FirebaseDatabase.getInstance().getReference("register")
        database.child(username).get().addOnSuccessListener {
            if (it.exists()){
                //get data
                //val username=it.child("username").value
               val idno= it.child("idNo").value.toString().trim()

                    //mapping
                    val editmap= mapOf(
                        "pass" to idno
                    )
                    //Database Reference
                    database= Firebase.database.reference

                    //update the user password
                    database.child("register").child(username).updateChildren(editmap).addOnSuccessListener {
                    Toast.makeText(this,"Password  Reset to  your Id Number",Toast.LENGTH_SHORT).show()

                        //Doing value passing
                        val intent= Intent(this, MainActivity::class.java).also {
                            it.putExtra("EXTRA_MESSAGE",username.toString())

                        }
                        progressdialog.dismiss()
                        startActivity(intent)
                        finish()
                    }


            }else{
                Toast.makeText(this,"Registration number not Found!",Toast.LENGTH_LONG).show()
                progressdialog.dismiss()
            }
        }
    }

}