package id.dapursegar.app.base.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BasePagerAdapter(
    fm: FragmentManager,
    private val fragments: SparseArray<Fragment>
) : FragmentPagerAdapter(fm, fragments.size()) {

    private lateinit var titles: SparseArray<String>

    fun addTitle(titles: SparseArray<String>) {
        this.titles = titles
    }

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titles.size() > 0) titles[position]
        else ""
    }
}
