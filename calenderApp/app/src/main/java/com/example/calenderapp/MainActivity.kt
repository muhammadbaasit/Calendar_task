package com.example.calenderapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calenderac.DViewModel
import com.example.calenderapp.databinding.ActivityMainBinding
import com.example.calenderapp.databinding.DialogLayoutBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rc_layout.*
import kotlinx.android.synthetic.main.rc_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding?=null
    var mViewModel:DViewModel?=null
    var listBinding:DialogLayoutBinding?=null
    var rvAdapter: RcAdapter? = null


    var cal = Calendar.getInstance()
    var usrSelectdate:String = ""
    var userEventStartDate:String = ""
    var userEventEndDate:String = ""
    var userEventStartTime:String = ""
    var userEventEndTime:String=""
    var calendarDate: Date ? = null
    val MONTHS = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    var ll = ArrayList<Modell>()
    var mLayout: ConstraintLayout? = null
    var eventIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel= ViewModelProvider(this@MainActivity).get(DViewModel::class.java)

        var  mo=cal.get(Calendar.MONTH)
        var dt = cal.get(Calendar.DAY_OF_YEAR)
        textView.text = MONTHS.get(mo) + "  "+dt

        ll.add(Modell("1 am"))
        ll.add(Modell("2 am"))
        ll.add(Modell("3 am"))
        ll.add(Modell("4 am"))
        ll.add(Modell("5 am"))
        ll.add(Modell("6 am"))
        ll.add(Modell("7 am"))
        ll.add(Modell("8 am"))
        ll.add(Modell("9 am"))
        ll.add(Modell("10 am"))
        ll.add(Modell("11 am"))
        ll.add(Modell("12 pm"))
        ll.add(Modell("13 pm"))
        ll.add(Modell("14 pm"))
        ll.add(Modell("15 pm"))
        ll.add(Modell("16 pm"))
        ll.add(Modell("17 pm"))
        ll.add(Modell("18 pm"))
        ll.add(Modell("19 pm"))
        ll.add(Modell("20 pm"))
        ll.add(Modell("21 pm"))
        ll.add(Modell("22 pm"))
        ll.add(Modell("23 pm"))
        ll.add(Modell("24 pm"))

        rvAdapter = RcAdapter(this@MainActivity, ll, DViewModel())
        binding!!.rv.layoutManager = LinearLayoutManager(this@MainActivity)
        binding!!.rv.adapter = rvAdapter

        //mLayout = findViewById(R.id.left_event_column);
        //eventIndex = mLayout!!.getChildCount();


        mViewModel?.apply {
            binding?.apply {
                viewModel = mViewModel
            }

            clkDatebtn.observe(this@MainActivity,androidx.lifecycle.Observer {

                if(it == true){

                    val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, monthOfYear)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        val myFormat = "dd.MM.yyyy" // mention the format you need
                        val sdf = SimpleDateFormat(myFormat, Locale.US)
                        //textView.text = sdf.format(cal.time)
                        usrSelectdate = sdf.format(cal.time)
                        var  mo=monthOfYear
                        var dt = dayOfMonth
                        textView.text = MONTHS.get(mo) + "  "+dt
                    }
                    DatePickerDialog(this@MainActivity, dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
            })

            fltbtn.observe(this@MainActivity,androidx.lifecycle.Observer {

                if (it == true){

                    val dialog= Dialog(this@MainActivity)
                    listBinding = DialogLayoutBinding.inflate(LayoutInflater.from(this@MainActivity))

                    listBinding?.viewModel = mViewModel

                    val dateFormat = "d-MM-yyyy"
                    val timeFormat = "HH:mm"
                    val dt = SimpleDateFormat(dateFormat, Locale.ENGLISH)
                    val tm = SimpleDateFormat(timeFormat, Locale.ENGLISH)

                    var stDate=dt.format(cal.time)
                    var tmTime = tm.format(cal.time)

                    listBinding!!.tvStartTime.text = tmTime
                    listBinding!!.tvEndTime.text = tmTime
                    listBinding!!.tvStartDate.text = stDate
                    listBinding!!.tvEndDate.text = stDate

                    listBinding?.tvStartDate?.setOnClickListener(View.OnClickListener {
                        dialog.hide()
                        datePickerDialog(dialog,true)

                    })

                    listBinding?.tvEndDate?.setOnClickListener(View.OnClickListener {
                        dialog.hide()
                        datePickerDialog(dialog,false)
                        //Log.d("DATE",""+userEventEndDate)
                    })

                    listBinding?.tvStartTime?.setOnClickListener(View.OnClickListener {
                        dialog.hide()
                        timePickerDialog(dialog,true)
                    })

                    listBinding?.tvEndTime?.setOnClickListener(View.OnClickListener {
                        dialog.hide()
                        timePickerDialog(dialog,false)
                    })

                    listBinding?.saveBtn?.setOnClickListener(View.OnClickListener {
                        Toast.makeText(this@MainActivity,"Clicked", Toast.LENGTH_LONG).show()



                    })


                    dialog.setContentView(listBinding?.root!!)
                    dialog.setCancelable(true)
                    dialog.show()
                    val win=dialog.window
                    win?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                }
            })
        }


    }

    fun datePickerDialog(dialog:Dialog,cnt:Boolean){

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "d-MM-yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

            userEventStartDate = sdf.format(cal.time)

            if(cnt == true){
               // userEventStartDate = sdf.format(cal.time)
                listBinding?.tvStartDate?.text = sdf.format(cal.time)
            }
            if(cnt == false){
                userEventEndDate = sdf.format(cal.time)
                listBinding?.tvEndDate?.text = sdf.format(cal.time)
            }
            dialog.show()
        }
        DatePickerDialog(this@MainActivity, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    fun timePickerDialog(dialog: Dialog,cnt: Boolean){

        val hour = cal.get(Calendar.HOUR)
        val minute = cal.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

            //Toast.makeText(this, h.toString() + " : " + m +" : " , Toast.LENGTH_LONG).show()
            if(cnt == true){
                calendarDate = cal.time
                Log.d("DATE",""+userEventEndDate)
                userEventStartTime = h.toString() + " : " + m
                listBinding?.tvStartTime?.text = h.toString() + " : " + m

            }
            if(cnt == false){
                userEventEndTime = h.toString() + " : " + m
                listBinding?.tvEndTime?.text = h.toString() + " : " + m
            }
            dialog.show()

        }),hour,minute,false)
        tpd.show()
    }

    fun createEventView(context: Context, topMargin: Int, height: Int, message: String) {

        val mEventView = TextView(context)

        val lParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT )

        lParam.topMargin = 50
        lParam.leftMargin = 10

        // mLayout.addView()

/*
        val mEventView = TextView(context)
        val lParam = RelativeLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lParam.topMargin = topMargin * 2
        lParam.leftMargin = 24
        mEventView.layoutParams = lParam
        mEventView.setPadding(24, 0, 24, 0)
        mEventView.height = height * 2
        mEventView.gravity = 0x11
        mEventView.setTextColor(Color.parseColor("#FF4081"))
        mEventView.text = message
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"))
        mLayout?.addView(mEventView, eventIndex - 1)*/
    }

}
