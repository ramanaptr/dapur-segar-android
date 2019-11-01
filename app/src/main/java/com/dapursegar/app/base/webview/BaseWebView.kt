package com.dapursegar.app.base.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.ActionBar
import com.dapursegar.app.R
import com.dapursegar.app.base.activity.BaseActivity
import com.dapursegar.app.base.extension.gone
import kotlinx.android.synthetic.main.default_web_view.*

class BaseWebView : BaseActivity() {

    companion object {
        const val OPEN_URL = "open_url"
    }

    override fun statusBarColor(): Int = R.color.white

    override fun setTitle(): String = "Syarat & Ketentuan"

    override fun setLayout(): Int = R.layout.default_web_view

    override fun onCreate(savedInstanceState: Bundle?, actionBar: ActionBar?) {
        onLoadweb()
    }

    override fun onClick() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun onLoadweb() {
        val url = intent.extras?.getString(OPEN_URL)
        webView.settings.userAgentString = "Android"
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loading.gone()
            }
        }
    }
}
