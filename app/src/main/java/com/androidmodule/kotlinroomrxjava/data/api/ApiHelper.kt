package com.androidmodule.kotlinroomrxjava.data.api

import com.androidmodule.kotlinroomrxjava.data.api.model.ApiUser
import io.reactivex.Single

interface ApiHelper {
    fun getUsers(): Single<List<ApiUser>>
}