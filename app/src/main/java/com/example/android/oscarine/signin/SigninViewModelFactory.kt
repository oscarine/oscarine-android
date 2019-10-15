package com.example.android.oscarine.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.oscarine.SharedPreference

class SigninViewModelFactory(private val sharedPreference: SharedPreference) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SigninViewModel::class.java)) {
            return SigninViewModel(sharedPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}