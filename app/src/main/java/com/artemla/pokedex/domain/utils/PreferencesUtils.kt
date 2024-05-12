package com.artemla.pokedex.domain.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtils private constructor(context: Context) {
    private var preferences: SharedPreferences
    private val PREFERENCES_NAME = "preferences"
    private val FIRST_LAUNCH = "first_launch"


    companion object {
        private var instance: PreferencesUtils? = null
        private lateinit var editor: SharedPreferences.Editor

        fun getInstance(context: Context): PreferencesUtils {
            if (instance == null) {
                synchronized(PreferencesUtils::class.java) {
                    if (instance == null) {
                        instance = PreferencesUtils(context)
                    }
                }
            }
            return instance!!
        }
    }

    init {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        return if (preferences.contains(FIRST_LAUNCH)) {
            false
        } else {
            setFirstLaunch()
            true
        }
    }

    private fun setFirstLaunch() {
        editor = preferences.edit()
        editor.putBoolean(FIRST_LAUNCH, true).apply()
    }
}