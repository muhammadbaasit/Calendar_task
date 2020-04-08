package com.example.calenderapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[(QModel::class)],version = 1)
abstract class CalendarDb:RoomDatabase(){

    abstract fun calendarDao() : CalendarDao

    companion object{

        @Volatile
        private var INSTANCE : CalendarDb? = null

        fun getInstance(context: Context) : CalendarDb{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(CalendarDb::class){
                val instance = Room.databaseBuilder(context.applicationContext,
                    CalendarDb::class.java,"quizdb").build()

                INSTANCE = instance

                return instance
            }
        }
    }
}