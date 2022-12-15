package com.example.prxwebapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val postsApi = PostRetrofit.getRetrofit()

        val button = findViewById<Button>(R.id.getPostButton)
        val textView = findViewById<TextView>(R.id.postsText)

        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                val posts = postsApi.getPosts()
                withContext(Dispatchers.Main){
                    textView.text = posts.body().toString()
                }
            }
        }
    }
}