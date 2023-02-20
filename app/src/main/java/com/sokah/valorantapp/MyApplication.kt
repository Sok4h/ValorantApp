package com.sokah.valorantapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sokah.valorantapp.db.ValorantDatabase

class MyApplication : Application() {

    override fun onCreate() {

        super.onCreate()
        context = this
    }


    companion object {

        private lateinit var context: Context
        private val room by lazy {
            Room.databaseBuilder(context, ValorantDatabase::class.java, "mydb")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getDatabase()=room
    }

}