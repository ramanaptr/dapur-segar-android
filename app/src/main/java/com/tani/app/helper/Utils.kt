package com.tani.app.helper

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.provider.ContactsContract
import android.provider.Settings
import android.text.Html
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val TAG = Utils.javaClass.canonicalName

    fun getUUID(context: Context): String {
        SessionManger.getUUID(context).isEmpty().let {
            if (it) {
                val uuid =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                SessionManger.putString(context, Constants.UUID, uuid)
                Log.d(TAG, "UUID anda $uuid")
                return uuid
            } else {
                Log.d(TAG, "UUID anda " + SessionManger.getUUID(context))
            }
        }
        return ""
    }

    fun validEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private var passwordVisible = false
    fun showPassword(event: MotionEvent, editText: EditText): Boolean {
        val drawableRight = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= editText.right - editText.compoundDrawables[drawableRight].bounds.width()) {
                if (!passwordVisible) {
                    editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    passwordVisible = true
//                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_on, 0)
                } else {
                    editText.transformationMethod = PasswordTransformationMethod.getInstance()
                    passwordVisible = false
//                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_off, 0)
                }
                return true
            }
        }
        return false
    }

//    private var showClear = false
//    fun showClear(event: MotionEvent, editText: EditText): Boolean {
//        val drawableRight = 2
//        if (event.action == MotionEvent.ACTION_UP) {
//            if (event.rawX >= editText.right - editText.compoundDrawables[drawableRight].bounds.width()) {
//                if (!showClear) {
//                    showClear = true
//                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
//                } else {
//                    editText.text.clear()
//                    showClear = false
//                    editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear, 0)
//                }
//                return true
//            }
//        }
//        return false
//    }

    fun hideKeyboard(act: Activity) {
        val inputManager = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.hideSoftInputFromWindow(act.currentFocus?.windowToken, HIDE_NOT_ALWAYS)
    }

    fun adjustLayoutResize(act: Activity) {
        act.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        act.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    fun adjustLayoutNothing(act: Activity) {
        act.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    fun getContact(context: Context, data: Uri?): String? {
        val contentResolver = context.contentResolver
        val c = data?.let { contentResolver.query(it, null, null, null, null) }
        c?.let {
            if (c.moveToFirst()) {
                val id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                val hasPhone =
                    c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                if (hasPhone.equals("1", ignoreCase = true)) {
                    val phones: Cursor? = context.contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null,
                        null
                    )
                    phones?.moveToFirst()
                    val coloumn =
                        phones?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val contactNumber = coloumn?.let { phones.getString(it) }
                    phones?.close()
                    c.close()
                    return contactNumber?.replace("+62", "0")
                        ?.replace("62", "0")
                        ?.replace("-", "")
                        ?.replace(" ", "")
                        ?.trim()
                }
            }
        }
        c?.close()
        return ""
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun formatDate(date: Date): String {
        return SimpleDateFormat("dd-mm-yyyy: HH:mm:ss").format(date)
    }

    fun formatDateStartYears(date: Date): String {
        val formateDate = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formateDate, Locale.US)
        return sdf.format(date)
    }

    fun formatDateFromString(date: String): String {
        return if (date.contains("T")) {
            val fromatDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(date)
            SimpleDateFormat("dd-MM-yyyy: HH:mm:ss").format(fromatDate)
        } else {
            date
        }
    }

    fun copyText(act: Activity, text: String) {
        val clipboard = act.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText(text, text)
        clipboard?.setPrimaryClip(clip)
    }

    fun showDatePicker(
        act: Activity,
        calendar: Calendar,
        listener: DatePickerDialog.OnDateSetListener
    ) {
        DatePickerDialog(
            act,
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun setFocusOnError(editText: TextInputEditText?, act: Activity) {
        if (editText?.requestFocus()!!) {
            act.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    fun setFocusOnError(editText: EditText?, act: Activity) {
        if (editText?.requestFocus()!!) {
            act.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    fun doubleToCurrencyString(double: Double?): String {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.currency = Currency.getInstance("IDR")
        return format.format(double).replace("IDR", "Rp.").replace(".00", "")
    }

    fun disableEditextFocus(editText: EditText, disable: Boolean, act: Activity) {
        editText.inputType = InputType.TYPE_NULL
        editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!disable) {
                val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
            }
        }
    }

    fun blackIconStatusBar(act: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            act.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun changeColorStatusBar(act: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val startColor = act.window.statusBarColor
            val endColor = ContextCompat.getColor(act, color)
            ObjectAnimator.ofArgb(act.window, "statusBarColor", startColor, endColor).start()
            act.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun showErrorTextInput(target: TextInputLayout, errorMessage: String) {
        enableError(target)
        target.error = errorMessage
    }

    fun hideErrorTextInput(target: TextInputLayout) {
        disableError(target)
        target.error = null
    }

    private fun enableError(textInputLayout: TextInputLayout) {
        if (textInputLayout.childCount == 2)
            textInputLayout.getChildAt(1).visibility = View.VISIBLE
    }

    private fun disableError(textInputLayout: TextInputLayout) {
        if (textInputLayout.childCount == 2)
            textInputLayout.getChildAt(1).visibility = View.GONE
    }

    fun showViews(vararg views: View?) {
        if (views.isNotEmpty())
            for (view in views)
                view?.visibility = View.VISIBLE
    }

    fun invisibleViews(vararg views: View?) {
        if (views.isNotEmpty())
            for (view in views)
                view?.visibility = View.INVISIBLE

    }

    fun hideViews(vararg views: View?) {
        if (views.isNotEmpty())
            for (view in views)
                view?.visibility = View.GONE

    }

    fun handleDoubleClick(vararg views: View?) {
        if (views.isNotEmpty())
            for (view in views) {
                view?.isClickable = true
                view?.isEnabled = false
                Handler().postDelayed({ view?.isEnabled = true }, 1000)
            }
    }

    fun <T> convertMutibleMapToObject(data: MutableMap<String, String>, clazz: Class<T>): Any? {
        val gson = Gson()
        val jsonFromMap = gson.toJson(data)
        return gson.fromJson(jsonFromMap, clazz::class.java)
    }

    fun htmlColorText(target: TextView?, htmlString: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            target?.setText(
                Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY),
                TextView.BufferType.SPANNABLE
            )
        } else {
            target?.setText(Html.fromHtml(htmlString), TextView.BufferType.SPANNABLE)
        }
    }

    fun htmlColorText(target: EditText?, htmlString: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            target?.setText(
                Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY),
                TextView.BufferType.SPANNABLE
            )
        } else {
            target?.setText(Html.fromHtml(htmlString), TextView.BufferType.SPANNABLE)
        }
    }

}