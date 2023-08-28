package com.example.ywca

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize progress bar
        // progressBar = findViewById(R.id.progress_bar)
        actionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setLogo(R.mipmap.ywca_logo)
            setDisplayUseLogoEnabled(true)
        }
        val myCardView = findViewById<CardView>(R.id.noticeCardView)
        myCardView.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }

        // Set click listeners for buttons
        val paymentButton = findViewById<CardView>(R.id.paymentCardView)
        paymentButton.setOnClickListener {
            openWebView()
        }
        // Set click listeners for buttons
        val messageButton = findViewById<CardView>(R.id.messageCardView)
        messageButton.setOnClickListener {
            openGmailWebView()
        }
        // Set click listeners for buttons
        val profileButton = findViewById<CardView>(R.id.profileCardView)
        profileButton.setOnClickListener {
            openWebView()
        }
        // Set click listeners for buttons
        val admissionButton = findViewById<CardView>(R.id.admissionCardView)
        admissionButton.setOnClickListener {
            openAdmissionWebView()
        }
        // Set click listeners for buttons
        val resultButton = findViewById<CardView>(R.id.resultCardView)
        resultButton.setOnClickListener {
            openWebView()
        }
    }


    private fun openWebView() {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)

    }

    private fun openAdmissionWebView() {
        val intent = Intent(this, AdmissionWebViewActivity::class.java)
        startActivity(intent)

    }

    private fun openGmailWebView() {
        val intent = packageManager.getLaunchIntentForPackage("com.google.android.gm")
        if(intent != null){
            startActivity(intent)
        }else{
            val webIntent = Intent(this, GmailWebViewActivity::class.java)
            startActivity(webIntent)
        }
    }
}
