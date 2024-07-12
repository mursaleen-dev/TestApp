package com.firstbit.composeapp

import android.app.Application
import androidx.room.Room
import com.firstbit.composeapp.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}