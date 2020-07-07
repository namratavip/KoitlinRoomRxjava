package com.androidmodule.kotlinroomrxjava.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidmodule.kotlinroomrxjava.data.api.ApiHelper
import com.androidmodule.kotlinroomrxjava.data.local.DbHelper
import com.androidmodule.kotlinroomrxjava.data.local.entity.User
import com.androidmodule.kotlinroomrxjava.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val apiHelper: ApiHelper, private val dbHelper: DbHelper) :
    ViewModel() {

    private val users = MutableLiveData<Resource<List<User>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        var usersToInsertInDB = mutableListOf<User>()
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            apiHelper.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (dbHelper.getUsers().isEmpty()) {
                        for (apiUser in it) {
                            val user = User(
                                apiUser.id,
                                apiUser.name,
                                apiUser.email,
                                apiUser.avatar
                            )
                            usersToInsertInDB.add(user)
                        }
                        dbHelper.insertAll(usersToInsertInDB)
                    } else {
                        val usersData = dbHelper.getUsers()
                        usersToInsertInDB.addAll(usersData)
                    }
                    users.value = Resource.success(usersToInsertInDB)

                }, { throwable -> users.value = (Resource.error(null, throwable.toString())) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    fun getUsers(): MutableLiveData<Resource<List<User>>> {
        return users
    }


}