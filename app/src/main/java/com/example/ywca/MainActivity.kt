package com.example.ywca

import android.os.Bundle
import android.view.Menu
import android.webkit.WebView
import android.widget.Button
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setLogo(R.mipmap.ywca_logo)
            setDisplayUseLogoEnabled(true)
        }

        // Find the WebView in the layout
        val webView = findViewById<WebView>(R.id.web_view)

        // Set click listeners for buttons
        val paymentButton = findViewById<Button>(R.id.but_payment)
        paymentButton.setOnClickListener {
            // Load the URL into the WebView
            webView.loadUrl("https://www.ywca.edu.bd/webform/index.php")
        }

        // Set click listeners for buttons
        val resultButton = findViewById<Button>(R.id.btn_result)
        resultButton.setOnClickListener {
            // Load the URL into the WebView
            webView.loadUrl("https://www.ywca.edu.bd/webform/index.php")
        }

        // Set click listeners for buttons
        val profileButton = findViewById<Button>(R.id.but_profile)
        profileButton.setOnClickListener {
            // Load the URL into the WebView
            webView.loadUrl("https://www.ywca.edu.bd/webform/index.php")
        }

        // Set click listeners for buttons
        val messageButton = findViewById<Button>(R.id.but_msg)
        messageButton.setOnClickListener {
            // Load the URL into the WebView
            webView.loadUrl("https://www.ywca.edu.bd/webform/index.php")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }
}

