package com.agooday.datausage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.agooday.datausage.feature.datausage.DataUsageFragment
import com.agooday.datausage.feature.settings.AboutFragment
import com.agooday.datausage.util.AppBundle
import com.agooday.datausage.util.Constant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configActionBar()
    }


    private fun configActionBar() {
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.action_bar_title)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }


    private fun showFragment(appBundle: AppBundle) {
        supportFragmentManager?.executePendingTransactions()
        val transaction = supportFragmentManager.beginTransaction()
        createNewFragmentForTag(appBundle.tag)?.let {
            if (!appBundle.tag.contains("ROOT")) {
                transaction.addToBackStack(null)
                transaction.setCustomAnimations(
                    R.anim.enter_from_left,
                    R.anim.exit_to_right,
                    R.anim.enter_from_right,
                    R.anim.exit_to_left
                )
            } else {
                supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            it.arguments = appBundle.bundle
            return@let transaction.replace(R.id.container, it, appBundle.tag).commitAllowingStateLoss()
        }
    }


    private fun createNewFragmentForTag(tag: String): Fragment? {
        when (tag) {
            Constant.HOME_TAG -> return DataUsageFragment()
            Constant.ABOUT_TAG -> return AboutFragment()
        }
        return null
    }
}
