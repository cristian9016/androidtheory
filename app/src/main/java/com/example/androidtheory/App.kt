package com.example.androidtheory

import android.app.Application
import com.example.androidtheory.database.Database
import com.example.androidtheory.preferences.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.initPrefs(this)
        Database.createDatabase(this)
    }
}
