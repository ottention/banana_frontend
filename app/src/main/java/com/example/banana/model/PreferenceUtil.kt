package com.example.banana.model

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context : Context) {
    private val prefs : SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key:String, defValue: String) : String {
        return prefs.getString(key, "").toString()
    }
    fun setString(key:String, defValue: String) {
        prefs.edit().putString(key, defValue).apply()
    }
}