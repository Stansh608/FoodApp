package com.example.foodapp.ui.resetpass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is the Reset Password Model"
    }
    val text: LiveData<String> = _text
    // TODO: Implement the ViewModel

}