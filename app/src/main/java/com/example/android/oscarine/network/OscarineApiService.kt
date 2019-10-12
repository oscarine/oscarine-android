package com.example.android.oscarine.network

import com.example.android.oscarine.network.models.login_user.LoginUser
import com.example.android.oscarine.network.models.register_user.RegisterUser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_API_URL = "https://oscarine.herokuapp.com/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_API_URL)
    .build()

interface OscarineApiService {

    @POST("users")
    fun registerNewUser(@Body newUser: RegisterUser):
            Call<Any>

    @POST("login")
    fun loginUser(@Body userDetails: LoginUser):
            Call<Any>

}


/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object OscarineApi {
    val retrofitService: OscarineApiService by lazy { retrofit.create(OscarineApiService::class.java) }
}