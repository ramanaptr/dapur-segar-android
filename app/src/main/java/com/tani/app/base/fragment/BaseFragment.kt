package com.tani.app.base.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tani.app.helper.DialogLoading

abstract class BaseFragment : Fragment() {

    var baseLoading: DialogLoading? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreate(savedInstanceState, view)
        initComponent()
    }

    private fun initComponent() {
        baseLoading = DialogLoading(activity as Activity)
    }

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, view: View)
}