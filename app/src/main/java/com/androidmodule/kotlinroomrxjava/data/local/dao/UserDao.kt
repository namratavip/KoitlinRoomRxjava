package com.androidmodule.kotlinroomrxjava.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.androidmodule.kotlinroomrxjava.data.local.entity.User

@Dao
interface UserDao {
    @Query("Select * FROM User")
    fun getUsers():List<User>

    @Insert
    fun insertAll(user: List<User>)
}