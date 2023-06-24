package com.example.ywca

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ywca.network.Api
import com.example.ywca.network.Property
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoticeActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private var propertyList: List<Property> = emptyList()
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        // Initialize progress bar
        progressBar = findViewById(R.id.progress_bar)
        actionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setLogo(R.mipmap.ywca_logo)
            setDisplayUseLogoEnabled(true)
        }
        manager = LinearLayoutManager(this)
        getAllData()


        // Set click listeners for buttons
        val paymentButton = findViewById<Button>(R.id.btn_payment)
        paymentButton.setOnClickListener {
            openWebView()
        }

        // Set click listeners for buttons
        val resultButton = findViewById<Button>(R.id.btn_result)
        resultButton.setOnClickListener {
            openWebView()
        }

        // Set click listeners for buttons
        val profileButton = findViewById<Button>(R.id.btn_profile)
        profileButton.setOnClickListener {
            openWebView()
        }

        // Set click listeners for buttons
        val messageButton = findViewById<Button>(R.id.btn_msg)
        messageButton.setOnClickListener {
            openWebView()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    private fun getAllData(){
        progressBar.visibility = View.VISIBLE
        Api.retrofitService.getAllData().enqueue(object: Callback<List<Property>>{
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                if(response.isSuccessful){
                    propertyList = response.body() ?: emptyList()
                    recyclerView = findViewById<RecyclerView>(R.id.rv_notice_list).apply{
                        myAdapter = NoticeAdapter(propertyList)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
                // Hide progress bar
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                t.printStackTrace()
                progressBar.visibility = View.VISIBLE
            }
        })
    }
    //function to open webview on button click
    private fun openWebView() {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)

    }

}
