package com.dapursegar.app.view.detail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.dialog.WeightAdapter
import com.dapursegar.app.base.extension.disable
import com.dapursegar.app.base.extension.enable
import com.dapursegar.app.base.extension.gone
import com.dapursegar.app.base.extension.show
import com.dapursegar.app.view.detail.DetailProductActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.default_add_quantity.*
import kotlinx.android.synthetic.main.dialog_product_quantity.*
import org.jetbrains.anko.support.v4.startActivity

class BSProductQuantity(
    private val data: MutableList<String>,
    private val listener: (String) -> Unit
) : BottomSheetDialogFragment() {

    private var weightAdapter: WeightAdapter? = null
    private var quantity = 1

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
        viewState(false)
        btnClose.setOnClickListener { dismiss() }
        btnAddCart.setOnClickListener { dismiss() }
        tvMinus.setOnClickListener {
            quantity--
            etQuantity.setText(quantity.toString())
            tvWeightPrice.text = "IDR 420.100/ 1 Kg (${quantity}x)"
        }
        tvPlus.setOnClickListener {
            quantity++
            etQuantity.setText(quantity.toString())
            tvWeightPrice.text = "IDR 420.100/ 1 Kg (${quantity}x)"
        }
        ivProduct.setOnClickListener { startActivity<DetailProductActivity>() }
    }

    private fun setupAdapter() {
        weightAdapter = WeightAdapter {
            viewState(true)
        }.apply { setData(data) }
        rvChoosePackage.apply {
            adapter = weightAdapter
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun viewState(isActive: Boolean) {
        if (isActive) {
            tvWeightPrice.show()
            tvTotalPrice.show()
            etQuantity.enable()
            tvMinus.enable()
            tvPlus.enable()
            btnAddCart.enable()
            tvPlus.setBackgroundResource(R.drawable.rounded_right_green)
            btnAddCart.setBackgroundResource(R.drawable.square_green_light_solid)
        } else {
            tvWeightPrice.gone()
            tvTotalPrice.gone()
            etQuantity.disable()
            tvMinus.disable()
            tvPlus.disable()
            btnAddCart.disable()
            btnAddCart.setBackgroundResource(R.drawable.square_grey_solid)
        }
    }

}
