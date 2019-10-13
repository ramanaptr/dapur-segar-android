package com.tani.app.view.search

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.tani.app.R
import com.tani.app.base.activity.BaseActivity

class SearchActivity : BaseActivity() {

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Search"

    override fun setLayout(): Int = R.layout.search_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {

    }

    override fun onClick() {

    }

}
