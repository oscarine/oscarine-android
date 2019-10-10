package com.example.android.oscarine.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.register_user.RegisterUserProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _signupSuccessful = MutableLiveData<Boolean>()
    val signupSuccessful: LiveData<Boolean>
        get() = _signupSuccessful

    val username = MutableLiveData<String>()

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val confirmPassword = MutableLiveData<String>()

    init {
        _signupSuccessful.value = false
        _response.value = ""
    }


    private fun postForRegisteringNewUser(newUser: RegisterUserProperty) {
        OscarineApi.retrofitService.registerNewUser(newUser = newUser).enqueue( object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _response.value = response.code().toString()
                _signupSuccessful.value = true
                resetValuesOnSignupSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }

    fun submitButtonClicked() {
        if (password.value == confirmPassword.value) {
            val newUser =
                RegisterUserProperty(
                    username = username.value.toString(),
                    email = email.value.toString(),
                    password = password.value.toString()
                )
            postForRegisteringNewUser(newUser = newUser)
        } else {
            Log.i("SignupViewModel", "Passwords didn't match !")
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
        _response.value = ""
    }

}