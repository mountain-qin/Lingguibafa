package com.baima.lingguibafa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.baima.lingguibafa.adapter.LgbfFragmentAdapter
import com.baima.lingguibafa.fragment.LgbfMainFragment
import kotlinx.android.synthetic.main.activity_lgbf_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class LgbfMainActivity : AppCompatActivity() {
    private val CONFIG = "config"
    private val FIRST_SHOW_DISCLAIMERS = "first_show_disclaimers"
    private lateinit var fragmentList: MutableList<Fragment>
    private lateinit var lgbfMainFragment: LgbfMainFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lgbf_main)

        initViews()
        firstShowDisclaimers()
    }

    /**
     * 第一次使用时弹出免责声明
     */
    private fun firstShowDisclaimers() {
        val sharedPreferences = getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
        val b = sharedPreferences.getBoolean(FIRST_SHOW_DISCLAIMERS, true)
        if (b) {
            showDisclaimers()
            val edit = sharedPreferences.edit()
            edit.putBoolean(FIRST_SHOW_DISCLAIMERS, false)
            edit.apply()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in fragmentList) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_lgbf_main_activity, menu)
        val versionName = packageManager.getPackageInfo(packageName, 0)
                .versionName
        menu!!.findItem(R.id.item_version)
                .setTitle("${getString(R.string.version)}: $versionName")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.item_help -> showHelp()
            R.id.item_refresh -> {
                toast(R.string.refresh_completed)
                lgbfMainFragment.showTimeAndData(System.currentTimeMillis())
            }
            R.id.item_disclaimers -> showDisclaimers()
        }
        return true
    }

    private fun showHelp() {
        val s = assets.open("help.txt").bufferedReader().use { it.readText() }
        alert(s, getString(R.string.help)) {
            negativeButton(R.string.ok)
        }.show()
    }

    private fun showDisclaimers() {
        alert(R.string.disclaimers_content, R.string.disclaimers) {
            negativeButton(R.string.ok)
        }.show()
    }

    private fun initViews() {
        fragmentList = mutableListOf()
        lgbfMainFragment = LgbfMainFragment()
        fragmentList.add(lgbfMainFragment)
        view_pager.adapter = LgbfFragmentAdapter(supportFragmentManager, fragmentList)
    }
}
