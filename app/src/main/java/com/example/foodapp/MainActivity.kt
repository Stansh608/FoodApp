package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.model.passUser
import com.example.foodapp.model.userName
//import com.example.foodapp.model.userName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //progressDialog
    private lateinit var progressdialog: ProgressDialog
//dbreference
    private lateinit var database: DatabaseReference
    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progressDialog
        progressdialog= ProgressDialog(this)
        progressdialog.setTitle("Please wait")
        progressdialog.setMessage("Logging in...")
        progressdialog.setCanceledOnTouchOutside(false)

        //Initialise firebase
        firebaseAuth=FirebaseAuth.getInstance()

        //check if logged in
        checkuser()

        binding.tvForgotPass.setOnClickListener{
            startActivity(Intent(this, ForgotPassword::class.java))
        }
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
        binding.btnLogin.setOnClickListener {
            validateUser()
        }
    }
    private fun checkuser() {
         val firebaseuser= firebaseAuth.currentUser
         if (firebaseuser!=null){
             //user is already logged in
             startActivity(Intent(this, DashBoard::class.java))
             finish()
         }
    }

    private fun validateUser() {



        //get the data
        email= binding.etEmail.text.toString().trim()
        password=binding.etPassword.text.toString().trim()
        if(email.isEmpty()){
            binding.etEmail.error=" This field cannot be empty"
        }else if(password.isEmpty()){
            binding.etPassword.error=" This field cannot be empty"
        } else{
            progressdialog.show()


        database= FirebaseDatabase.getInstance().getReference("register")
        database.child(email).get().addOnSuccessListener {
            if (it.exists()) {
                //get data
                val pass = it.child("pass").value
                val username = it.child("username").value
                val phone = it.child("phone").value
                val email = it.child("email").value
                val name = it.child("name").value
                //val  = it.child("pass").value
                //val idno = it.child("idno").value
                if (pass == password) {
                    progressdialog.dismiss()
                    val myPrefer = userName(this)

                    myPrefer.setUser(username.toString())

                    var usere=myPrefer.getUser()
                    var a = passUser()
                    a.getUser(usere)
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    var intent=Intent(this,DashBoard::class.java)
                    //create object of userdetails class

                    startActivity(intent)
                    finish()
                } else {
                    progressdialog.dismiss()
                    Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_LONG).show()
                }
            } else {
                progressdialog.dismiss()
                Toast.makeText(this, "User not found!", Toast.LENGTH_LONG).show()
            }

        }




    }
    }

}