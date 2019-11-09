package com.dapursegar.app.view.main.profile.edit

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.gone
import kotlinx.android.synthetic.main.toolbar_default.*

/**
 * Created by Ramana on 15-Oct-19.
 */

class EditProfileActivity : BaseActivity<EditProfileViewModel>() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Edit Profile"

    override fun setLayout(): Int = R.layout.edit_profile_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        initComponents()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {

    }

    private fun initComponents() {
        tvTitle.gone()
    }
}