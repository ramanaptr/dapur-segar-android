package id.dapursegar.app.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import id.dapursegar.app.R

class BaseLoading(context: Context) : Dialog(context) {

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