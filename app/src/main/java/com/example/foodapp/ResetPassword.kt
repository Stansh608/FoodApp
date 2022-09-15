package com.example.foodapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.foodapp.databinding.ActivityResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ResetPassword : AppCompatActivity() {
    //initialize binding
    private lateinit var binding:ActivityResetPasswordBinding
    //db referencing
    private lateinit var databaseReference: DatabaseReference
    // progress dialog
    private lateinit var progressdialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progressdialog
        //configure progressDialog
        progressdialog= ProgressDialog(this)
        progressdialog.setTitle("Please wait")
        progressdialog.setMessage("Processing...")
        progressdialog.setCanceledOnTouchOutside(false)

        //getting the passed username
        val userpassed=intent.getStringExtra("EXTRA_MESSAGE")

        //inputting passed value to editText field
        //binding.etPass.setText(userpassed.toString())

        binding.btnupdate.setOnClickListener {
            //validate data
            val pass1 = binding.etPass.text.toString()
            val pass2 =binding.etCpass.text.toString()
            if (pass1.isEmpty()){
                binding.etPass.error="Please enter the Password!"

            } else if(pass2.isEmpty()){
                binding.etCpass.error="Confirm the Password!"

            }
            else if(pass1 !=pass2){
                binding.etCpass.error="The Password doesn't match!"

            }else{
                //data is validated



                //perform data update

                updatePass(userpassed,pass2)


            }

        }
    }

    private fun updatePass(userpassed: String?,pass2: String?) {
        //start progressdialog
        progressdialog.show()
        //mapping
        val editmap= mapOf(
            "pass" to pass2
        )
        //Database Reference
        databaseReference=Firebase.database.reference

        //update the user password
        databaseReference.child("register").child(userpassed.toString()).updateChildren(editmap).addOnSuccessListener {
            Toast.makeText(this,"Password has been Changed Successfully", Toast.LENGTH_LONG).show()
            progressdialog.dismiss()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }.addOnFailureListener {
            progressdialog.dismiss()
            Toast.makeText(this,"Error while updating the password!",Toast.LENGTH_LONG).show()
        }




    }
}