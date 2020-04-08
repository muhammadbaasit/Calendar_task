package com.example.calenderac

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calenderapp.CaldendarRepo
import com.example.calenderapp.CalendarDb
import com.example.calenderapp.QModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DViewModel : ViewModel(){

    val clkDatebtn = MutableLiveData<Boolean>().apply { value = false }
    val fltbtn = MutableLiveData<Boolean>().apply { value = false }
    var allEvents : LiveData<QModel>? = null
    var repository:CaldendarRepo ?= null


    fun insertEvents(event:QModel) = viewModelScope.launch {
        repository?.insertEvent(event)
    }

    fun getEvents(context: Context,qid:Int){
        val calDao = CalendarDb.getInstance(context).calendarDao()
        repository = CaldendarRepo(calDao,qid)
        allEvents = repository?.allQuiz
    }

    fun onDateSelect(view: View){
        clkDatebtn.value = true
    }

    fun floatingBtnClick(view:View){
        fltbtn.value = true
    }

    //---------------------------------------------------------------------------------

//    fun displayDailyEvents(context: Context) {
//
//        var dailyEvent =  ArrayList<QModel>()
//
//        val sDate1 = "20:00"
//        val eDate1 = "11:00"
//        val date1s = SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(sDate1)
//        val date1e= SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(eDate1)
//
//        val sDate2 = "20:00"
//        val eDate2 = "11:00"
//        val date2s: Date = SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(sDate2)
//        val date2e: Date = SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(eDate2)
//        Log.d("DATE",""+date1s+""+date1e)
//
//         dailyEvent?.add(QModel(1,"this data",date1s,date1e))
//         dailyEvent?.add(QModel(2,"this data",date2s,date2e))
//
//         for (items in dailyEvent!!) {
//            val eventDate: Date = items.date!!
//            val endDate: Date = items.end!!
//            val eventMessage: String = items.message
//            val eventBlockHeight = getEventTimeFrame(eventDate, endDate)
//            Log.d("DATE","eventBlockHeight>>"+eventBlockHeight)
//        Log.d(FragmentActivity.TAG, "Height $eventBlockHeight")
//          displayEventSection(context,eventDate, eventBlockHeight, eventMessage)
//    }
//}
//
//    fun getEventTimeFrame(start: Date, end: Date): Int {
//
//       var cal = Calendar.getInstance()
//
//        val timeDifference = end.time - start.time
//      cal.timeInMillis = timeDifference
//        Log.d("DATE","cal.timeInMillis>>"+cal.timeInMillis)
//    Log.d("DATE","cal.timeInMillis>>"+timeDifference)
//    val hours = cal[Calendar.HOUR]
//    Log.d("DATE","hours"+hours)
//    val minutes = cal[Calendar.MINUTE]
//    Log.d("DATE","minutes"+minutes)
//    Log.d("DATE","return"+hours * 60 + minutes * 60 / 100)
//    return hours * 60 + minutes * 60 / 100
//}
//
//fun displayEventSection(context: Context,eventDate: Date, height: Int, message: String) {
//
//    val timeFormatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
//    val displayValue = timeFormatter.format(eventDate)
//
//    val hourMinutes = displayValue.split(":").toTypedArray()
//
//    val hours = hourMinutes[0].toInt()
//    Log.d("DATE","hours"+hours)
//
//    val minutes = hourMinutes[1].toInt()
//    Log.d("DATE","hourMinutes"+minutes)
//
//    val topViewMargin = hours * 60 + minutes * 60 / 100
//    createEventView(context,topViewMargin, height, message)
//}
//
//fun createEventView(context: Context,topMargin: Int, height: Int, message: String) {
//
//    val mEventView = TextView(context)
//
//    val lParam = ConstraintLayout.LayoutParams(
//        ConstraintLayout.LayoutParams.MATCH_PARENT,
//        ConstraintLayout.LayoutParams.WRAP_CONTENT )
//
//    lParam.rightToRight = vericalLine
//    lParam.leftMargin = 10
//
//}

}