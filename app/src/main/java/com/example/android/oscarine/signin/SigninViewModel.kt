package com.example.android.oscarine.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.oscarine.SharedPreference
import com.example.android.oscarine.network.OscarineApi
import com.example.android.oscarine.network.models.login_user.LoginServerResponse
import com.example.android.oscarine.network.models.login_user.LoginUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel(private val sharedPreference: SharedPreference): ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()


    private fun postForSigningInUser(userDetails: LoginUser) {
        OscarineApi.retrofitService.loginUser(userDetails).enqueue(object: Callback<LoginServerResponse> {
            override fun onFailure(call: Call<LoginServerResponse>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(
                call: Call<LoginServerResponse>,
                response: Response<LoginServerResponse>
            ) {
                _response.value = response.code().toString()
                val token = response.body()!!.access_token
                saveToken(token)
                resetValuesOnSigninSuccess()
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

    fun saveToken(token: String) {
        sharedPreference.setToken(token)
    }
}