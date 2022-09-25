package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.foodapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    //firebase db reference
    private lateinit var database : DatabaseReference

    //initialize progressdialog
    private lateinit var progressdialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //progress dialog configs
        progressdialog= ProgressDialog(this)
        progressdialog.setTitle("Please wait")
        progressdialog.setMessage("Processing...")
        progressdialog.setCanceledOnTouchOutside(false)

        //onclick signup
        binding.btnSignup.setOnClickListener {
            validateDetails()
        }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun validateDetails() {
        //get the data
        val username = binding.etUsername.text.toString()
        val names= binding.etNames.text.toString()
        val phone =binding.etPhone.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPass.text.toString()
        val pass2= binding.etCpass.text.toString()
        val idno= binding.etIdno.text.toString()

        //validating the data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error="Invalid email"

        }else if (pass!=pass2){

            binding.etCpass.error= "Password does not match"
        }else if(username.isEmpty()){
            binding.etUsername.error="This field cannot be empty!"
        } else if(pass.isEmpty()){
        binding.etPass.error="This field cannot be empty!"
    } else if(idno.isEmpty()) {
            binding.etIdno.error = "This field cannot be empty!"
        }else if(names.isEmpty()){
            binding.etNames.error="Enter your name"
        }else if(phone.isEmpty()){
            binding.etPhone.error="This field cannot be empty"
        }
        //the data is validated
        else{
            //start progressdialog
            progressdialog.show()


// referencing to realtime database
            database = FirebaseDatabase.getInstance().getReference("register")
            //creating the users object
            val Usr= Register(username, names,idno, phone, email, pass)
            //passing it to the realtime firebase db
            database.child(username).setValue(Usr).addOnSuccessListener {
                // on success clear the edittexts
                binding.etUsername.text?.clear()
                binding.etNames.text?.clear()
                binding.etPhone.text?.clear()
                binding.etEmail.text?.clear()
                binding.etPass.text?.clear()
                binding.etCpass.text?.clear()
                binding.etIdno.text?.clear()

                // a popup msg on success
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                progressdialog.dismiss()
                startActivity(Intent(this, Homepage::class.java))
                finish()
                // a popup msg on failure
            }.addOnFailureListener{
                progressdialog.dismiss()
                Toast.makeText(this, "Failed. Check your internet connection and try again", Toast.LENGTH_LONG).show()
            }


        }
    }
}