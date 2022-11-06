package com.baima.lingguibafa.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class LgbfFragmentAdapter(var fm:FragmentManager ,var fragmentList: MutableList<Fragment>) :FragmentPagerAdapter(fm){
    override fun getCount(): Int {
return fragmentList.size
    }

    override fun getItem(p0: Int): Fragment {
      return fragmentList.get(p0)
    }
}