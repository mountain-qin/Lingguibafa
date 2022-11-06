package com.baima.lingguibafa.util

import java.util.*

object TimeUtil {
    private val gans = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")
    private val zhis = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
    //六十甲子列表
    private val sixtyJiaziList = mutableListOf<String>()

    init {
        //创建六十甲子表
        //包括59
        for (i in 0..59 step 1) {
            val gz = "${gans[i % 10]}${zhis[i % 12]}"
            sixtyJiaziList.add(gz)
        }
    }

    /**
     *获取指定时间的日柱
     *@param millis 指定的时间的时间戳
     * @return
     */
    fun getRizhu(millis: Long): String {
//        1949年19月1日：甲子日。
        val calendar = Calendar.getInstance()
        //月份从0算起
        //日柱从前一天23点算起
        calendar.set(1949, 8, 30, 23, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val timeInMillis = calendar.timeInMillis
        var index = 0L
        if (millis >= timeInMillis) {
            index = (millis - calendar.timeInMillis) / (1000 * 60 * 60 * 24) % 60
        } else {
            //需要向下取整，向下取整
            index = (Math.floor((millis - calendar.timeInMillis) / (1000 * 60 * 60 * 24.0)) % 60.0).toLong()
            index += 60
        }
        return sixtyJiaziList.get(index.toInt())
    }

    /**
     * 获取指定时间的时柱
     */
    fun getShizhu(millis: Long): String {
        //1949年9月30日23点，1949年10月1日0点：甲子时，
        val calendar = Calendar.getInstance()
        //月份从0开始
        //时柱从前一天23点开始
        calendar.set(1949, 8, 30, 23, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        var index = 0L
        if (millis >= calendar.timeInMillis) {
            index = (millis - calendar.timeInMillis) / (1000 * 60 * 60 * 2) % 60
        } else {
            //需要向下取整，向下取整
            index = (Math.floor((millis - calendar.timeInMillis) / (1000 * 60 * 60 * 2.0)) % 60.0).toLong()
            index += 60
        }
        return sixtyJiaziList.get(index.toInt())
    }
}