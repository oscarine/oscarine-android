package com.example.android.oscarine.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.login_user.LoginUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()


    private fun postForSigningInUser(userDetails: LoginUser) {
        OscarineApi.retrofitService.loginUser(userDetails).enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                _response.value = response.code().toString()
                resetValuesOnSigninSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }

    fun login() {
        val userDetails =
            LoginUser(
                username = username.value.toString(),
                password = password.value.toString()
            )
        postForSigningInUser(userDetails)
    }

    fun resetValuesOnSigninSuccess() {
        username.value = ""
        password.value = ""
    }

}