package id.dapursegar.app.view.main.profile

import android.app.ActionBar
import android.os.Bundle
import id.dapursegar.app.R
import id.dapursegar.app.base.fragment.BaseFragment
import id.dapursegar.app.view.main.profile.edit.EditProfileActivity
import kotlinx.android.synthetic.main.profile_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class ProfileFragment : BaseFragment() {

    override fun setLayout(): Int = R.layout.profile_fragment

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
    }

    override fun onClick() {
        tvEdit.setOnClickListener { startActivity<EditProfileActivity>() }
    }

}