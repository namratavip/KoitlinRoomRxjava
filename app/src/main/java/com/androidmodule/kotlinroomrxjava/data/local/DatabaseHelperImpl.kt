package com.androidmodule.kotlinroomrxjava.data.local

import com.androidmodule.kotlinroomrxjava.data.local.entity.User

class DatabaseHelperImpl(private val appDatabase: AppDatabase):DbHelper {
    override fun getUsers(): List<User> = appDatabase.userDao().getUsers()

    override fun insertAll(user: List<User>) = appDatabase.userDao().insertAll(user)
}