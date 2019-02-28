package com.agooday.datausage.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.agooday.datausage.MainViewModel
import com.agooday.datausage.R
import com.agooday.datausage.util.Constant
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var compositeDisposable: CompositeDisposable

    abstract fun getLayoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
        this.tag?.let {
            mainViewModel.showTitleEvent.value = createNewFragmentForTag(it)
            mainViewModel.showBackButtonEvent.value = !isRoot(it)
        }
    }

    private fun createNewFragmentForTag(tag: String): String? {
        when (tag) {//update title?
            Constant.HOME_TAG -> return getString(R.string.app_name)
            Constant.ABOUT_TAG -> return getString(R.string.about)
        }
        return null
    }

    private fun isRoot(tag: String): Boolean {
        return tag.contains("ROOT")
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()

    }
}