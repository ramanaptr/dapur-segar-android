package com.dapursegar.app.view.main.bill

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.fragment.BaseFragment
import com.dapursegar.app.view.main.bill.adapter.BillAdapter
import kotlinx.android.synthetic.main.bill_fragment.*
import kotlinx.android.synthetic.main.toolbar_bill.*

class BillFragment : BaseFragment<BillViewModel>() {

    private var billAdapter: BillAdapter? = null

    override fun setLayout(): Int = R.layout.bill_fragment

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        tvTitle.text = getString(R.string.bill_title)
        setupAdapter()
    }

    private fun setupAdapter() {
        billAdapter = BillAdapter {}
        rvBill.apply {
            adapter = billAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
        }
    }

    override fun onClick() {

    }

}
