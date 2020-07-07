package com.androidmodule.kotlinroomrxjava.data.api

import com.androidmodule.kotlinroomrxjava.data.api.model.ApiUser
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUser(): Single<List<ApiUser>>

}