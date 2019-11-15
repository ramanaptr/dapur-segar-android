package com.dapursegar.app.view.main.bill

import android.app.ActionBar
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.fragment.BaseFragment
import com.dapursegar.app.model.home.ProductItem
import com.dapursegar.app.view.cart.CartActivity
import com.dapursegar.app.view.detail.DetailProductActivity
import com.dapursegar.app.view.main.bill.adapter.BillAdapter
import com.dapursegar.app.view.payment.PaymentDetailActivity
import kotlinx.android.synthetic.main.fragment_bill.*
import kotlinx.android.synthetic.main.toolbar_bill.*
import org.jetbrains.anko.support.v4.startActivity

class BillFragment : BaseFragment<BillViewModel>() {

    private var billAdapter: BillAdapter? = null

    override fun setLayout(): Int = R.layout.fragment_bill

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        tvTitle.text = getString(R.string.bill_title)
        setupAdapter()
        loadDummyData()
    }

    override fun onClick() {

    }

    private fun setupAdapter() {
        billAdapter = BillAdapter { onClickBill(this, it) }
        rvBill.apply {
            adapter = billAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
        }
    }

    private fun onClickBill(data: ProductItem, state: String) {
        data.apply {
            when (state) {
                BillAdapter.DETAIL -> {
                    startActivity<DetailProductActivity>()
                }
                BillAdapter.PAY -> {
                    startActivity<PaymentDetailActivity>()
                }
            }
        }
    }

    private fun loadDummyData() {
        val dummy = arrayListOf<ProductItem>()
        for (i in 1..6) {
            dummy.add(ProductItem())
        }
        billAdapter?.setData(dummy)
    }

}
