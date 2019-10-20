package id.dapursegar.app.base.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BaseMainPagerAdapter(
    fm: FragmentManager,
    private val fragments: SparseArray<Fragment>
) : FragmentPagerAdapter(fm, fragments.size()) {

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size()
    }
}
