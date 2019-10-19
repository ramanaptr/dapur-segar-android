package com.tani.app.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tani.app.R
import kotlinx.android.synthetic.main.dialog_weight.*

class WeightDialog(context: Context, private val data: MutableList<String>) :
    Dialog(context) {

    private var weightAdapter: WeightAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_weight)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setWindowAnimations(R.style.Dialog_Animation_Scale)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setupAdapter()
        onClick()
    }

    private fun setupAdapter() {
        weightAdapter = WeightAdapter { dismiss() }.apply { setData(data) }
        rvProductWeight.apply {
            adapter = weightAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false

        }
    }

    private fun onClick() {
        ivClose.setOnClickListener { dismiss() }
    }
}

fun Fragment.showWeightDialog(data: MutableList<String>) {
    context?.apply {
        WeightDialog(this, data).show()
        return
    }
    throw IllegalArgumentException("Unable to init ${WeightDialog::class.java.name} on $this")
}

fun Context.showWeightDialog(data: MutableList<String>) {
    WeightDialog(this, data).show()
}