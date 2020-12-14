package com.example.binarchallenge5

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

object PreferencesUtil {
    private fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setPrefName(name: String, context: Context) {
        sharedPreferences(context).edit().putString("name", name).apply()
    }

    fun getPrefName(context: Context) = sharedPreferences(context).getString("name", "")

}