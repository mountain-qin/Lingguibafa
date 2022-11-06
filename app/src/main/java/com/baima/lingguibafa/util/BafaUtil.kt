package com.baima.lingguibafa.util

object BafaUtil {
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

    /**
     * 根据日干支时干支获取九宫八卦之数
     */
    private fun getNumber(riGan: String, riZhi: String, shiGan: String, shiZhi: String): Int {
        val i = riGanNumbers[gans.indexOf(riGan)] + riZhiNumbers[zhis.indexOf(riZhi)] +
                shiGanNumbers[gans.indexOf(shiGan)] + shiZhiNumbers[zhis.indexOf(shiZhi)]
        var number = -1;
        //阳日%9，阴日%6
        if (gans.indexOf(riGan) % 2 == 0 &&
                zhis.indexOf(riZhi) % 2 == 0) {
            number = if (i % 9 == 0) 9 else i % 9
        } else {
            number = if (i % 6 == 0) 6 else i % 6
        }
        return number
    }

    /**
     * 根据日干支时干支获取九宫八卦之数
     */
    fun getNumber(rizhu: String, shizhu: String): Int {
        return getNumber(rizhu[0].toString(), rizhu[1].toString(), shizhu[0].toString(), shizhu[1].toString())
    }

}