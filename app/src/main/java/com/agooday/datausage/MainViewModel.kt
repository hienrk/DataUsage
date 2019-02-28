package com.agooday.datausage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.agooday.datausage.util.AppBundle
import com.agooday.datausage.util.SingleLiveEvent

class MainViewModel(private val context:Application) : AndroidViewModel(context){
    val showBackButtonEvent = SingleLiveEvent<Boolean>()
    val backEvent = SingleLiveEvent<Void>()
    val showFragmentEvent = SingleLiveEvent<AppBundle>()
    val showTitleEvent = SingleLiveEvent<String>()
    val restartEvent = SingleLiveEvent<Void>()
    val showActionBarEvent= SingleLiveEvent<Boolean>()
    val showToastEvent= SingleLiveEvent<String>()
    val reviewEvent= SingleLiveEvent<Void>()
    val upgradeEvent= SingleLiveEvent<String>()
    val finishAppEvent= SingleLiveEvent<Void>()
    val showFullScreenEvent= SingleLiveEvent<Boolean>()
}