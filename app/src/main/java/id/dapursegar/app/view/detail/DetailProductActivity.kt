package id.dapursegar.app.view.detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import id.dapursegar.app.R
import id.dapursegar.app.base.activity.BaseActivity

/**
 * Created by Ramana on 20-Oct-19.
 */

class DetailProductActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Dummy Apel Malang"

    override fun setLayout(): Int = R.layout.detail_product_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponent()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {

    }

    private fun initComponent() {
    }
}