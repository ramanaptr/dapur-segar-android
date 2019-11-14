package com.dapursegar.app.base.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.dapursegar.app.R
import com.dapursegar.app.base.extension.changeColorStatusBar
import com.dapursegar.app.base.extension.gone
import com.dapursegar.app.base.extension.setupToolbar
import kotlinx.android.synthetic.main.toolbar_default.*
import kotlinx.android.synthetic.main.default_web_view.*

class BaseWebView : AppCompatActivity() {

    companion object {
        const val OPEN_URL = "open_url"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.default_web_view)
        setupToolbar(tvTitle, "Syarat & Ketentuan", toolbar)
        changeColorStatusBar(R.color.white)
        onLoadweb()
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
