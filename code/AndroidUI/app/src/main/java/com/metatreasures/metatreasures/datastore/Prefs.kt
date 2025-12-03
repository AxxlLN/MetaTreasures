package com.metatreasures.metatreasures.datastore

import android.content.Context

object Prefs {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_EXPERIENCE_SHOWN = "experience_shown"

    fun isExperienceShown(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_EXPERIENCE_SHOWN, false)
    }

    fun setExperienceShown(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_EXPERIENCE_SHOWN, true).apply()
    }
}
