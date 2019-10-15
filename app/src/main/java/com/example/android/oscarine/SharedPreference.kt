package com.example.android.oscarine

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreference(context: Context) {

    private val preferenceName = "oscarine_preferences"
    private val token = "Token"

    private val preference: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return preference.getString(token, null)
    }

    fun setToken(token: String) {
        val editor = preference.edit()
        editor.putString(this.token, token)
        editor.apply()
        Log.i("SharedPreference", "token saved successfully")
    }

}