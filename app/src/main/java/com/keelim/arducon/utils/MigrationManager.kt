//package com.keelim.arducon.utils
//
//import android.content.Context
//import androidx.datastore.preferences.SharedPreferencesMigration
//import androidx.datastore.preferences.core.emptyPreferences
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.createDataStore
//
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.map
//import java.io.IOException
//
//class MigrationManager(context: Context) {
//    val dataStore = context.createDataStore(
//        name = PREFERENCENAME,
//        migrations = listOf(SharedPreferencesMigration(context,
//            PREFERENCENAME
//        ))
//    )
//
//    val dataFlow: Flow<String> = dataStore.data
//        .catch { exception ->
//            if (exception is IOException) {
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }.map {
//            val firstName = it[PREF_FIRST_NAME] ?: "light"
//                firstName
//        }
//
//    companion object {
//        const val PREFERENCENAME = "themePref"
//        const val THEME = "theme"
//
//        val PREF_FIRST_NAME = stringPreferencesKey(THEME)
//    }
//}