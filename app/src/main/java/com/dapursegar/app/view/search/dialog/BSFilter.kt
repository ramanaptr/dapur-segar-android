package com.dapursegar.app.view.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.dapursegar.app.R
import com.dapursegar.app.view.search.adapter.FilterAdapter
import kotlinx.android.synthetic.main.dialog_filter.*

class BSFilter(
    private val filter: MutableList<String>,
    private val listener: (String) -> Unit
) : BottomSheetDialogFragment() {

    private var filterAdapter: FilterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.dialog_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        filterAdapter = FilterAdapter {
            listener(it)
            dismiss()
        }.apply { setData(filter) }
        rvFilter.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
        }
    }

}
