package com.example.android.oscarine.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.login_user.LoginUserProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _signinSuccessful = MutableLiveData<Boolean>()
    val signinSuccessful: LiveData<Boolean>
        get() = _signinSuccessful

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    init {
        _signinSuccessful.value = false
    }

    private fun postForSigningInUser(userDetails: LoginUserProperty) {
        OscarineApi.retrofitService.loginUser(userDetails = userDetails).enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _response.value = response.code().toString()
                _signinSuccessful.value = true
                resetValuesOnSigninSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }

    fun loginSubmitButtonClicked() {
        val userDetails =
            LoginUserProperty(
                username = username.value.toString(),
                password = password.value.toString()
            )
        postForSigningInUser(userDetails = userDetails)
    }

    fun resetValuesOnSigninSuccess() {
        username.value = ""
        password.value = ""
    }

}