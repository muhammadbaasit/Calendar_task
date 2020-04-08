package com.example.calenderapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalendarDao {

    @Query("select * from QModel where id=:qId")
    fun getEvent(qId: Int) : LiveData<QModel>

    @Insert
    suspend fun insertEvent(quiz:QModel)

}