package com.example.banana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val joinBtn = findViewById<Button>(R.id.join_button);

        joinBtn.setOnClickListener{

        }
    }
}