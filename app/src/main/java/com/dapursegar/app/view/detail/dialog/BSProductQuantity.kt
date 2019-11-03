package com.dapursegar.app.view.detail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.dialog.WeightAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_product_quantity.*

class BSProductQuantity(
    private val data: MutableList<String>,
    private val listener: (String) -> Unit
) : BottomSheetDialogFragment() {

    private var weightAdapter: WeightAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.dialog_product_quantity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        onClick()
        setupAdapter()
    }

    private fun onClick() {
        btnClose.setOnClickListener { dismiss() }
    }

    private fun setupAdapter() {
        weightAdapter = WeightAdapter {
            listener(it)
        }.apply { setData(data) }
        rvProductOrder.apply {
            adapter = weightAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

}