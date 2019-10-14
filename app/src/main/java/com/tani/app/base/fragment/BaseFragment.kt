package com.tani.app.base.fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tani.app.helper.DialogLoading
import org.jetbrains.anko.support.v4.act

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
        onCreate(savedInstanceState, act.actionBar)
        initComponent()
        onClick()
    }

    private fun initComponent() {
        context?.apply { baseLoading = DialogLoading(this) }
    }

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?)

    protected abstract fun onClick()
}