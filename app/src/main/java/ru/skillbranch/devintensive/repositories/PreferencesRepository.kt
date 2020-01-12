package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPO = "REPO"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfileData(): Profile {
        val a: Profile = Profile("", "", "", "", "", "")
        return a
    }

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(FIRST_NAME to firstName)!!
            putValue(LAST_NAME to lastName)!!
            putValue(ABOUT to about)!!
            putValue(REPO to repository)!!
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first
        val value = pair.second

        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            else -> error("Only primitives types can be stored in Shared Preferences")
        }

        apply()
    }
}