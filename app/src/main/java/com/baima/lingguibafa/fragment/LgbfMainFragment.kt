package com.baima.lingguibafa.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.baima.lingguibafa.R
import com.baima.lingguibafa.SelectRizhuShizhuActivity
import com.baima.lingguibafa.SelectTimeActivity
import com.baima.lingguibafa.entity.Xuewei
import com.baima.lingguibafa.util.BafaUtil
import com.baima.lingguibafa.util.TimeUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_lgbf_main.view.*
import java.text.SimpleDateFormat

class LgbfMainFragment : Fragment() {
    private val EXTRA_TIME_MILLIS = "time_millis"
    val EXTRA_RIZHU = "rizhu"
    val EXTRA_SHIZHU = "shizhu"

    val REQUEST_CODE_SELECT_TIME = 1
    val REQUEST_CODE_SELECT_EIZHU_SHIZHU = 2

    /**当前显示的时间的时间戳*/
    private var timeMillis: Long = 0L
    private lateinit var rizhu: String
    private lateinit var shizhu: String

    private lateinit var tv_time: TextView
    private lateinit var tv_rizhu_shizhu: TextView
    private lateinit var tv_bagua: TextView
    private lateinit var tv_xuewei: TextView
    private lateinit var tv_location: TextView
    private lateinit var tv_effect: TextView
    private lateinit var tv_clinical: TextView
    private lateinit var xueweiList: List<Xuewei>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lgbf_main, container, false)
        tv_time = view.tv_time
        tv_rizhu_shizhu = view.tv_rizhu_shizhu
        tv_bagua = view.tv_bagua
        tv_xuewei = view.tv_xuewei
        tv_location = view.tv_location
        tv_effect = view.tv_effect
        tv_clinical = view.tv_clinical
        loadXueweis()
        showTimeAndData(System.currentTimeMillis())

        tv_time.setOnClickListener {
            val intent = Intent(activity!!, SelectTimeActivity::class.java)
            if (timeMillis >= 0) {
                //其实小于0时也可以put
                intent.putExtra(EXTRA_TIME_MILLIS, timeMillis)
            }
            activity!!.startActivityForResult(intent, REQUEST_CODE_SELECT_TIME)
        }

        tv_rizhu_shizhu.setOnClickListener {
            val intent = Intent(activity, SelectRizhuShizhuActivity::class.java)
            intent.putExtra(EXTRA_RIZHU, rizhu)
            intent.putExtra(EXTRA_SHIZHU, shizhu)
            startActivityForResult(intent, REQUEST_CODE_SELECT_EIZHU_SHIZHU)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //从别的界面返回时回调的是activity里的onActivityResult,可以在activity里调用fragment里的onActivityResult
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SELECT_TIME ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val timeMillis = data.getLongExtra(EXTRA_TIME_MILLIS, -1L)
                    if (timeMillis != -1L) {
                        showTimeAndData(timeMillis)
                    }
                }
            REQUEST_CODE_SELECT_EIZHU_SHIZHU ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val rizhu = data.getStringExtra(EXTRA_RIZHU)
                    val shizhu = data.getStringExtra(EXTRA_SHIZHU)
                    if (rizhu != null && shizhu != null) {
                        showData(rizhu, shizhu)
                        tv_time.text = getString(R.string.unknow_time)
                        this.timeMillis = -1L
                    }
                }
        }
    }

    private fun showData(rizhu: String, shizhu: String) {
        this.rizhu = rizhu
        this.shizhu = shizhu

        tv_rizhu_shizhu.text = "${rizhu}日${shizhu}时"
        val number = BafaUtil.getNumber(rizhu, shizhu)
        var xuewei: Xuewei? = null
        for (item in xueweiList) {
            if (item.number == number) {
                xuewei = item
                break
            }
        }
        tv_bagua.text = "${xuewei!!.bagua}卦 "
        tv_xuewei.text = "${xuewei!!.name} 配 ${xuewei.guest}"
        tv_location.text = "定位:${xuewei.location}"
        tv_effect.text = "功效:${xuewei.effect}"
        tv_clinical.text = "临床应用:${xuewei.clinical}"
    }


    private fun loadXueweis() {
        val jsonStr = activity!!.assets.open("xuewei.json").bufferedReader().use { it.readText() }
        xueweiList = Gson().fromJson(jsonStr, object : TypeToken<List<Xuewei>>() {}.type)
    }

    /**
     * 在控件上显示数据
     */
    fun showTimeAndData(timeMillis: Long) {
        this.timeMillis = timeMillis
        tv_time.text = SimpleDateFormat("yyyy年MM月dd日 HH时mm分").format(timeMillis)
        showData(TimeUtil.getRizhu(timeMillis), TimeUtil.getShizhu(timeMillis))
    }
}