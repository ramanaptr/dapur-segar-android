package id.dapursegar.app.view.search

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import id.dapursegar.app.R
import id.dapursegar.app.base.activity.BaseActivity
import id.dapursegar.app.view.search.adapter.TagAdapter
import id.dapursegar.app.view.search.model.TagItem
import kotlinx.android.synthetic.main.search_activity.*
import org.jetbrains.anko.toast

class SearchActivity : BaseActivity() {

    private var tagAdapter: TagAdapter? = null

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Search"

    override fun setLayout(): Int = R.layout.search_activity

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        setupAdapter()
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
}
