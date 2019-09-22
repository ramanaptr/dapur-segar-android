package com.tani.app.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.tabs.TabLayout;
import com.tani.app.R;

import java.util.ArrayList;
import java.util.List;

public class ViewUtils {

    public static List<View> show(View... views) {
        List<View> viewList = new ArrayList<>();
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.VISIBLE);
                viewList.add(view);
            }
        }
        return viewList;
    }

    public static List<View> hide(View... views) {
        List<View> viewList = new ArrayList<>();
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
                viewList.add(view);
            }
        }
        return viewList;
    }

    public static List<View> gone(View... views) {
        List<View> viewList = new ArrayList<>();
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.GONE);
                viewList.add(view);
            }
        }
        return viewList;
    }

    public static ActionBar initToolbar(
            TextView tvTitle,
            String title,
            Toolbar toolbar,
            ActionBar actionBar
    ) {
        if (toolbar != null && actionBar != null) {
            if (tvTitle != null) {
                actionBar.setDisplayShowTitleEnabled(false);
                tvTitle.setText(title);
            } else {
                actionBar.setDisplayShowTitleEnabled(true);
                toolbar.setTitle(title);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            return actionBar;
        }
        return null;
    }


    public static void setFonts(Activity activity, String aFontName, View... views) {
        for (View view : views)
            setFont(activity, view, aFontName);
    }

    public static void setFont(Activity activity, View view, String aFontName) {
        if (view != null) {
            Typeface typeFace = Typeface.createFromAsset(activity.getAssets(), aFontName);
            ((TextView) view).setTypeface(typeFace);
        }
    }

    public static View findTogglePasswordButton(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int ind = 0; ind < childCount; ind++) {
            View child = viewGroup.getChildAt(ind);
            if (child instanceof ViewGroup) {
                return findTogglePasswordButton((ViewGroup) child);
            } else if (child instanceof CheckableImageButton) {
                return child;
            }
        }
        return null;
    }

    private static boolean showPassword = false;

    public static void passwordState(View btnEye, EditText etPassword) {
        if (btnEye instanceof ImageView) {
            if (!showPassword) {
                showPassword = true;
                etPassword.setTransformationMethod(null);
                ((ImageButton) btnEye).setImageResource(R.drawable.ic_eye_off);
            } else {
                showPassword = false;
                etPassword.setTransformationMethod(new PasswordTransformationMethod());
                ((ImageButton) btnEye).setImageResource(R.drawable.ic_eye);
            }
        }
    }


    private static long sayBackPress = 0;

    public static boolean handleBackFromMain(Activity activity, int keyCode, ViewPager viewPager) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (viewPager.getCurrentItem() == 0) {
                if (sayBackPress + 1500 > System.currentTimeMillis()) {
                    activity.finish();
                } else {
                    Toast.makeText(activity, activity.getString(R.string.pressed_back_message), Toast.LENGTH_SHORT).show();
                    sayBackPress = System.currentTimeMillis();
                }
            } else {
                viewPager.setCurrentItem(0);
            }
            return true;
        }
        return false;
    }

    private static TypedArray getIconsFromResource(Context context, boolean active) {
        if (active) {
            return context.getResources().obtainTypedArray(R.array.ic_main_tab);
        } else {
            return context.getResources().obtainTypedArray(R.array.ic_main_tab_off);
        }
    }

    public static void setupTablayout(TabLayout tabLayout, ViewPager vpPager) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(getIconsFromResource(tabLayout.getContext(), false).getResourceId(i, -1));
            }
        }
        setupTablayoutListener(tabLayout, vpPager);
    }

    private static void setupTablayoutListener(TabLayout tabLayout, ViewPager vpPager) {
        vpPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(vpPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.setIcon(getIconsFromResource(tab.parent.getContext(), true).getResourceId(
                                tab.getPosition(), -1)
                        );
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        tab.setIcon(getIconsFromResource(tab.parent.getContext(), false).getResourceId(
                                tab.getPosition(), -1)
                        );
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        vpPager.setCurrentItem(1);
        vpPager.setCurrentItem(0);
    }
}
