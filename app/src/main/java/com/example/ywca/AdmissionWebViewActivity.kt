package com.example.ywca

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity




class AdmissionWebViewActivity : ComponentActivity() {
    private lateinit var webView: WebView
    private val url = "https://ywca.edu.bd/admission-form/index.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an Intent to open the URL in the default browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
    // Override onBackPressed to handle navigation within the WebView
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}