package com.tani.app.helper

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import com.tani.app.R

class DialogLoading(act: Activity) : Dialog(act) {

    override fun show() {
        super.show()
        setContentView(R.layout.dialog_loading)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setWindowAnimations(R.style.Dialog_Animation_Scale)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }
}