package com.example.calenderapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class QModel(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "quizNumber")
    var message:String="",

    @ColumnInfo(name = "question")
    var date:String="",

    @ColumnInfo(name = "option1")
    var end:String=""
)