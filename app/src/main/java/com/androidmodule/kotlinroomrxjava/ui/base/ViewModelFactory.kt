package com.androidmodule.kotlinroomrxjava.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidmodule.kotlinroomrxjava.data.api.ApiHelper
import com.androidmodule.kotlinroomrxjava.data.local.DbHelper
import com.androidmodule.kotlinroomrxjava.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper,private val dbHelper: DbHelper):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(apiHelper,dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}