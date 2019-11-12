package com.dapursegar.app.view.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.ActionBar
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.view.search.adapter.TagAdapter
import com.dapursegar.app.view.search.model.TagItem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchActivity : BaseActivity<SearchViewModel>() {

    private var tagAdapter: TagAdapter? = null

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Search"

    override fun setLayout(): Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        setupAdapter()
        action()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onClick() {

    }

    private fun setupAdapter() {
        val data = arrayListOf<TagItem>().apply {
            add(TagItem("Fruit"))
            add(TagItem("Vegetable"))
            add(TagItem("Durian"))
            add(TagItem("Fresh Fruit"))
            add(TagItem("Good Quality"))
            add(TagItem("Apple"))
            add(TagItem("For Me"))
        }
        tagAdapter = TagAdapter { toast(it.tagName) }.apply { setData(data) }
        rvTag.apply {
            val flm = FlexboxLayoutManager(baseContext, FlexDirection.ROW)
            layoutManager = flm
            adapter = tagAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun action() {
        etSearchField.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                startActivity<SearchResultActivity>()
                true
            } else {
                false
            }
        }
    }
}
