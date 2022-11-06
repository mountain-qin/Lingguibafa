package com.baima.lingguibafa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_select_time.*
import java.util.*

/**
 * 选择年月日时分的活动
 * 可以传入time_millis
 * 返回time_millis
 */
class SelectTimeActivity : AppCompatActivity() {
    private val EXTRA_TIME_MILLIS = "time_millis"
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_time)

        val context: Context = this
        //不传入EXTRA_TIME_MILLIS或者传入的数小于0，对应的下拉列表框显示当前对应时间
        var timeMillis = intent.getLongExtra(EXTRA_TIME_MILLIS, -1L)
        if (timeMillis < 0L) {
            timeMillis = System.currentTimeMillis()
        }
        calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis

        val year = calendar.get(Calendar.YEAR)
        val yearList = mutableListOf<String>()
        val count = 199
        for (i in 0..count) {
            yearList.add("${year + i - count / 2}年")
        }
        val yearAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, yearList)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_year.adapter = yearAdapter
        spinner_year.setSelection(count / 2)

        val monthList = mutableListOf<String>()
        for (i in 0..11) {
            monthList.add("${i + 1}月")
        }
        val monthAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, monthList)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_month.adapter = monthAdapter
        spinner_month.setSelection(calendar.get(Calendar.MONTH))

        val dayList = mutableListOf<String>()
        for (i in 0..30) {
            dayList.add("${i + 1}日")
        }
        val dayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, dayList)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_day.adapter = dayAdapter
        spinner_day.setSelection(calendar.get(Calendar.DAY_OF_MONTH) - 1)

        val hourList = mutableListOf<String>()
        for (i in 0..23) {
            hourList.add("${i}时")
        }
        val hourAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, hourList)
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_hour.adapter = hourAdapter
        spinner_hour.setSelection(calendar.get(Calendar.HOUR_OF_DAY))

        val minuteList = mutableListOf<String>()
        for (i in 0..59) {
            minuteList.add("${i}分")
        }
        val minuteAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, minuteList)
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_minute.adapter = minuteAdapter
        spinner_minute.setSelection(calendar.get(Calendar.MINUTE))

        val l = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent) {
                    spinner_year -> calendar.set(Calendar.YEAR, year - count / 2 + position)
                    spinner_month -> calendar.set(Calendar.MONTH, position)
                    spinner_day -> calendar.set(Calendar.DAY_OF_MONTH, position + 1)
                    spinner_hour -> calendar.set(Calendar.HOUR_OF_DAY, position)
                    spinner_minute -> calendar.set(Calendar.MINUTE, position)
                }
            }
        }

        spinner_year.onItemSelectedListener = l
        spinner_month.onItemSelectedListener = l
        spinner_day.onItemSelectedListener = l
        spinner_hour.onItemSelectedListener = l
        spinner_minute.onItemSelectedListener = l

        bt_cancel.setOnClickListener { finish() }
        bt_ok.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_TIME_MILLIS, calendar.timeInMillis)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}
