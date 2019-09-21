package com.tani.app.helper;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.internal.CheckableImageButton;
import com.tani.app.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @NotNull
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
}
