package com.testapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testapp.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<User>)

    @Query("SELECT * FROM user ")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email like :email AND password like :pass")
    fun getUser(email: String, pass: String): LiveData<User>

    @Query("SELECT email FROM user WHERE email like :email AND password like :pass")
    fun checkPass(email: String, pass: String): LiveData<String>
}