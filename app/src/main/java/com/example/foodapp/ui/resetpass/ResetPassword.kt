package com.example.foodapp.ui.resetpass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.databinding.FragmentResetPasswordBinding
import com.example.foodapp.ui.notifications.SlideshowViewModel

class ResetPassword : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*



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
         */


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
