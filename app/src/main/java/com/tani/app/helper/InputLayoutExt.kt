package com.tani.app.helper

import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.NestedScrollView
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Ramana on 13-Oct-19.
 */

fun AppCompatActivity.setFocusOnError(target: AppCompatEditText?) {
    target?.requestFocus()?.run {
        if (this) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }
}

class EditTextDTO(
    val inputLayout: TextInputLayout,
    val target: AppCompatEditText,
    val status: Boolean,
    val text: String
)

fun NestedScrollView.focus(input: View) {
    Handler().post { smoothScrollTo(0, input.top - input.height) }
    input.requestFocus()
}

fun TextInputLayout.isNotEmpty(target: AppCompatEditText, msg: String): EditTextDTO {
    val text = target.text.toString().trim()
    return if (text.isNotEmpty()) {
        disableError()
        error = null
        EditTextDTO(this, target, true, text)
    } else {
        enableError()
        error = msg
        EditTextDTO(this, target, false, text)
    }
}

fun TextInputLayout.showError(msg: String): TextInputLayout {
//    enableError()
    error = msg
    return this
}

fun TextInputLayout.hideError(): Boolean {
//    disableError()
    error = null
    return true
}

private fun TextInputLayout.enableError() {
    if (childCount == 2)
        getChildAt(1).visibility = View.VISIBLE
}

private fun TextInputLayout.disableError() {
    if (childCount == 2)
        getChildAt(1).visibility = View.GONE
}