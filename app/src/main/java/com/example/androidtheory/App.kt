package com.example.androidtheory

import android.app.Application
import com.example.androidtheory.database.Database

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Database.createDatabase(this)
    }
}