package com.example.android.oscarine.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.RegisterUserProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        postForRegisteringNewUser()
        Log.i("SignupViewModel", "Initialized!!!!!!!!!!!!!!")
    }

    private fun postForRegisteringNewUser() {
        val newUser = RegisterUserProperty(
            username = "haider8-12345",
            email = "haider8test@test.io",
            password = "32dcdcd"
        )
        OscarineApi.retrofitService.registerNewUser(newUser = newUser).enqueue( object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _response.value = response.body().toString()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }

}