package com.tani.app.helper;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;

public class Validator {

    public static  boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;

        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhone(String phone){
        if (TextUtils.isEmpty(phone) || phone.length() <= 6)
            return false;
        Matcher matcher = Patterns.PHONE.matcher(phone);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password))
            return false;
        return password.length() > 7;
    }

    public static  void showErrorTextInput(TextInputLayout target, String errorMessage) {
        enableError(target);
        target.setError(errorMessage);
    }
    public static void hideErrorTextInput(TextInputLayout target) {
        disableError(target);
        target.setError(null);
    }

    private static void enableError(TextInputLayout textInputLayout) {
        if (textInputLayout.getChildCount() == 2)
            textInputLayout.getChildAt(1).setVisibility(View.VISIBLE);
    }
    private static void disableError(TextInputLayout textInputLayout) {
        if (textInputLayout.getChildCount() == 2)
            textInputLayout.getChildAt(1).setVisibility(View.GONE);
    }

}
