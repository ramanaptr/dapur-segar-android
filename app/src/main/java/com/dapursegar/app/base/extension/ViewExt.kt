package com.dapursegar.app.base.extension

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.dapursegar.app.R
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.toast
import java.util.*

private var showPassword = false
private var sayBackPress: Long = 0

fun show(vararg views: View): List<View> {
    val viewList = ArrayList<View>(views.toList())
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.VISIBLE
        }
    }
    return viewList
}

fun hide(vararg views: View): List<View> {
    val viewList = ArrayList<View>(views.toList())
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.INVISIBLE
        }
    }
    return viewList
}

fun gone(vararg views: View): List<View> {
    val viewList = ArrayList<View>(views.toList())
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.GONE
        }
    }
    return viewList
}

fun View.show() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun handleDoubleClick(vararg views: View): List<View> {
    val viewList = ArrayList<View>(views.toList())
    if (views.isNotEmpty()) {
        for (view in views) {
            view.isClickable = true
            view.isEnabled = false
            Handler().postDelayed({ view.isEnabled = true }, 1000)
        }
    }
    return viewList
}

fun AppCompatActivity.settingToolbar(
    tvTitle: TextView?,
    title: String,
    toolbar: Toolbar?
): ActionBar? {
    if (toolbar != null) {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            if (tvTitle != null) {
                setDisplayShowTitleEnabled(false)
                tvTitle.text = title
            } else {
                setDisplayShowTitleEnabled(true)
                toolbar.title = title
            }
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            return this
        }
    }
    return null
}


fun setFonts(activity: Activity, aFontName: String, vararg views: View) {
    for (view in views)
        setFont(activity, view, aFontName)
}

fun setFont(activity: Activity, view: View?, aFontName: String) {
    if (view != null) {
        val typeFace = Typeface.createFromAsset(activity.assets, aFontName)
        (view as TextView).typeface = typeFace
    }
}

fun findTogglePasswordButton(viewGroup: ViewGroup): View? {
    val childCount = viewGroup.childCount
    for (ind in 0 until childCount) {
        val child = viewGroup.getChildAt(ind)
        if (child is ViewGroup) {
            return findTogglePasswordButton(child)
        } else if (child is CheckableImageButton) {
            return child
        }
    }
    return null
}

fun View.passwordState(target: EditText) {
    when (this) {
        is ImageView -> if (!showPassword) {
            showPassword = true
            target.transformationMethod = null
            target.setSelection(target.text.toString().length)
            (this as ImageButton).setImageResource(R.drawable.ic_eye_off)
        } else {
            showPassword = false
            target.transformationMethod = PasswordTransformationMethod()
            target.setSelection(target.text.toString().length)
            (this as ImageButton).setImageResource(R.drawable.ic_eye)
        }
    }
}

fun AppCompatActivity.handleBackFromMain(keyCode: Int, viewPager: ViewPager): Boolean {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        if (viewPager.currentItem == 0) {
            if (sayBackPress + 1500 > System.currentTimeMillis()) {
                finish()
            } else {
                toast(getString(R.string.pressed_back_message))
                sayBackPress = System.currentTimeMillis()
            }
        } else {
            viewPager.currentItem = 0
        }
        return true
    }
    return false
}

private fun getIconsFromResource(context: Context, active: Boolean): TypedArray {
    return if (active) {
        context.resources.obtainTypedArray(R.array.ic_main_tab)
    } else {
        context.resources.obtainTypedArray(R.array.ic_main_tab_off)
    }
}

fun TabLayout.setupTablayout(vpPager: ViewPager, listener: (Int) -> Unit) {
    for (i in 0 until tabCount) {
        val tab = getTabAt(i)
        tab?.setIcon(getIconsFromResource(context, false).getResourceId(i, -1))
    }
    setupTablayoutListener(this, vpPager, listener)
}

private fun setupTablayoutListener(
    tabLayout: TabLayout,
    vpPager: ViewPager,
    listener: (Int) -> Unit
) {
    vpPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    tabLayout.addOnTabSelectedListener(
        object : TabLayout.ViewPagerOnTabSelectedListener(vpPager) {
            override fun onTabSelected(tab: TabLayout.Tab) {
                super.onTabSelected(tab)
                listener(tab.position)
                tab.setIcon(
                    getIconsFromResource(tab.parent.context, true).getResourceId(
                        tab.position, -1
                    )
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                super.onTabUnselected(tab)
                tab?.setIcon(
                    getIconsFromResource(tab.parent.context, false).getResourceId(
                        tab.position, -1
                    )
                )
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                super.onTabReselected(tab)
                tab?.apply { listener(position) }
            }
        }
    )
//    vpPager.currentItem = 1
//    vpPager.currentItem = 0
}

fun AppCompatTextView.colorText(color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}