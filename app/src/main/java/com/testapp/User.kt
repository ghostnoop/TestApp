package com.testapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mvpbook.strorage.SharedPrefManager

@Entity
class User(
    @PrimaryKey val id: Int,
    val email: String,
    val name: String,
    val lastname: String,
    val photo: String,
    val password: String
) {
}