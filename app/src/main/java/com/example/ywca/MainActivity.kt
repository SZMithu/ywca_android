package com.example.ywca

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class MainActivity : ComponentActivity()  {
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
    }

}