package com.androidmodule.kotlinroomrxjava.data.local

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var INSTANCE :AppDatabase?= null

    fun getInstance(context: Context):AppDatabase{
        if(INSTANCE == null){
            synchronized(AppDatabase::class){
               INSTANCE = buildRoomDb(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDb(context: Context) =
        Room.databaseBuilder(context,AppDatabase::class.java,
            "com.androidmodule.kotlinroomrxjava").allowMainThreadQueries().build()
}