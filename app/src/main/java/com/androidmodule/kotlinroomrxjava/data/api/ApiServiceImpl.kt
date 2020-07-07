package com.androidmodule.kotlinroomrxjava.data.api

import com.androidmodule.kotlinroomrxjava.data.api.model.ApiUser
import io.reactivex.Single

class ApiServiceImpl(private val apiService: ApiService) : ApiHelper {
    override fun getUsers(): Single<List<ApiUser>> {
        return apiService.getUser()
    }
}