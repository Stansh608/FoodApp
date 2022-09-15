package com.example.foodapp.ui.home

import android.app.ProgressDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class HomeViewModel : ViewModel() {

    private var _binding: FragmentResetPasswordBinding? = null

    //db referencing
    private lateinit var databaseReference: DatabaseReference
    // progress dialog
    private lateinit var progressdialog: ProgressDialog

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val _text = MutableLiveData<String>().apply {
        value = "This is home "
    }
    val text: LiveData<String> = _text
}