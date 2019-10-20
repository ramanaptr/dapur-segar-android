package id.dapursegar.app.view.payment

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import id.dapursegar.app.R
import id.dapursegar.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */
 
class PaymentDetailActivity : BaseActivity(){

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = getString(R.string.payment_detail)

    override fun setLayout(): Int = R.layout.payment_detail

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onClick() {

    }

    private fun initComponent() {
    }
}