package com.baima.lingguibafa

import com.baima.lingguibafa.util.BafaUtil
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TimeUtiltTest {
    private val gans = arrayOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")
    private val zhis = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")
    /**日干基数*/
    private val riGanNumbers = arrayOf(10, 9, 7, 8, 7, 10, 9, 7, 8, 7)
    /**日地支基数*/
    private val riZhiNumbers = arrayOf(7, 10, 8, 8, 10, 7, 7, 10, 9, 9, 10, 7)

    /**时干基数*/
    private val shiGanNumbers = arrayOf(9, 8, 7, 6, 5, 9, 8, 7, 6, 5)
    /**时支基数*/
    private val shiZhiNumbers = arrayOf(9, 8, 7, 6, 5, 4, 9, 8, 7, 6, 5, 4)

    private val baguas = arrayOf("坎", "坤", "震", "巽", "坤", "乾", "兑", "艮", "离")
    private val xuewei = arrayOf("申脉", "照海", "外关", "临泣", "照海", "公孙", "后溪", "内关", "列缺")
    @Test
    fun test1() {

    }

}
