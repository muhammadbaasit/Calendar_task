package com.example.calenderapp

import androidx.lifecycle.LiveData

class CaldendarRepo(val calDao: CalendarDao, qid:Int){

    val allQuiz : LiveData<QModel> = calDao.getEvent(qid)

    suspend fun insertEvent(event:QModel){
        calDao.insertEvent(event)
    }
}