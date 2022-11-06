package com.baima.lingguibafa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_select_rizhu_shizhu.*

/**
 * 选择日柱时柱的活动
 *启动 这个活动的时候可以
 * intent.putExtra("rizhu", rizhu:String)
 * intent.putExtra("shizhu", shizhu:String)
 * 可以带可以不带
 *
 * 返回
 * intent.putExtra("rizhu", rizhu:String)
 * intent.putExtra("shizhu", shizhu:String)
 *setResult(Activity.RESULT_OK, intent)
 */
class SelectRizhuShizhuActivity : AppCompatActivity() {
    private val gans = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")
    private val zhis = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")

    val EXTRA_RIZHU = "rizhu"
    val EXTRA_SHIZHU = "shizhu"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_rizhu_shizhu)

        val context: Context = this
        //六十甲子表
        val sixtyJiazhiList = mutableListOf<String>()
        for (i in 0..59) {
            sixtyJiazhiList.add("${gans[i % 10]}${zhis[i % 12]}")
        }

        val rizhuAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, sixtyJiazhiList)
        rizhuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_rizhu.adapter = rizhuAdapter

        var rizhu = intent.getStringExtra(EXTRA_RIZHU)
        if (rizhu != null) {
            spinner_rizhu.setSelection(sixtyJiazhiList.indexOf(rizhu))
        }

        spinner_rizhu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                rizhu = sixtyJiazhiList.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        val shizhuAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, sixtyJiazhiList)
        shizhuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_shizhu.adapter = shizhuAdapter

        var shizhu = intent.getStringExtra(EXTRA_SHIZHU)
        if (shizhu != null) {
            spinner_shizhu.setSelection(sixtyJiazhiList.indexOf(shizhu))
        }

        spinner_shizhu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                shizhu = sixtyJiazhiList.get(position)
            }
        }

        bt_cancel.setOnClickListener { finish() }
        bt_ok.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_RIZHU, rizhu)
            intent.putExtra(EXTRA_SHIZHU, shizhu)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
