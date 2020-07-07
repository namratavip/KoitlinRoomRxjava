package com.androidmodule.kotlinroomrxjava.data.local

import com.androidmodule.kotlinroomrxjava.data.local.entity.User

interface DbHelper {
    fun getUsers():List<User>
    fun insertAll(user: List<User>)
}