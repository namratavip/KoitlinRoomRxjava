package com.androidmodule.kotlinroomrxjava.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidmodule.kotlinroomrxjava.R
import com.androidmodule.kotlinroomrxjava.data.api.ApiServiceImpl
import com.androidmodule.kotlinroomrxjava.data.api.RetrofitBuilder
import com.androidmodule.kotlinroomrxjava.data.local.DatabaseBuilder
import com.androidmodule.kotlinroomrxjava.data.local.DatabaseHelperImpl
import com.androidmodule.kotlinroomrxjava.data.local.entity.User
import com.androidmodule.kotlinroomrxjava.ui.base.ViewModelFactory
import com.androidmodule.kotlinroomrxjava.ui.main.adapter.MainAdapter
import com.androidmodule.kotlinroomrxjava.ui.main.viewmodel.MainViewModel
import com.androidmodule.kotlinroomrxjava.util.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        setViewModel()
        setObserver()
    }

    private fun setObserver() {
            mainViewModel.getUsers().observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        it.data?.let { users -> renderList(users) }
                        recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        //Handle Error
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun renderList(users: List<User>) {
        mainAdapter.addList(users)
        mainAdapter.notifyDataSetChanged()
    }

    private fun setViewModel() {
        mainViewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiServiceImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)))).get(MainViewModel::class.java)
    }

    private fun initUi() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(arrayListOf())
        recyclerView.adapter = mainAdapter
    }
}