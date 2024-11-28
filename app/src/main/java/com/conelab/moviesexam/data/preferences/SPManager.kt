package com.conelab.moviesexam.data.preferences

import android.content.Context
import android.content.SharedPreferences

class SPManager(context: Context) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCES_NAME = "movies_preferences"
        private const val KEY_UPDATED = "updated"
        private const val KEY_DATE = "date"
    }

    fun isUpdated(): Boolean {
        return sharedPreferences.getBoolean(KEY_UPDATED, false)
    }
    fun setUpdated(updated: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_UPDATED, updated).apply()
    }

    fun getDate(): String? {
        return sharedPreferences.getString(KEY_DATE, null)
    }
    fun setDate(date: String) {
        sharedPreferences.edit().putString(KEY_DATE, date).apply()
    }
}