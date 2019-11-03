package com.dapursegar.app.base.fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.dapursegar.app.base.dialog.BaseLoading
import org.jetbrains.anko.support.v4.act
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<T: ViewModel> : Fragment() {

    val viewModel: T by lazy { getViewModel(viewModelClass()) }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<T> {
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<T>)
            .kotlin
    }

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
        onClick()
    }

    protected abstract fun setLayout(): Int

    protected abstract fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?)

    protected abstract fun onClick()
}