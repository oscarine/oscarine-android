package com.example.android.oscarine.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.register_user.RegisterUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {

    private val _messages = MutableLiveData<String>()
    val messages: LiveData<String>
        get() = _messages

    private val _signupSuccessful = MutableLiveData<Boolean>()
    val signupSuccessful: LiveData<Boolean>
        get() = _signupSuccessful

    val username = MutableLiveData<String>()

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val confirmPassword = MutableLiveData<String>()

    init {
        _signupSuccessful.value = false
    }


    private fun postForRegisteringNewUser(newUser: RegisterUser) {
        OscarineApi.retrofitService.registerNewUser(newUser).enqueue( object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val responseCode = response.code()
                if (responseCode == 201) {
                    _messages.value = "Registration successful"
                    _signupSuccessful.value = true
                    resetValuesOnSignupSuccess()
                } else {
                    _messages.value = "Registration failed"
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                _messages.value = "Failure: " + t.message
            }

        })
    }

    fun submitButtonClicked() {
        if (password.value == confirmPassword.value) {
            val newUser =
                RegisterUser(
                    username = username.value.toString(),
                    email = email.value.toString(),
                    password = password.value.toString()
                )
            postForRegisteringNewUser(newUser)
        } else {
            _messages.value = "Passwords didn't match. Please try again"
        }
    }

    fun resetValuesOnSignupSuccess() {
        username.value = ""
        email.value = ""
        password.value = ""
        confirmPassword.value = ""
    }

    fun signupSuccessfullyCompleted() {
        _signupSuccessful.value = false
    }

    override fun onCleared() {
        super.onCleared()
        _signupSuccessful.value = false
    }

}