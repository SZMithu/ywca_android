package com.example.ywca

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog



class AdmissionWebViewActivity : ComponentActivity() {
    private var webViewPopUp: WebView? = null
    private var builder: AlertDialog? = null
    private var globalContext: Context? = null
    private val url = "https://ywca.edu.bd/admission-form/index.php"
    private var webView: WebView? = null
    private var userAgent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        webView = findViewById<WebView>(R.id.web_view)
        webView?.setWebViewClient(WebViewClient())
        webView?.loadUrl(url)

        val webSettings = webView?.settings
        webSettings?.javaScriptEnabled = true

        // Set User Agent
        userAgent = System.getProperty("http.agent")
        webSettings?.setUserAgentString(userAgent + "ywca")

        // Enable Cookies
        CookieManager.getInstance().setAcceptCookie(true)
        if (Build.VERSION.SDK_INT >= 21) CookieManager.getInstance()
            .setAcceptThirdPartyCookies(webView, true)

        // Handle Popups
        webView?.setWebChromeClient(CustomChromeClient())
        webSettings?.javaScriptCanOpenWindowsAutomatically = true
        webSettings?.setSupportMultipleWindows(true)
        globalContext = this.applicationContext

        // WebView Tweaks
        webSettings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings?.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings?.domStorageEnabled = true
        webSettings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings?.useWideViewPort = true
        webSettings?.saveFormData = true
        webSettings?.setEnableSmoothTransition(true)
        webView?.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

        webView?.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                findViewById<View>(R.id.progress_bar)?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                // do your stuff here
                findViewById<View>(R.id.progress_bar)?.visibility = View.GONE
            }
        })
    }

    internal inner class CustomChromeClient : WebChromeClient() {
        override fun onCreateWindow(
            view: WebView?, isDialog: Boolean,
            isUserGesture: Boolean, resultMsg: Message
        ): Boolean {
            webViewPopUp = WebView(globalContext!!)
            webViewPopUp!!.isVerticalScrollBarEnabled = false
            webViewPopUp!!.isHorizontalScrollBarEnabled = false
            webViewPopUp!!.webChromeClient = CustomChromeClient()
            webViewPopUp!!.settings.javaScriptEnabled = true
            webViewPopUp!!.settings.saveFormData = true
            webViewPopUp!!.settings.setEnableSmoothTransition(true)
            webViewPopUp!!.settings.userAgentString = userAgent + "yourAppName"

            // pop the  webview with alert dialog
            builder = AlertDialog.Builder(this@AdmissionWebViewActivity).create()
            builder?.setTitle("")
            builder?.setView(webViewPopUp)
            builder?.setButton(DialogInterface.BUTTON_POSITIVE, "Close", DialogInterface.OnClickListener { dialog, id ->
                webViewPopUp!!.destroy()
                dialog.dismiss()
            })
            builder?.show()
            builder?.getWindow()
                ?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            if (Build.VERSION.SDK_INT >= 21) {
                cookieManager.setAcceptThirdPartyCookies(webViewPopUp, true)
                cookieManager.setAcceptThirdPartyCookies(webView, true)
            }
            val transport = resultMsg.obj as WebViewTransport
            transport.webView = webViewPopUp
            resultMsg.sendToTarget()
            return true
        }

        override fun onCloseWindow(window: WebView) {
            //Toast.makeText(contextPop,"onCloseWindow called",Toast.LENGTH_SHORT).show();
            try {
                webViewPopUp!!.destroy()
            } catch (e: Exception) {
                Log.d("Destroyed with Error ", e.stackTrace.toString())
            }
            try {
                builder?.dismiss()
            } catch (e: Exception) {
                Log.d("Dismissed with Error: ", e.stackTrace.toString())
            }
        }
    }

    override fun onBackPressed() {
        if (webView != null) {
            if (webView!!.canGoBack()) {
                webView!!.goBack()
                return
            }
        }
        super.onBackPressed()
    }
}