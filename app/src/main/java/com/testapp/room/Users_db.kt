package com.testapp.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.testapp.User

@Database(version = 1, entities = [User::class])
abstract class Users_db : RoomDatabase() {
    companion object {
        fun get(application: Application): Users_db {
            return Room.databaseBuilder(application, Users_db::class.java, "users")
                .build()
        }
    }

    abstract fun getUserDao(): UsersDao
}